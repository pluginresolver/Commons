package com.caved_in.commons.config.adapters;

import com.google.gson.*;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Type;

public class PotionEffectAdapter implements JsonSerializer<PotionEffect>, JsonDeserializer<PotionEffect> {
    @Override
    public PotionEffect deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(PotionEffect potionEffect, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
