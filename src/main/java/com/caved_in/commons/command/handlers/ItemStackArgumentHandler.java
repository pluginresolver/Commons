package com.caved_in.commons.command.handlers;

import com.caved_in.commons.Messages;
import com.caved_in.commons.command.*;
import com.caved_in.commons.exceptions.InvalidMaterialNameException;
import com.caved_in.commons.item.ItemType;
import com.caved_in.commons.item.Items;
import com.caved_in.commons.player.Players;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.EnumSet;

public class ItemStackArgumentHandler extends ArgumentHandler<ItemStack> {
    public ItemStackArgumentHandler() {
        //Add the sender variable,
        addVariable("hand", "item in the hand of the command executor", ItemStackHandArgumentVariable.getInstance());
        addVariable("sender", "item in the hand of the command executor", ItemStackHandArgumentVariable.getInstance());

        for (ItemType itemType : EnumSet.allOf(ItemType.class)) {
            //Add the items ID as a valid identifier.
            addVariable(String.valueOf(itemType.getID()), itemType.getName(), ItemStackArgumentVariable.getInstance());

            //For every alias that exists in the items data, register the variable for that item.
            for (String alias : itemType.getAliases()) {
                addVariable(alias, itemType.getName(), ItemStackArgumentVariable.getInstance());
            }
        }
    }

    @Override
    public ItemStack transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        MaterialData data;
        try {
            data = Items.getMaterialDataFromString(value);
        } catch (InvalidMaterialNameException e) {
            throw new TransformError(e.getMessage());
        }

        return data.toItemStack();
    }

    private static class ItemStackArgumentVariable implements ArgumentVariable<ItemStack> {

        private static ItemStackArgumentVariable instance;

        public static ItemStackArgumentVariable getInstance() {
            if (instance == null) {
                instance = new ItemStackArgumentVariable();
            }
            return instance;
        }

        private ItemStackArgumentVariable() {
        }

        @Override
        public ItemStack var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
            MaterialData data = null;

            try {
                data = Items.getMaterialDataFromString(varName);
            } catch (InvalidMaterialNameException e) {
                throw new CommandError(e.getMessage());
            }

            return data.toItemStack();
        }
    }

    private static class ItemStackHandArgumentVariable implements ArgumentVariable<ItemStack> {
        private static ItemStackHandArgumentVariable instance;

        public static ItemStackHandArgumentVariable getInstance() {
            if (instance == null) {
                instance = new ItemStackHandArgumentVariable();
            }
            return instance;
        }

        @Override
        public ItemStack var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
            if (!(sender instanceof Player)) {
                throw new CommandError(Messages.CANT_AS_CONSOLE);
            }

            Player player = (Player) sender;
            if (Players.handIsEmpty(player)) {
                throw new CommandError(Messages.ITEM_IN_HAND_REQUIRED);
            }

            return player.getItemInHand();
        }
    }
}
