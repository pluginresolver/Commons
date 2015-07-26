package com.caved_in.commons.config.adapters;

import com.caved_in.commons.config.DebugConfig;
import com.google.gson.*;

import java.lang.reflect.Type;

public class DebugConfigAdapater implements JsonSerializer<DebugConfig>, JsonDeserializer<DebugConfig> {
    @Override
    public DebugConfig deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();

        DebugConfig config;

        boolean traceEvent = obj.get("stack-trace-event").getAsBoolean();
        boolean traceBook = obj.get("stack-trace-book").getAsBoolean();
        boolean traceChat = obj.get("stack-trace-chat").getAsBoolean();

        config = new DebugConfig(traceEvent, traceBook, traceChat);

        return config;
    }

    @Override
    public JsonElement serialize(DebugConfig debugConfig, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty("stack-trace-event", debugConfig.isStackTraceEvent());
        obj.addProperty("stack-trace-book", debugConfig.isStackTraceBooks());
        obj.addProperty("stack-trace-chat", debugConfig.isStackTraceChat());

        return obj;
    }
}
