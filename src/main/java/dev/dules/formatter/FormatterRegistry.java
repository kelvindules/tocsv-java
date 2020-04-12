package dev.dules.formatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

public class FormatterRegistry {
    private FormatterRegistry() {

    }

    static {
        formatters = new ArrayList<>();
        final ServiceLoader<FieldFormatter> formatterLoader = ServiceLoader.load(FieldFormatter.class);
        formatterLoader.forEach(FormatterRegistry::register);
    }

    private static List<FieldFormatter> formatters;

    private static void register(final FieldFormatter formatter) {
        formatters.add(formatter);
    }

    public static List<FieldFormatter> getAll() {
        return Collections.unmodifiableList(formatters);
    }

    public static FieldFormatter find(final Class<?> clazz) {
        return formatters.stream().filter(f -> f.getType().equals(clazz)).findFirst().orElse(null);
    }
}