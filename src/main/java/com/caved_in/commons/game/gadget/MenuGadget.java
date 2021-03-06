package com.caved_in.commons.game.gadget;

import com.caved_in.commons.item.ItemBuilder;
import com.caved_in.commons.menu.ItemMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * An extension of {@link com.caved_in.commons.game.gadget.ItemGadget} used to attach an {@link com.caved_in.commons.menu.ItemMenu}
 * to an item.
 * <p>
 * When the item is interacted with, the gadget will be opened.
 */
public abstract class MenuGadget extends ItemGadget {

    private ItemMenu menu;

    /**
     * Create a new instance of MenuGadget, with the item, menu, and ID assigned.
     *
     * @param builder item builder used to create the item from, which the gadget will attach to.
     * @param menu    menu to open when the item is interacted with.
     */
    public MenuGadget(ItemBuilder builder, ItemMenu menu) {
        super(builder);
        this.menu = menu;
    }

    /**
     * Create a new instance of MenuGadget, with the item, menu, and ID assigned.
     *
     * @param item item to attach the gadget to.
     * @param menu menu to open when the item is interacted with.
     */
    public MenuGadget(ItemStack item, ItemMenu menu) {
        super(item);
        this.menu = menu;
    }

    @Override
    public void perform(Player holder) {
        menu.openMenu(holder);
    }
}
