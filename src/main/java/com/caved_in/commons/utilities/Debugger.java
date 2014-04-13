package com.caved_in.commons.utilities;

import com.caved_in.commons.item.Items;
import com.caved_in.commons.location.Locations;
import com.caved_in.commons.player.Players;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class Debugger {
	private static final String BLOCK_BREAK_MESSAGE = "&9@[%sx,%sy,%sz]: &e#%s &7(%s) &r[%s] (%s/15)";
	private static final String INVENTORY_CLICK_MESSAGE = "&7[%s / %s] (%s / %s)";
	private static final String COMMAND_PREPROCESS_MESSAGE = "&7/%s &r has &l%s&r arguments %s&7";

	public static void debugEvent(Player player, Event event) {
		if (event instanceof BlockBreakEvent) {
			debugBlockBreakEvent(player, (BlockBreakEvent) event);
		} else if (event instanceof InventoryClickEvent) {
			debugInventoryClickEvent(player, (InventoryClickEvent) event);
		}
	}

	public static void debugBlockBreakEvent(Player player, BlockBreakEvent event) {
		Block block = event.getBlock();
		int blockId = block.getTypeId();
		String blockName = Items.getFormattedMaterialName(block.getType());
		int[] blockCords = Locations.getXYZ(block.getLocation());
		String debugMessage = String.format(BLOCK_BREAK_MESSAGE, blockCords[0], blockCords[1], blockCords[2], blockId, blockName, block.getState().getData().getData(), block.getLightLevel());
		Players.sendMessage(player, debugMessage);
	}

	public static void debugInventoryClickEvent(Player player, InventoryClickEvent event) {
		String action = event.getAction().toString();
		String clickType = event.getClick().toString();
		int rawSlot = event.getRawSlot();
		int slot = event.getSlot();
		String inventoryMessage = String.format(INVENTORY_CLICK_MESSAGE, slot, rawSlot, clickType, action);
		Players.sendMessage(player, inventoryMessage);
	}

	public static void debugCommandPreProcessEvent(Player player, PlayerCommandPreprocessEvent event) {
		String command = event.getMessage();
		String addition = "";
		String[] args = command.split(" ");
		int argLength = args.length;
		//Check the arguments and parse them
		if (argLength > 0) {
			for (int i = 0; i < argLength; i++) {
				addition += "[" + i + " -> " + args[i] + "]" + (i == (argLength - 1) ? "" : ", ");
			}
		}
		String message = String.format(COMMAND_PREPROCESS_MESSAGE, command, argLength, addition);
		Players.sendMessage(player, message);
	}
}
