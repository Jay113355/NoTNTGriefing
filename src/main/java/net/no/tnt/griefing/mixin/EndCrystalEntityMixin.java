package net.no.tnt.griefing.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.no.tnt.griefing.NoTNTGriefing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EndCrystal.class)
public abstract class EndCrystalEntityMixin extends Entity {
    public EndCrystalEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @ModifyArg(
        method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"
        )
    )
    private Level.ExplosionInteraction modifyExplosionInteraction(Level.ExplosionInteraction explosionInteraction) {
        if (this.level() instanceof ServerLevel level) {
            GameRules gameRules = level.getGameRules();
            if (!gameRules.getBoolean(NoTNTGriefing.END_CRYSTAL_GRIEFING)) {
                return Level.ExplosionInteraction.NONE;
            }
        }
        return Level.ExplosionInteraction.BLOCK;
    }
}
