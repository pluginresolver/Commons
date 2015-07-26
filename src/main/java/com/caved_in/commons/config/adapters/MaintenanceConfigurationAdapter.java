package com.caved_in.commons.config.adapters;

import com.caved_in.commons.config.MaintenanceConfiguration;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MaintenanceConfigurationAdapter implements JsonSerializer<MaintenanceConfiguration>, JsonDeserializer<MaintenanceConfiguration> {
    @Override
    public MaintenanceConfiguration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        MaintenanceConfiguration config = new MaintenanceConfiguration();

        JsonObject o = jsonElement.getAsJsonObject();

        config.setKickMessage(o.get("maintenance-kick-message").getAsString());
        config.setMotd(o.get("maintenance-motd").getAsString());

        return config;
    }

    @Override
    public JsonElement serialize(MaintenanceConfiguration maintenanceConfiguration, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("maintenance-motd", maintenanceConfiguration.getMotd());

        object.addProperty("maintenance-kick-message", maintenanceConfiguration.getKickMessage());

        return object;
    }
}
