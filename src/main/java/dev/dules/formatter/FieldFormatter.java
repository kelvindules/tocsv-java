package dev.dules.formatter;

public interface FieldFormatter {
    public Class<?> getType();

    public String getDefaultPattern();

    public String format(final Object object, final String pattern);
}