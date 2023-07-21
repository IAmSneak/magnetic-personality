package com.gmail.sneakdevs.destroyer.registry;

import com.gmail.sneakdevs.destroyer.DestroyerMod;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class DestroyerGameRules {
    public static final GameRules.Key<GameRules.IntRule> BIFRONIC_SPREAD_CHANCE = GameRuleRegistry.register(DestroyerMod.MODID + "_bifronicSpreadChance", GameRules.Category.MISC, GameRuleFactory.createIntRule(1000, 0));
    public static final GameRules.Key<GameRules.BooleanRule> BLOCK_X_CREATABLE = GameRuleRegistry.register(DestroyerMod.MODID + "_blockXCreatable", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
}
