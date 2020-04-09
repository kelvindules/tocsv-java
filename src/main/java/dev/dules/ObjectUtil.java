package dev.dules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectUtil {
    private ObjectUtil() {
    }

    private static final String SEPARATOR = ",";

    public static List<Field> getFields(final Object object) {
        return Arrays.asList(object.getClass().getDeclaredFields());
    }

    public static String buildHeader(final Object object) {
        final List<Field> fields = getFields(object);
        return fields.stream().map(Field::getName).collect(Collectors.joining(SEPARATOR));
    }

    public static String buildRows(final Object object) {
        final List<Field> fields = getFields(object);
        return fields.stream().map(f -> {
            try {
                return f.get(object).toString();
            } catch (IllegalArgumentException | IllegalAccessException e) {
                return null;
            }
        }).collect(Collectors.joining(SEPARATOR));
    }
}