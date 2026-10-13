package net.ato.shupapium.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

@FunctionalInterface
public interface ShupapiumMuzzleEffect {
    void apply(ServerLevel level, Vec3 pos, Vec3 vel);
}
