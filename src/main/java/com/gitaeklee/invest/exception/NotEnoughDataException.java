package com.gitaeklee.invest.exception;

public class NotEnoughDataException extends RuntimeException {
    public NotEnoughDataException() {
        super();
    }

    public NotEnoughDataException(String message) {
        super(message);
    }

    public NotEnoughDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughDataException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
