package com.caved_in.commons.config.adapters;

import com.caved_in.commons.config.WorldConfiguration;
import com.google.gson.*;

import java.lang.reflect.Type;

public class WorldConfigAdapter implements JsonDeserializer<WorldConfiguration>, JsonSerializer<WorldConfiguration> {
    @Override
    public WorldConfiguration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        WorldConfiguration config = new WorldConfiguration();

        JsonObject o = jsonElement.getAsJsonObject();

        config.weatherDisabled(o.get("disable-weather").getAsBoolean());
        config.lightningDisabled(o.get("disable-lightning").getAsBoolean());
        config.thunderDisabled(o.get("disable-thunder").getAsBoolean());
        config.iceAccumulation(o.get("disable-ice-accumulation").getAsBoolean());
        config.snowAccumulationDisabled(o.get("disable-snow-accumulation").getAsBoolean());
        config.myceliumSpreadDisabled(o.get("disable-mycelium-spread").getAsBoolean());
        config.fireSpreadDisabled(o.get("disable-fire-spread").getAsBoolean());
        config.launchpadPressurePlates(o.get("launchpad-pressure-plates").getAsBoolean());
        config.enableJoinMessages(o.get("enable-join-messages").getAsBoolean());
        config.enableLeaveMessages(o.get("enable-leave-messages").getAsBoolean());
        config.enableBlockBreak(o.get("enable-block-break").getAsBoolean());
        config.enableItemPickup(o.get("enable-item-pickup").getAsBoolean());
        config.enableItemDrop(o.get("enable-item-drop").getAsBoolean());
        config.enableFoodChange(o.get("enable-food-change").getAsBoolean());
        config.externalChatHandler(o.get("external-chat-plugin").getAsBoolean());
        config.explosionFireworks(o.get("explosion-fireworks").getAsBoolean());
        config.fallDamage(o.get("enable-fall-damage").getAsBoolean());

        return config;
    }

    @Override
    public JsonElement serialize(WorldConfiguration worldConfiguration, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("disable-weather", worldConfiguration.isWeatherDisabled());

        object.addProperty("disable-lightning", worldConfiguration.isLightningDisabled());

        object.addProperty("disable-thunder", worldConfiguration.isThunderDisabled());

        object.addProperty("disable-ice-accumulation", worldConfiguration.isIceSpreadDisabled());

        object.addProperty("disable-snow-accumulation", worldConfiguration.isSnowSpreadDisabled());

        object.addProperty("disable-mycelium-spread", worldConfiguration.isMyceliumSpreadDisabled());

        object.addProperty("disable-fire-spread", worldConfiguration.isFireSpreadDisabled());

        object.addProperty("launchpad-pressure-plates", worldConfiguration.hasLaunchpadPressurePlates());

        object.addProperty("enable-join-messages", worldConfiguration.hasJoinMessages());

        object.addProperty("enable-leave-messages", worldConfiguration.hasLeaveMessages());

        object.addProperty("enable-block-break", worldConfiguration.isBlockBreakEnabled());

        object.addProperty("enable-item-pickup", worldConfiguration.isItemPickupEnabled());

        object.addProperty("enable-item-drop", worldConfiguration.isItemDropEnabled());

        object.addProperty("enable-food-change", worldConfiguration.isFoodChangeEnabled());

        object.addProperty("external-chat-plugin", worldConfiguration.hasExternalChatHandler());

        object.addProperty("explosion-fireworks", worldConfiguration.hasExplosionFireworks());

        object.addProperty("enable-fall-damage", worldConfiguration.hasFallDamage());
        return object;
    }
}
