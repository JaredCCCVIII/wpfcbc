package net.ato.shupapium;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ShupapiumTags {
    public static final TagKey<EntityType<?>> WARM_OBJECTIVES =
            TagKey.create(Registries.ENTITY_TYPE, MainShupapium.resource("warm"));
}
