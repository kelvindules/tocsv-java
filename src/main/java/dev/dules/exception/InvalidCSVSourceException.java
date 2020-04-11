package dev.dules.exception;

public class InvalidCSVSourceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidCSVSourceException() {
        // thrown when EasyCSV's source attribute is null
    }
}