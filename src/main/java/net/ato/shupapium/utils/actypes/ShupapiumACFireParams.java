package net.ato.shupapium.utils.actypes;

import net.ato.shupapium.utils.acattachments.ShupapiumACModifiers;

public record ShupapiumACFireParams(float speed, float spread, float recoilMultiplier) {
    public static ShupapiumACFireParams resolve(ShupapiumACProfile profile, ShupapiumACModifiers mods, float projectileSpeed, float projectileSpread, float barrelSpeedBonus, float barrelSpreadReduction) {
        float speed = (projectileSpeed + profile.getCannonBaseSpeed() + barrelSpeedBonus) * mods.speedMultiplier();
        float spread = Math.max(0F, projectileSpread + profile.getCanonBaseSpread() - barrelSpreadReduction) * mods.spreadMultiplier();
        return new ShupapiumACFireParams(speed, spread, mods.recoilMultiplier());
    }
}
