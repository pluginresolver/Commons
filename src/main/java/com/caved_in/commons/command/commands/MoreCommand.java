package com.caved_in.commons.command.commands;

import com.caved_in.commons.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MoreCommand {
	@Command(name = "more", usage = "/more", permission = "tunnels.common.more")
	public void onMoreCommand(Player player, String[] args) {
		ItemStack playerHandItem = player.getItemInHand();
		playerHandItem.setAmount(playerHandItem.getMaxStackSize());
		player.setItemInHand(playerHandItem);
	}
}
