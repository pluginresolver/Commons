package com.caved_in.commons.config.adapters;

import com.caved_in.commons.inventory.ArmorInventory;
import com.caved_in.commons.inventory.ArmorSlot;
import com.google.gson.*;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;

public class ArmorInventoryAdapter implements JsonSerializer<ArmorInventory>, JsonDeserializer<ArmorInventory> {
    Gson gson = new GsonBuilder().registerTypeAdapter(ItemStack.class, new BrokenItemStackAdapter()).create();

    public ArmorInventory deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext deserializer) throws JsonParseException {
        ArmorInventory inv = new ArmorInventory();

        JsonObject object = jsonElement.getAsJsonObject();

        if (object.has("helmet")) {
            inv.setItem(ArmorSlot.HELMET, deserializer.deserialize(object.getAsJsonObject("helmet"), ItemStack.class));
        }

        if (object.has("chestplate")) {
            inv.setItem(ArmorSlot.CHEST, deserializer.deserialize(object.getAsJsonObject("chestplate"), ItemStack.class));
        }

        if (object.has("leggings")) {
            inv.setItem(ArmorSlot.LEGGINGS, deserializer.deserialize(object.getAsJsonObject("leggings"), ItemStack.class));
        }

        if (object.has("boots")) {
            inv.setItem(ArmorSlot.BOOTS, deserializer.deserialize(object.getAsJsonObject("boots"), ItemStack.class));
        }

        if (object.has("weapon")) {
            inv.setItem(ArmorSlot.WEAPON, deserializer.deserialize(object.getAsJsonObject("weapon"), ItemStack.class));
        }

        return inv;
    }

    @Override
    public JsonElement serialize(ArmorInventory armorInventory, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        ItemStack helmet = armorInventory.getHelmet();
        ItemStack chest = armorInventory.getHelmet();
        ItemStack leggings = armorInventory.getLegs();
        ItemStack boots = armorInventory.getBoots();
        ItemStack weapon = armorInventory.getWeapon();

        if (helmet != null) {
            object.add("helmet", gson.toJsonTree(helmet));
        }

        if (chest != null) {
            object.add("chestplate", gson.toJsonTree(chest));
        }

        if (leggings != null) {
            object.add("leggings", gson.toJsonTree(leggings));
        }

        if (boots != null) {
            object.add("boots", gson.toJsonTree(boots));
        }

        if (weapon != null) {
            object.add("weapon", gson.toJsonTree(weapon));
        }

        return object;
    }
}
