package com.caved_in.commons.config.adapters;

import com.caved_in.commons.config.*;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ConfigurationAdapter implements JsonSerializer<Configuration>, JsonDeserializer<Configuration> {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(SqlConfiguration.class, new SqlConfigurationAdapter())
            .registerTypeAdapter(CommandConfiguration.class, new CommandConfigurationAdapter())
            .registerTypeAdapter(PremiumConfiguration.class, new PremiumConfigurationAdapter())
            .registerTypeAdapter(WorldConfiguration.class, new WorldConfigAdapter())
            .registerTypeAdapter(MaintenanceConfiguration.class, new MaintenanceConfigurationAdapter())
            .registerTypeAdapter(DebugConfig.class, new DebugConfigAdapater())
            .registerTypeAdapter(WarpConfig.class, new WarpConfigAdapter())
            .create();

    @Override
    public Configuration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Configuration config = null;

        JsonObject obj = jsonElement.getAsJsonObject();
        CommandConfiguration cmdConfig = jsonDeserializationContext.deserialize(obj.getAsJsonObject("command-config"), CommandConfiguration.class);
        PremiumConfiguration premConfig = jsonDeserializationContext.deserialize(obj.getAsJsonObject("premium-config"), PremiumConfiguration.class);
        WorldConfiguration worldConfig = jsonDeserializationContext.deserialize(obj.getAsJsonObject("world-config"), WorldConfiguration.class);
        MaintenanceConfiguration maintenanceConfig = jsonDeserializationContext.deserialize(obj.getAsJsonObject("maintenance-config"), MaintenanceConfiguration.class);
        DebugConfig debugConfig = jsonDeserializationContext.deserialize(obj.getAsJsonObject("debug-config"), DebugConfig.class);
        WarpConfig warpConfig = jsonDeserializationContext.deserialize(obj.getAsJsonObject("warp-config"), WarpConfig.class);
        SqlConfiguration sqlConfig = jsonDeserializationContext.deserialize(obj.getAsJsonObject("sql-config"), SqlConfiguration.class);

        boolean mysqlBackend = obj.get("mysql-backend").getAsBoolean();
        boolean registerCommands = obj.get("register-commands").getAsBoolean();
        String serverName = obj.get("server-name").getAsString();

        config = new Configuration(worldConfig, sqlConfig, maintenanceConfig, serverName, premConfig, mysqlBackend, registerCommands, debugConfig, warpConfig, cmdConfig);

        return config;
    }

    @Override
    public JsonElement serialize(Configuration configuration, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("mysql-backend", configuration.hasSqlBackend());
        object.addProperty("register-commands", configuration.registerCommands());
        object.addProperty("server-name", configuration.getServerName());

        object.add("sql-config", gson.toJsonTree(configuration.getSqlConfig()));
        object.add("command-config", gson.toJsonTree(configuration.getCommandConfig(), CommandConfiguration.class));
        object.add("premium-config", gson.toJsonTree(configuration.getPremiumConfig(), PremiumConfiguration.class));
        object.add("world-config", gson.toJsonTree(configuration.getWorldConfig(), WorldConfiguration.class));
        object.add("maintenance-config", gson.toJsonTree(configuration.getMaintenanceConfig(), MaintenanceConfiguration.class));
        object.add("debug-config", gson.toJsonTree(configuration.getDebugConfig(), DebugConfig.class));
        object.add("warp-config", gson.toJsonTree(configuration.getWarpConfig(), WarpConfig.class));
        return object;
    }
}
