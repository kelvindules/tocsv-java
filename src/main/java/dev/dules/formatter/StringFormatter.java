package dev.dules.formatter;

public class StringFormatter implements FieldFormatter {

    public Class<?> getType() {
        return String.class;
    }

    @Override
    public String format(Object object, String pattern) {
        return String.valueOf(object);
    }

}