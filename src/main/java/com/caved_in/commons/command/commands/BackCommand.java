package com.caved_in.commons.command.commands;

import com.caved_in.commons.command.Command;
import com.caved_in.commons.player.PlayerWrapper;
import com.caved_in.commons.player.Players;
import org.bukkit.entity.Player;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 30/01/14
 * Time: 11:30 PM
 */
public class BackCommand {
	@Command(name = "back", permission = "tunnels.common.back")
	public void onBackCommand(Player player, String[] args) {
		PlayerWrapper playerWrapper = Players.getData(player);
		Players.teleport(player, playerWrapper.getPreTeleportLocation());
	}
}