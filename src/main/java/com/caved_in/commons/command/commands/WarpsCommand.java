package com.caved_in.commons.command.commands;

import com.caved_in.commons.command.Command;
import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.commons.warp.Warps;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WarpsCommand {
	@Command(name = "warps", permission = "tunnels.common.warps")
	public void onWarpsCommand(Player player, String[] args) {
		int page = 1;
		HelpScreen warpsMenu = HelpMenus.generateHelpScreen("Warps / Waypoints", HelpMenus.PageDisplay.DEFAULT, HelpMenus.ItemFormat.NO_DESCRIPTION, ChatColor.GREEN, ChatColor.DARK_GREEN);
		for (String warp : Warps.getWarps()) {
			warpsMenu.setEntry(warp, "");
		}
		if (args.length > 0) {
			String pageArg = args[0];
			if (StringUtils.isNumeric(pageArg)) {
				page = Integer.parseInt(pageArg);
			}
		}
		warpsMenu.sendTo(player, page, 7);
	}
}
