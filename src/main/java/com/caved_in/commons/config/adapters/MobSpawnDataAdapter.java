package com.caved_in.commons.config.adapters;

import com.caved_in.commons.entity.MobSpawnData;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MobSpawnDataAdapter implements JsonSerializer<MobSpawnData>, JsonDeserializer<MobSpawnData> {
    @Override
    public MobSpawnData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(MobSpawnData mobSpawnData, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("mob-type", mobSpawnData.getEntityType().name());

        object.addProperty("health", mobSpawnData.getHealth());

        object.addProperty("max-health", mobSpawnData.getMaxHealth());

        object.addProperty("baby", mobSpawnData.isBaby());

        object.addProperty("villager", mobSpawnData.isVillager());

        object.addProperty("powered", mobSpawnData.isPowered());

        object.addProperty("skeleton-type", mobSpawnData.getSkeletonType().name());

        object.addProperty("name", mobSpawnData.getName());

        object.addProperty("age", mobSpawnData.getAge());

        object.addProperty("age-min", mobSpawnData.getAgeMin());

        object.addProperty("age-max", mobSpawnData.getAgeMax());

        object.addProperty("slime-size", mobSpawnData.getSize());

        object.addProperty("slime-min", mobSpawnData.getSizeMin());

        object.addProperty("slime-max", mobSpawnData.getSizeMax());


        return null;
    }
}
