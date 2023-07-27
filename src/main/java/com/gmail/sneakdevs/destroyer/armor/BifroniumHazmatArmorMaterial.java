package com.gmail.sneakdevs.destroyer.armor;

import com.gmail.sneakdevs.destroyer.registry.DestroyerItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class BifroniumHazmatArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {2, 3, 4, 2};

    @Override
    public int getDurability(ArmorItem.Type slot) {
        return BASE_DURABILITY[slot.getEquipmentSlot().getEntitySlotId()] * 15;
    }

    @Override
    public int getProtection(ArmorItem.Type slot) {
        return PROTECTION_VALUES[slot.getEquipmentSlot().getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 5;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(DestroyerItems.BIFRONIUM);
    }

    @Override
    public String getName() {
        return "bifronium_hazmat";
    }

    @Override
    public float getToughness() {
        return 1.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
