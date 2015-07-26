package com.caved_in.commons.config.adapters;

import com.caved_in.commons.config.SqlConfiguration;
import com.google.gson.*;

import java.lang.reflect.Type;

public class SqlConfigurationAdapter implements JsonSerializer<SqlConfiguration>, JsonDeserializer<SqlConfiguration> {
    @Override
    public SqlConfiguration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();

        String host = object.get("mysql-host").getAsString();
        String port = object.get("mysql-port").getAsString();
        String database = object.get("mysql-database").getAsString();
        String username = object.get("mysql-username").getAsString();
        String password = object.get("mysql-password").getAsString();
        boolean trackOnlineStatus = object.get("track-online-player-status").getAsBoolean();

        SqlConfiguration config = new SqlConfiguration(host, port, database, username, password, trackOnlineStatus);

        return config;
    }

    @Override
    public JsonElement serialize(SqlConfiguration sqlConfiguration, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("mysql-host", sqlConfiguration.getHost());

        object.addProperty("mysql-port", sqlConfiguration.getPort());

        object.addProperty("mysql-database", sqlConfiguration.getDatabase());

        object.addProperty("mysql-username", sqlConfiguration.getUsername());

        object.addProperty("mysql-password", sqlConfiguration.getPassword());

        object.addProperty("track-online-player-status", sqlConfiguration.trackPlayerOnlineStatus());

        return object;
    }
}
