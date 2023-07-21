package com.gmail.sneakdevs.destroyer.registry;

import com.gmail.sneakdevs.destroyer.DestroyerMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class DestroyerTags {
    public static final TagKey<Block> PACIFIES_BIFRONIC_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(DestroyerMod.MODID, "pacifies_bifronic_blocks"));
}
