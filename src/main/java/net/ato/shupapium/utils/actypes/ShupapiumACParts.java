package net.ato.shupapium.utils.actypes;

import net.ato.shupapium.cannons.ShupapiumACBarrelBlock;
import net.ato.shupapium.cannons.ShupapiumACBreechBlock;

public record ShupapiumACParts(
        ShupapiumACBarrelBlock barrel,
        ShupapiumACBreechBlock breech
) {
}
