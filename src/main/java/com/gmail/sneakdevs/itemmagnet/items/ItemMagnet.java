package com.gmail.sneakdevs.itemmagnet.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemMagnet extends Item {
    public ItemMagnet() {
        super(new Settings().maxCount(1).group(ItemGroup.TOOLS));
    }
}
