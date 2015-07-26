package com.caved_in.commons.config.adapters;

import com.caved_in.commons.config.PremiumConfiguration;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PremiumConfigurationAdapter implements JsonSerializer<PremiumConfiguration>, JsonDeserializer<PremiumConfiguration> {
    @Override
    public PremiumConfiguration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();

        PremiumConfiguration config = new PremiumConfiguration();

        config.setKickMessage(object.get("premium-kick-message").getAsString());
        config.setPremiumMode(object.get("premium-only-mode").getAsBoolean());
        return config;
    }

    @Override
    public JsonElement serialize(PremiumConfiguration premiumConfiguration, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("premium-kick-message", premiumConfiguration.getKickMessage());
        object.addProperty("premium-only-mode", premiumConfiguration.isPremiumMode());

        return object;
    }
}
