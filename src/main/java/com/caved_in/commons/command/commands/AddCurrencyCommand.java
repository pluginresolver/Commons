package com.caved_in.commons.command.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.player.MinecraftPlayer;
import com.caved_in.commons.player.Players;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddCurrencyCommand {
	@Command(name = "addcurrency", usage = "/addcurrency <Player> <Amount>", permission = "commons.command.addcurrency")
	public void addCurrencyCommand(CommandSender sender, String[] args) {
		if (args.length < 2) {
			Players.sendMessage(sender, Messages.invalidCommandUsage("Player", "Amount"));
			return;
		}

		String playerName = args[0];
		String currencyAmount = args[1];
		int amount = 0;
		if (!Players.isOnline(playerName)) {
			Players.sendMessage(sender, Messages.playerOffline(playerName));
			return;
		}

		if (!StringUtils.isNumeric(currencyAmount)) {
			Players.sendMessage(sender, Messages.invalidCommandUsage("player", "amount (number)"));
			return;
		}

		Player player = Players.getPlayer(playerName);
		MinecraftPlayer minecraftPlayer = Players.getData(player);
		amount = NumberUtils.toInt(currencyAmount);
		minecraftPlayer.addCurrency(amount);
		Players.updateData(minecraftPlayer);

		Players.sendMessage(sender, Messages.playerAddedXp(playerName, amount));
	}
}
