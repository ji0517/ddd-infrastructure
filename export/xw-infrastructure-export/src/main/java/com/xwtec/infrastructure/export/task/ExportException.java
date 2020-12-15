package com.xwtec.infrastructure.export.task;

public class ExportException extends RuntimeException {

    public ExportException() {
        super();
    }

    public ExportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExportException(String message) {
        super(message);
    }

    public ExportException(Throwable cause) {
        super(cause);
    }
}
