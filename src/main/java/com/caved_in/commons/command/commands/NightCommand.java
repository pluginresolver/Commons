package com.caved_in.commons.command.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.player.Players;
import com.caved_in.commons.world.Worlds;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 30/01/14
 * Time: 7:11 PM
 */
public class NightCommand {
	@Command(name = "night", permission = "tunnels.common.time")
	public void onNightCommand(Player player, String[] args) {
		World world = player.getWorld();
		Worlds.setTimeNight(world);
		Players.sendMessage(player, Messages.timeUpdated(world.getName(), "night"));
	}
}
