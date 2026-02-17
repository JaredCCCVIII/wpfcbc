package net.ato.shupapium;

import net.ato.shupapium.utils.accombos.*;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfileHandler;

public class ShupapiumACProfiles {
    public static final ShupapiumACProfile MACHINE_GUN_PROFILE = new MachineGunProfile();
    public static final ShupapiumACProfile LIGHT_MACHINE_GUN_PROFILE = new LMGProfile();
    public static final ShupapiumACProfile HEAVY_MACHINE_GUN_PROFILE = new HMGProfile();
    public static final ShupapiumACProfile COVERED_MACHINE_GUN_PROFILE = new SilencedMachineGunProfile();
    public static final ShupapiumACProfile COVERED_LIGHT_MACHINE_GUN_PROFILE = new SilencedLMGProfile();
    public static final ShupapiumACProfile COVERED_HEAVY_MACHINE_GUN_PROFILE = new SilencedHMGProfile();
    public static final ShupapiumACProfile LIGHT_AUTOCANNON_PROFILE = new LightCannonProfile();
    public static final ShupapiumACProfile HEAVY_AUTOCANNON_PROFILE = new HeavyCannonProfile();
    public static final ShupapiumACProfile ROTARY_AUTOCANNON_PROFILE = new RotaryCannonProfile();
    public static final ShupapiumACProfile BATTLE_CANNON_PROFILE = new BattleCannonProfile();
    public static final ShupapiumACProfile THICK_BATTLE_CANNON_PROFILE = new BigBattleCannonProfile();
    public static final ShupapiumACProfile ARTILLERY_BATTLE_CANNON_PROFILE = new ArtilleryCannonProfile();
    public static final ShupapiumACProfile MORTAR_PROFILE = new MortarCannonProfile();
    public static final ShupapiumACProfile ROCKET_POD_PROFILE = new RocketPodProfile();
    public static final ShupapiumACProfile FLAME_GUN_PROFILE = new FlamethrowerGunProfile();
    public static final ShupapiumACProfile COVERED_FLAME_GUN_PROFILE = new SilencedFlamethrowerGunProfile();
    public static final ShupapiumACProfile MINIGUN_PROFILE = new MinigunGunProfile();

    public static void register() {
        ShupapiumACProfileHandler.register(MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(LIGHT_MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(HEAVY_MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(COVERED_MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(COVERED_LIGHT_MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(COVERED_HEAVY_MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(LIGHT_AUTOCANNON_PROFILE);
        ShupapiumACProfileHandler.register(HEAVY_AUTOCANNON_PROFILE);
        ShupapiumACProfileHandler.register(ROTARY_AUTOCANNON_PROFILE);
        ShupapiumACProfileHandler.register(BATTLE_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(THICK_BATTLE_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(ARTILLERY_BATTLE_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(MORTAR_PROFILE);
        ShupapiumACProfileHandler.register(ROCKET_POD_PROFILE);
        ShupapiumACProfileHandler.register(FLAME_GUN_PROFILE);
        ShupapiumACProfileHandler.register(COVERED_FLAME_GUN_PROFILE);
        ShupapiumACProfileHandler.register(MINIGUN_PROFILE);
    }
}
