package com.gerrard.constants;

public final class ErrorCode {

    public static final int LOAD_PROPERTIES_ERROR = 100;
    public static final int LOAD_DRIVER_EXCEPTION = 101;
    public static final int GET_CONNECTION_EXCEPTION = 102;

    public static final int EXECUTE_QUERY_FAILURE = 200;
    public static final int EXECUTE_UPDATE_FAILURE = 201;
    public static final int EXECUTE_LARGE_UPDATE_FAILURE = 202;
    public static final int CALL_PROCEDURE_FAILURE = 203;
    public static final int INVALID_SQL_KIND = 300;

    public static final int ORM_ERROR = 400;
    public static final int MISSING_COLUMN_ERROR = 401;

    private ErrorCode() {

    }
}
