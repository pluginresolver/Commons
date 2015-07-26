package com.caved_in.commons.item;

import org.bukkit.enchantments.Enchantment;

public class EnchantWrapper {
    private String enchantment;
    private int level;
    private boolean itemGlow = false;

    public EnchantWrapper(Enchantment enchantment, int level, boolean itemGlow) {
        this.enchantment = enchantment.getName();
        this.level = level;
        this.itemGlow = itemGlow;
    }

    public EnchantWrapper() {


    }

    public Enchantment getEnchantment() {
        return Enchantment.getByName(enchantment);
    }

    public boolean isItemGlow() {
        return itemGlow;
    }

    public int getLevel() {
        return level;
    }

    public EnchantWrapper enchantment(Enchantment enchant, int level) {
        this.enchantment = enchant.getName();
        this.level = level;
        return this;
    }

    public EnchantWrapper glow(boolean glow) {
        this.itemGlow = glow;
        return this;
    }
}
