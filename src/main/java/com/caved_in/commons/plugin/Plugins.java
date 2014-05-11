package com.caved_in.commons.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Plugins {
	private static PluginManager pluginManager = Bukkit.getPluginManager();

	public static boolean pluginExists(String pluginName) {
		return pluginManager.getPlugin(pluginName) != null;
	}


}
