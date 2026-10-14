package net.ato.shupapium.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumAttachments;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Method;
import java.util.*;

public final class ProjectileManager {
    private static final Method ON_HIT = findOnHit();
    private static final int FALL_GRACE_TICKS = 400;
    private record Entry(long expireTick, UUID id) {}
    private static final Map<ResourceKey<Level>, PriorityQueue<Entry>> QUEUES = new HashMap<>();

    private ProjectileManager() {}

    public static void track(AbstractArrow projectile, ServerLevel level, int lifetimeTicks, ExpireAction action) {
        projectile.setData(ShupapiumAttachments.TRACK, new TrackData(level.getGameTime() + lifetimeTicks, action));
    }

    public static void expire(ServerLevel level, AbstractArrow projectile, ExpireAction action) {
        //MainShupapium.LOGGER.info("Projectile expired at {}", projectile.position());
        switch (action) {
            case FALL -> {
                projectile.setNoGravity(false);
                projectile.setData(ShupapiumAttachments.TRACK, new TrackData(level.getGameTime() + FALL_GRACE_TICKS, ExpireAction.DISCARD));
                enqueue(level, projectile);
            }
            case DETONATE -> {
                detonate(projectile);
                if (projectile.isAlive()) projectile.discard();
            }
            case DISCARD -> projectile.discard();
        }
    }

    public static void enqueue(ServerLevel level, AbstractArrow projectile) {
        TrackData data = projectile.getData(ShupapiumAttachments.TRACK);
        QUEUES.computeIfAbsent(level.dimension(), k -> new PriorityQueue<>(Comparator.comparingLong(Entry::expireTick))).add(new Entry(data.expireTick(), projectile.getUUID()));
    }

    public static void tick(ServerLevel level) {
        PriorityQueue<Entry> queue = QUEUES.get(level.dimension());
        if (queue == null) return;
        long now = level.getGameTime();
        while (!queue.isEmpty() && queue.peek().expireTick() <= now) {
            Entry e = queue.poll();
            if (!(level.getEntity(e.id()) instanceof AbstractArrow a) || !a.isAlive() || !a.hasData(ShupapiumAttachments.TRACK)) continue;
            TrackData data = a.getData(ShupapiumAttachments.TRACK);
            if (data.expireTick() != e.expireTick()) continue;
            expire(level, a, data.action());
        }
    }

    public static void clear() {QUEUES.clear();}

    public enum ExpireAction {FALL, DETONATE, DISCARD}

    public static void detonate(AbstractArrow projectile) {
        Vec3 motion = projectile.getDeltaMovement();
        Direction face = motion.lengthSqr() > 1.0E-7 ? Direction.getNearest(motion.x, motion.y, motion.z).getOpposite() : Direction.UP;
        try {
            ON_HIT.invoke(projectile, new BlockHitResult(projectile.position(), face, projectile.blockPosition(), false));
        } catch (ReflectiveOperationException e) {
            MainShupapium.LOGGER.error("Could not detonate the projectile", e);
        }
    }

    public record TrackData(long expireTick, ExpireAction action) {
        public static final Codec<TrackData> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.LONG.fieldOf("expire").forGetter(TrackData::expireTick),
                Codec.STRING.xmap(ExpireAction::valueOf, ExpireAction::name).fieldOf("action").forGetter(TrackData::action)
        ).apply(i, TrackData::new));
    }

    private static Method findOnHit() {
        try {
            Method m = Projectile.class.getDeclaredMethod("onHit", HitResult.class);
            m.setAccessible(true);
            return m;
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
