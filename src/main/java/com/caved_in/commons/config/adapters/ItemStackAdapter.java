package com.caved_in.commons.config.adapters;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;
import java.util.Map;

public class ItemStackAdapter implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

    private Type serializeType;
    private Gson gson;

    public ItemStackAdapter() {
        serializeType = new TypeToken<Map<String, Object>>() {
        }.getType();
    }

    @Override
    public ItemStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ItemStack.deserialize((Map<String, Object>) gson.fromJson(jsonElement.toString(), serializeType));
    }

    @Override
    public JsonElement serialize(ItemStack itemStack, Type type, JsonSerializationContext jsonSerializationContext) {
        return gson.toJsonTree(itemStack.serialize(), serializeType);
    }
}
