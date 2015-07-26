package com.caved_in.commons.config.adapters;

import com.caved_in.commons.world.Worlds;
import com.google.gson.*;
import org.bukkit.Location;

import java.lang.reflect.Type;

public class LocationAdapter implements JsonSerializer<Location>, JsonDeserializer<Location> {
    @Override
    public Location deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();

        Location location = new Location(
                Worlds.getWorld(object.get("world").getAsString()),
                object.get("x").getAsDouble(),
                object.get("y").getAsDouble(),
                object.get("z").getAsDouble(),
                object.get("pitch").getAsFloat(),
                object.get("yaw").getAsFloat()
        );

        return location;
    }

    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("x", location.getX());
        object.addProperty("y", location.getY());
        object.addProperty("z", location.getZ());
        object.addProperty("pitch", location.getPitch());
        object.addProperty("yaw", location.getYaw());
        object.addProperty("world", location.getWorld().getName());
        return object;
    }
}
