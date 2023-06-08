package com.gmail.sneakdevs.itemmagnet;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

public class ItemMagnetMod implements ModInitializer {
    public static final ItemMagnetMod INSTANCE = new ItemMagnetMod();
    int tick = 0;

    public static final Item MAGNET = Registry.register(Registries.ITEM, new Identifier("itemmagnet", "magnet"), new Item(new FabricItemSettings().maxCount(1)));

    @Override
    public void onInitialize() {
        ServerTickEvents.START_SERVER_TICK.register(ItemMagnetMod.INSTANCE::doMagnet);
        addItemsToGroup();
    }

    private static void addItemsToGroup() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(ItemMagnetMod.MAGNET));
    }

    public void doMagnet(MinecraftServer server) {
        tick++;
        if (tick >= 8) {
            List<ServerPlayerEntity> playerList = server.getPlayerManager().getPlayerList();
            for (ServerPlayerEntity player : playerList) {
                if (player.getOffHandStack().getItem() == MAGNET || player.getMainHandStack().getItem() == MAGNET) {
                    List<ItemEntity> entityItems = player.getWorld().getEntitiesByClass(ItemEntity.class, player.getBoundingBox().expand(16.0D), EntityPredicates.VALID_ENTITY);
                    for (ItemEntity entityItemNearby : entityItems) {
                        entityItemNearby.setPickupDelay(0);
                        entityItemNearby.setPosition(player.getPos());
                    }
                }
            }
            tick = 0;
        }
    }
}
