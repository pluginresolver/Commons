package com.caved_in.commons.config.adapters;

import com.caved_in.commons.config.WarpConfig;
import com.google.gson.*;

import java.lang.reflect.Type;

public class WarpConfigAdapter implements JsonSerializer<WarpConfig>, JsonDeserializer<WarpConfig> {
    @Override
    public WarpConfig deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        WarpConfig config = new WarpConfig();

        JsonObject object = jsonElement.getAsJsonObject();

        config.setWarpsMenuEnabled(object.get("enable-warps-menu").getAsBoolean());

        return config;
    }

    @Override
    public JsonElement serialize(WarpConfig warpConfig, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("enable-warps-menu", warpConfig.isWarpsMenuEnabled());

        return object;
    }
}
