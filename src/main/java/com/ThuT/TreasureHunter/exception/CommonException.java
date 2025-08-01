package com.ThuT.TreasureHunter.exception;

public class CommonException extends RuntimeException {

    private final String errorCode;
    private final String message;

    public CommonException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
