package com.caved_in.commons.config.adapters;

import com.caved_in.commons.warp.Warp;
import com.google.gson.*;

import java.lang.reflect.Type;

public class WarpAdapter implements JsonSerializer<Warp>, JsonDeserializer<Warp> {
    @Override
    public Warp deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Warp warp;

        JsonObject object = jsonElement.getAsJsonObject();

        String name = object.get("name").getAsString();

        String worldName = object.get("world").getAsString();

        double x = object.get("x").getAsDouble();

        double y = object.get("y").getAsDouble();

        double z = object.get("z").getAsDouble();

        float pitch = object.get("pitch").getAsFloat();

        float yaw = object.get("yaw").getAsFloat();

        warp = new Warp(name, worldName, x, y, z, pitch, yaw);

        return warp;
    }

    @Override
    public JsonElement serialize(Warp warp, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("name", warp.getName());

        object.addProperty("world", warp.getLocation().getWorld().getName());

        object.addProperty("x", warp.getLocation().getX());
        object.addProperty("y", warp.getLocation().getY());
        object.addProperty("z", warp.getLocation().getZ());
        object.addProperty("pitch", warp.getLocation().getPitch());
        object.addProperty("yaw", warp.getLocation().getYaw());

        return object;
    }
}
