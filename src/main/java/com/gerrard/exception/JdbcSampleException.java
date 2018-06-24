package com.gerrard.exception;

import lombok.Getter;

@Getter
public final class JdbcSampleException extends RuntimeException {

    private final int errorCode;

    public JdbcSampleException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
