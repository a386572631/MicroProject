package cn.jay.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MyUtils {

    /**
     * 对象转map
     */
    public static <T> Map<String, Object> objectToMap(T object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }
}
