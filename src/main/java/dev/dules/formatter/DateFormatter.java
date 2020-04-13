package dev.dules.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter implements FieldFormatter {
    @Override
    public Class<?> getType() {
        return Date.class;
    }

    @Override
    public String format(Object object, String pattern) {
        return new SimpleDateFormat(pattern).format(object);
    }

    @Override
    public String getDefaultPattern() {
        return "dd/MM/yyyy";
    }

}