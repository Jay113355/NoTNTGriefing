package net.no.tnt.griefing.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.phys.Vec3;
import net.no.tnt.griefing.NoTNTGriefing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorBlockMixin {

    @Redirect(
        method = "explode",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"
        )
    )
    private Explosion injected(Level level, Entity entity, DamageSource damageSource, ExplosionDamageCalculator behavior, Vec3 vec3d, float power, boolean createFire, Level.ExplosionInteraction explosionInteraction) {
        if (level instanceof ServerLevel) {// Check server sided
            GameRules gameRules = level.getGameRules();
            if (!gameRules.getBoolean(NoTNTGriefing.RESPAWN_ANCHOR_GRIEFING)){
                return level.explode(null, level.damageSources().badRespawnPointExplosion(vec3d), null, vec3d, 5.0F, false, Level.ExplosionInteraction.NONE);
            }
        }
        return level.explode(null, level.damageSources().badRespawnPointExplosion(vec3d), null, vec3d, 5.0F, true, Level.ExplosionInteraction.BLOCK);
    }
}
