package dev.dules.formatter;

public class StringFormatter implements FieldFormatter {
    @Override
    public Class<?> getType() {
        return String.class;
    }

    @Override
    public String format(Object object, String pattern) {
        return String.valueOf(object);
    }

    @Override
    public String getDefaultPattern() {
        return "";
    }

}