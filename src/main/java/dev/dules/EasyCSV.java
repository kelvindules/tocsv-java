package dev.dules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ClassUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.dules.exception.InvalidCSVSourceException;
import dev.dules.formatter.FieldFormatter;
import dev.dules.formatter.FormatterRegistry;

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
    // TODO: add something like a isBuilt check

    public EasyCSV setSource(final Object source) {
        this.source = source;
        setFields();
        return this;
    }

    private void setFields() {
        if (this.source != null) {
            this.fields = Arrays.asList(this.source.getClass().getDeclaredFields());
        }
    }

    public List<Field> getFields() {
        return fields;
    }

    public EasyCSV buildHeader() {
        this.header = getFields().stream().filter(f -> isTypeSupported(f.getType())).map(Field::getName)
                .collect(Collectors.joining(separator));
        return this;
    }

    public EasyCSV buildRows() {
        this.rows = getFields().stream().filter(f -> isTypeSupported(f.getType())).map(f -> {
            try {
                f.setAccessible(true);
                return getFormattedValue(f.get(source));
            } catch (final IllegalArgumentException | IllegalAccessException e) {
                logger.error(e.getMessage());
                return "";
            }
        }).collect(Collectors.joining(separator));
        return this;
    }

    public boolean isTypeSupported(final Class<?> c) {
        return ClassUtils.isPrimitiveOrWrapper(c) || FormatterRegistry.containsClassFormatter(c);
    }

    // do we need this?
    public Object castToSupported(final Object object) {
        if (ClassUtils.isPrimitiveOrWrapper(object.getClass()) && !ClassUtils.isPrimitiveWrapper(object.getClass())) {
            return ClassUtils.primitiveToWrapper(object.getClass()).cast(object);
        }
        final Class<?> type = extraSupportedTypes.stream().filter(t -> t.equals(object.getClass())).findFirst()
                .orElse(null);
        if (type != null) {
            return type.cast(object);
        } else {
            return new UnsupportedClassVersionError();
        }
    }

    public String getFormattedValue(final Object object) {
        final FieldFormatter customFormatter = FormatterRegistry.find(object.getClass());
        if (customFormatter != null) {
            return customFormatter.format(object, "dd/MM/yyyy");
        } else if (ClassUtils.isPrimitiveOrWrapper(object.getClass())) {
            return object.getClass().cast(object).toString();
        } else {
            return "";
        }
    }
}