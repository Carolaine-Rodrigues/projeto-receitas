package com.receitas.convert;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ConvertMap {
    public static Map<String, String> convertDTOToMap(Object dto) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = dto.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(dto);
            map.put(field.getName(), value != null ? value.toString() : null);
        }

        return map;
    }


}