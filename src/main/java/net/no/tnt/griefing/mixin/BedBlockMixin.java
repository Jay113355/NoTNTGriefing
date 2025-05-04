package net.no.tnt.griefing.mixin;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BedBlock.class)
public class BedBlockMixin {

    @ModifyArgs(
        method = "useWithoutItem",
        require = 0,
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"
        )
    )
    private void modifyExplosionInteraction(Args args) {
        for (int i = 0; i < args.size(); i++) {
            Object obj = args.get(i);
            if (obj instanceof Boolean) {
                args.set(i, false); // Turn off the explosion particles
            } else if (obj instanceof Level.ExplosionInteraction) {
                args.set(i, Level.ExplosionInteraction.NONE); // Turn off the explosion
            }
        }
    }
}
