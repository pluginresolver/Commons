package com.caved_in.commons.config.adapters;

import com.caved_in.commons.chat.Title;
import com.caved_in.commons.chat.TitleBuilder;
import com.google.gson.*;
import org.bukkit.ChatColor;

import java.lang.reflect.Type;

public class TitleAdapter implements JsonSerializer<Title>, JsonDeserializer<Title> {

    @Override
    public JsonElement serialize(Title title, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("title-text", title.getTitle());

        object.addProperty("subtitle-text", title.getSubtitle());

        object.addProperty("fade-in-time", title.getFadeInTime());

        object.addProperty("fade-out-time", title.getFadeOutTime());

        object.addProperty("time-in-ticks", title.isTicks());

        object.addProperty("title-color", title.getTitleColor().name());

        object.addProperty("subtitle-color", title.getSubtitleColor().name());

        return object;
    }

    @Override
    public Title deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();

        TitleBuilder builder = new TitleBuilder();

        builder.title(object.get("title-text").getAsString())
                .subtitle(object.get("subtitle-text").getAsString())
                .fadeIn(object.get("fade-in-time").getAsInt())
                .fadeOut(object.get("fade-out-time").getAsInt());

        if (object.has("time-in-ticks")) {
            if (object.get("time-in-ticks").getAsBoolean()) {
                builder.ticks();
            }
        }

        builder.titleColor(ChatColor.valueOf(object.get("title-color").getAsString()))
                .subtitleColor(ChatColor.valueOf(object.get("subtitle-color").getAsString()));

        return builder.build();
    }
}
