package net.no.tnt.griefing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class NoTNTGriefing implements ModInitializer {
	public static GameRules.Key<GameRules.BooleanValue> TNT_GRIEFING;
	public static GameRules.Key<GameRules.BooleanValue> BED_GRIEFING;
	public static GameRules.Key<GameRules.BooleanValue> RESPAWN_ANCHOR_GRIEFING;
	public static GameRules.Key<GameRules.BooleanValue> END_CRYSTAL_GRIEFING;

	@Override
	public void onInitialize() {
		TNT_GRIEFING = GameRuleRegistry.register("doTntGriefing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
		BED_GRIEFING = GameRuleRegistry.register("doBedGriefing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
		RESPAWN_ANCHOR_GRIEFING = GameRuleRegistry.register("doRespawnAnchorGriefing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
		END_CRYSTAL_GRIEFING = GameRuleRegistry.register("doEndCrystalGriefing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
	}
}
