package com.gmail.sneakdevs.itemmagnet;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.ItemEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import com.gmail.sneakdevs.itemmagnet.init.ModItems;

import java.util.List;

public class ItemMagnet implements ModInitializer {
    public static final ItemMagnet INSTANCE = new ItemMagnet();
    int tick = 0;

    @Override
    public void onInitialize() {
        ModItems.register();
        ServerTickEvents.START_SERVER_TICK.register(ItemMagnet.INSTANCE::doMagnet);
    }

    public void doMagnet(MinecraftServer server) {
        tick++;
        if (tick >= 7) {
            List<ServerPlayerEntity> playerList = server.getPlayerManager().getPlayerList();
            for (ServerPlayerEntity player : playerList) {
                if (player.getOffHandStack().getItem() == ModItems.MAGNET || player.getMainHandStack().getItem() == ModItems.MAGNET) {
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
