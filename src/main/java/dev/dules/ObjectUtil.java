package dev.dules;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObjectUtil {
    private ObjectUtil() {
    }

    private static final Logger logger = LogManager.getLogger();

    private static final List<Class<?>> supportedTypes = Arrays.asList(byte.class, char.class, short.class, int.class,
            Integer.class, long.class, float.class, double.class, boolean.class, String.class, Date.class);

    private static final String SEPARATOR = ",";

    public static String toCSV(Object object) {
        return String.format("%s%n%s", buildHeader(object), buildRows(object));
    }

    public static List<Field> getFields(final Object object) {
        return Arrays.asList(object.getClass().getDeclaredFields());
    }

    public static String buildHeader(final Object object) {
        return getFields(object).stream().map(Field::getName).collect(Collectors.joining(SEPARATOR));
    }

    public static String buildRows(final Object object) {
        return getFields(object).stream().map(f -> {
            try {
                f.setAccessible(true);
                return isTypeSupported(f.getType()) ? castToSupported(f.get(object)).toString() : "";
            } catch (final IllegalArgumentException | IllegalAccessException e) {
                logger.error(e.getMessage());
                return "";
            }
        }).collect(Collectors.joining(SEPARATOR));
    }

    public static boolean isTypeSupported(final Class<?> c) {
        return supportedTypes.contains(c);
    }

    public static Object castToSupported(final Object object) {
        return supportedTypes.stream().filter(t -> t.equals(object.getClass())).findFirst().orElse(String.class)
                .cast(object);
    }
}