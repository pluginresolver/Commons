package com.caved_in.commons.command.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.command.FlagArg;
import com.caved_in.commons.command.Flags;
import com.caved_in.commons.permission.Perms;
import com.caved_in.commons.player.Players;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class MoreCommand {
    @Command(identifier = "more", permissions = {Perms.COMMAND_MORE})
    @Flags(identifier = {"a"})
    public void onMoreCommand(Player player, @FlagArg("a") final boolean allItems) {
        if (allItems) {
            PlayerInventory inventory = player.getInventory();
            for (ItemStack item : inventory.getContents()) {
                if (item == null) {
                    continue;
                }

                item.setAmount(item.getMaxStackSize());
            }
            return;
        }

        if (!Players.hasItemInHand(player)) {
            Chat.message(player, Messages.ITEM_IN_HAND_REQUIRED);
            return;
        }

        ItemStack playerHandItem = player.getItemInHand();
        playerHandItem.setAmount(64);
        player.setItemInHand(playerHandItem);
    }
}
