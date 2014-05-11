package com.caved_in.commons.command.commands;

import com.caved_in.commons.Commons;
import com.caved_in.commons.Messages;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.player.Players;
import org.bukkit.command.CommandSender;

public class SilenceCommand {
	@Command(name = "silence", permission = "tunnels.common.silence")
	public void silenceLobbyCommand(CommandSender sender, String[] args) {
		Commons.getConfiguration().getWorldConfig().setChatSilenced(true);
		Players.messageAll(Messages.CHAT_SILENCED);
	}
}
