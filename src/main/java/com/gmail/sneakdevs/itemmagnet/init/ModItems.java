package com.gmail.sneakdevs.itemmagnet.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.gmail.sneakdevs.itemmagnet.items.ItemMagnet;

public class ModItems {

    public static final ItemMagnet MAGNET = new ItemMagnet();

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("itemmagnet", "magnet"), MAGNET);
    }

}
