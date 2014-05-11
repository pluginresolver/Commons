package com.caved_in.commons.command.commands;

import com.caved_in.commons.Commons;
import com.caved_in.commons.Messages;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.player.Players;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCommand {
	@Command(name = "unban", description = "Unban / pardon a user from their ban", permission = "tunnels.common.unban", usage = "/unban [Name]", aliases = {"pardon"})
	public void UnbanCommand(CommandSender sender, String[] args) {
		if (args.length > 0) {
			String playerToUnban = args[0];
			String pardonIssuer = "";

			if (sender instanceof Player) {
				pardonIssuer = ((Player) sender).getName();
			} else {
				pardonIssuer = "Console";
			}

			if (Commons.bansDatabase.pardonPlayer(playerToUnban, pardonIssuer)) {
				Players.sendMessage(sender, Messages.playerUnbanned(playerToUnban, pardonIssuer));
			} else {
				Players.sendMessage(sender, Messages.playerNotBanned(playerToUnban));
			}
		} else {
			Players.sendMessage(sender, Messages.invalidCommandUsage("name"));
		}
	}
}
