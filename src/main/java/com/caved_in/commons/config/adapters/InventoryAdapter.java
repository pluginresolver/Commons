package com.caved_in.commons.config.adapters;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.lang.reflect.Type;

public class InventoryAdapter implements JsonSerializer<Inventory>, JsonDeserializer<Inventory> {
    private GsonBuilder gsonBuilder = new GsonBuilder();

    public InventoryAdapter() {
        gsonBuilder.registerTypeAdapter(ItemStack.class, new ItemStackAdapter());
    }

    @Override
    public Inventory deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = gsonBuilder.create();

        JsonObject object = jsonElement.getAsJsonObject();

        InventoryType invType = InventoryType.valueOf(object.get("type").getAsString());

        int size = object.get("size").getAsInt();

        Inventory inv = Bukkit.createInventory(null, invType);

        if (invType == InventoryType.PLAYER) {
            PlayerInventory pInv = (PlayerInventory) inv;

            if (object.has("helmet")) {
                pInv.setHelmet(gson.fromJson(object.getAsJsonObject("helmet"), ItemStack.class));
            }

            if (object.has("chestplate")) {
                pInv.setChestplate(gson.fromJson(object.getAsJsonObject("chestplate"), ItemStack.class));
            }

            if (object.has("leggings")) {
                pInv.setLeggings(gson.fromJson(object.getAsJsonObject("leggings"), ItemStack.class));
            }

            if (object.has("boots")) {
                pInv.setBoots(gson.fromJson(object.getAsJsonObject("boots"), ItemStack.class));
            }
        }

        ItemStack[] contents = new ItemStack[size];

        for (int i = 0; i < size; i++) {
            String stackId = String.valueOf(i);

            JsonElement jItemStack = object.get(stackId);
            ItemStack item = gson.fromJson(jItemStack, ItemStack.class);

            if (item == null) {
                continue;
            }

            contents[i] = item;
        }

        inv.setContents(contents);
        return inv;
    }

    @Override
    public JsonElement serialize(Inventory inv, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        Gson gson = gsonBuilder.create();

        InventoryType invType = inv.getType();
        object.addProperty("type", invType.name());

        ItemStack[] items = inv.getContents();

        if (invType == InventoryType.PLAYER) {
            PlayerInventory pInv = (PlayerInventory) inv;

            object.addProperty("size", 36);

            ItemStack helmet = pInv.getHelmet();
            ItemStack chestplate = pInv.getChestplate();
            ItemStack leggings = pInv.getLeggings();
            ItemStack boots = pInv.getBoots();

            if (helmet != null) {
                object.add("helmet", gson.toJsonTree(helmet, ItemStack.class));
            }

            if (chestplate != null) {
                object.add("chestplate", gson.toJsonTree(chestplate, ItemStack.class));
            }

            if (leggings != null) {
                object.add("leggings", gson.toJsonTree(leggings, ItemStack.class));
            }

            if (boots != null) {
                object.add("boots", gson.toJsonTree(boots, ItemStack.class));
            }
        } else {
            object.addProperty("size", items.length);
        }

        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];

            if (item == null) {
                continue;
            }

            JsonElement itemElement = gson.toJsonTree(item, ItemStack.class);

            object.add(String.valueOf(i), itemElement);
        }


        return object;
    }
}
