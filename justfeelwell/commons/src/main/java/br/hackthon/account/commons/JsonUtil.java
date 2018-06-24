package br.hackthon.account.commons;

import com.google.gson.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class JsonUtil {

    private static JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer =  (json, type, context) ->
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(json.getAsString(), LocalDateTime::from);

    /**
     * Parse list of serializble to JSON
     *
     * @param serializable
     * @return
     */
    public static String getListAsJson(List<? extends Serializable> serializable) {
        if (serializable == null) return "[]";

        String array = serializable.stream().map(JsonUtil::getAsJson).collect(Collectors.joining(","));
        return "[" + array + "]";
    }

    /**
     * Get list of objets to a
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getListFromJson(String json, Class<T> clazz) {
        if (json == null || "[]".equals(json)) return new ArrayList<>();

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();

        List<T> returnList =  new ArrayList<>();
        for(final JsonElement jsonElements: array){
            T entity = JsonUtil.getBuilder().fromJson(jsonElements, clazz);
            returnList.add(entity);
        }
        return returnList;
    }

    public static String getAsJson(Serializable serializable) {
        if (serializable == null) return null;

        Gson gson = JsonUtil.getBuilder();
        return gson.toJson( serializable );
    }

    public static <T> T getAsObject(String json, Class<T> clazz) {

        Gson gson = JsonUtil.getBuilder();

        return gson.fromJson(json, clazz);
    }

    private static Gson getBuilder() {

        return new GsonBuilder().create();
    }
}
