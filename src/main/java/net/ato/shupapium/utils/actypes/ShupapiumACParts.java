package net.ato.shupapium.utils.actypes;

import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBarrelBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AutocannonBreechBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlock;

public record ShupapiumACParts(
        AutocannonBarrelBlock barrel,
        AutocannonRecoilSpringBlock attachment,
        AutocannonBreechBlock breech
) {
}
