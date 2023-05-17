package ar.com.stormex.app.library.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.*;

public enum GenderEnum {
    MALE, FEMALE, OTHER;

    private static Map<String, GenderEnum> namesMap = new LinkedHashMap<>(3);

    static {
        namesMap.put("MALE", MALE);
        namesMap.put("FEMALE", FEMALE);
        namesMap.put("OTHER", OTHER);
    }

    @JsonCreator
    public static GenderEnum fromValue(String value) {
        if (!namesMap.containsKey(value.toUpperCase()))
            return namesMap.get("OTHER");
        return namesMap.get(value.toUpperCase());
    }

    @JsonValue
    public String getValue() {
        return super.toString();
    }

    public static List<GenderEnum> getList() {
        Iterator<String> iterator = namesMap.keySet().iterator();
        List<GenderEnum> list = new ArrayList<GenderEnum>();
        for (int i = 0; i < 3; i++) {
            String key = iterator.next();
            list.add(namesMap.get(key));
        }
        return list;
    }
}
