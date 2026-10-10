package net.ato.shupapium.utils.actypes;

import net.ato.shupapium.utils.acattachments.ShupapiumACModifiers;
import net.ato.shupapium.utils.acballistics.ShupapiumAmmoBallistics;

public record ShupapiumACFireParams(float speed, float spread, float recoilMultiplier) {
    public static ShupapiumACFireParams resolve(ShupapiumACProfile profile, ShupapiumACModifiers mods, ShupapiumAmmoBallistics ammo, float barrelSpeedBonus, float barrelSpreadReduction) {
        float speed = (ammo.speed() + profile.getCannonBaseSpeed() + barrelSpeedBonus) * mods.speedMultiplier();
        float spread = Math.max(0F, ammo.spread() + profile.getCanonBaseSpread() - barrelSpreadReduction) * mods.spreadMultiplier();
        return new ShupapiumACFireParams(speed, spread, mods.recoilMultiplier());
    }
}
