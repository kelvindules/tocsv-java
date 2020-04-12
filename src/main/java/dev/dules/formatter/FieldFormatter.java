package dev.dules.formatter;

public interface FieldFormatter {
    public Class<?> getType();

    public String format(final Object object, final String pattern);
}