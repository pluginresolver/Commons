package com.caved_in.commons.command.commands;

import com.caved_in.commons.command.Command;
import com.caved_in.commons.command.SubCommand;
import org.bukkit.command.CommandSender;

public class CommonsCommand {
	@Command(name = "commons", permission = "commons.command.commons")
	public void onCommonsCommand(CommandSender sender, String[] args) {

	}

	@SubCommand(name = "reload", parent = "commons", permission = "commons.reload")
	public void onCommonsReloadCommand(CommandSender sender, String[] args) {

	}
}
