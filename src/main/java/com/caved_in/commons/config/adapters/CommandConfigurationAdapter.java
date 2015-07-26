package com.caved_in.commons.config.adapters;

import com.caved_in.commons.config.CommandConfiguration;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CommandConfigurationAdapter implements JsonSerializer<CommandConfiguration>, JsonDeserializer<CommandConfiguration> {
    @Override
    public CommandConfiguration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();

        boolean disableBukkitCommands = obj.get("disable-bukkit-commands").getAsBoolean();
        boolean disablePluginCommand = obj.get("disable-plugins-command").getAsBoolean();

        return new CommandConfiguration(disableBukkitCommands, disablePluginCommand);
    }

    @Override
    public JsonElement serialize(CommandConfiguration commandConfiguration, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("disable-bukkit-commands", commandConfiguration.disableBukkitCommands());
        object.addProperty("disable-plugins-command", commandConfiguration.disablePluginsCommand());
        return object;
    }
}
