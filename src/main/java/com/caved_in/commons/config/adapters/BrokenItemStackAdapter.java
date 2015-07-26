package com.caved_in.commons.config.adapters;

import com.caved_in.commons.item.EnchantWrapper;
import com.caved_in.commons.item.ItemBuilder;
import com.caved_in.commons.item.Items;
import com.google.gson.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Deprecated
public class BrokenItemStackAdapter implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

    @Override
    public ItemStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();

        ItemBuilder builder = new ItemBuilder(Material.getMaterial(object.get("id").getAsInt()));

        builder.amount(object.get("amount").getAsInt())
                .name(object.get("name").getAsString());

        if (object.has("lore")) {
            JsonArray loreArray = object.getAsJsonArray("lore");
            List<String> lorelines = new ArrayList<>();

            for (JsonElement loreElement : loreArray) {
                lorelines.add(loreElement.getAsString());
            }

            builder.lore(lorelines);
        }

        builder.durability(object.get("data-value").getAsShort());

        if (object.has("enchantments")) {
            JsonArray enchantmentArray = object.getAsJsonArray("enchantments");

            Set<EnchantWrapper> enchantments = new HashSet<>();

            for (JsonElement element : enchantmentArray) {
                EnchantWrapper enchant = context.deserialize(element, EnchantWrapper.class);

                enchantments.add(enchant);
            }

            builder.enchantments(enchantments);
        }

        if (object.has("flags")) {
            JsonArray flagArray = object.getAsJsonArray("flags");

            for (JsonElement jsonFlag : flagArray) {
                ItemFlag flag = context.deserialize(jsonFlag, ItemFlag.class);

                builder.addFlags(flag);
            }
        }

        if (object.has("skull-owner")) {
            builder.skull(object.get("skull-owner").getAsString());
        }

        return builder.item();
    }

    @Override
    public JsonElement serialize(ItemStack itemStack, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("id", itemStack.getTypeId());
        object.addProperty("amount", itemStack.getAmount());

        object.addProperty("name", Items.hasName(itemStack) ? Items.getName(itemStack) : Items.getFormattedMaterialName(itemStack));

        if (Items.hasLore(itemStack)) {
            JsonArray loreArray = new JsonArray();

            for (String lore : Items.getLore(itemStack)) {
                loreArray.add(context.serialize(lore));
            }

            object.add("lore", loreArray);
        }

        if (Items.isArmor(itemStack) || Items.isWeapon(itemStack)) {
            object.addProperty("data-value", Items.getDataValue(itemStack));
        } else {
            object.addProperty("data-value", itemStack.getDurability());
        }

        if (Items.hasEnchantments(itemStack)) {
            JsonArray enchantArray = new JsonArray();

            for (EnchantWrapper enchantWrapper : Items.getEnchantments(itemStack)) {
                enchantArray.add(context.serialize(enchantWrapper));
            }

            object.add("enchantments", enchantArray);
        }

        if (Items.hasFlags(itemStack)) {
            Set<ItemFlag> itemFlags = Items.getFlags(itemStack);

            JsonArray flagArray = new JsonArray();
            for (ItemFlag flag : itemFlags) {
                flagArray.add(context.serialize(flag));
            }


            object.add("flags", flagArray);
        }

        if (Items.isPlayerSkull(itemStack)) {
            object.add("skull-owner", context.serialize(((SkullMeta) Items.getMetadata(itemStack)).getOwner()));
        }

        return object;
    }
}
