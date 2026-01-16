package net.ato.shupapium;

import dev.engine_room.flywheel.api.visualization.VisualizerRegistry;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.ato.shupapium.client.flywheels.ShupapiumACBreechVisual;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AutocannonBreechVisual;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringVisual;

public class ShupapiumFlywheelVisuals {
    private ShupapiumFlywheelVisuals() {}

    public static void registerVisuals() {
        VisualizerRegistry.setVisualizer(ShupapiumBlockEntities.SHUPAPIUM_AUTOCANNON_BREECH.get(),
                new SimpleBlockEntityVisualizer<>(ShupapiumACBreechVisual::new, be -> false));
        VisualizerRegistry.setVisualizer(ShupapiumBlockEntities.SHUPAPIUM_AUTOCANNON_RECOIL_SPRING.get(),
                new SimpleBlockEntityVisualizer<>(AutocannonRecoilSpringVisual::new, be -> false));
    }
}
