package dev.dules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.dules.exception.InvalidCSVSourceException;

public class EasyCSV {
    private static final Logger logger = LogManager.getLogger();

    public EasyCSV(Object source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        this.source = source;
        setDefaults();
    }

    private EasyCSV() {
        setDefaults();
    }

    public static EasyCSV builder() {
        return new EasyCSV();
    }

    private Object source;
    private String separator;
    private List<Field> fields;
    private String header;
    private String rows;

    private final List<Class<?>> supportedTypes = Arrays.asList(byte.class, char.class, short.class, int.class,
            Integer.class, long.class, float.class, double.class, boolean.class, String.class, Date.class);

    private void setDefaults() {
        this.separator = ",";
        this.header = "";
        this.rows = "";
        setFields();
    }

    public String build() {
        if (this.source == null) {
            throw new InvalidCSVSourceException();
        }
        this.buildHeader().buildRows();
        return String.format("%s%n%s", header, rows);
    }

    public EasyCSV setSource(final Object source) {
        this.source = source;
        setFields();
        return this;
    }

    private void setFields() {
        if(this.source != null) {
            this.fields = Arrays.asList(this.source.getClass().getDeclaredFields());
        }
    }
    public List<Field> getFields() {
        return fields;
    }

    public EasyCSV buildHeader() {
        this.header = getFields().stream().map(Field::getName).collect(Collectors.joining(separator));
        return this;
    }

    public EasyCSV buildRows() {
        this.rows = getFields().stream().map(f -> {
            try {
                f.setAccessible(true);
                return isTypeSupported(f.getType()) ? castToSupported(f.get(source)).toString() : "";
            } catch (final IllegalArgumentException | IllegalAccessException e) {
                logger.error(e.getMessage());
                return "";
            }
        }).collect(Collectors.joining(separator));
        return this;
    }

    public boolean isTypeSupported(final Class<?> c) {
        return supportedTypes.contains(c);
    }

    public Object castToSupported(final Object object) {
        // TODO: shouldn't switch to String.class on orElse
        return supportedTypes.stream().filter(t -> t.equals(object.getClass())).findFirst().orElse(String.class)
                .cast(object);
    }
}