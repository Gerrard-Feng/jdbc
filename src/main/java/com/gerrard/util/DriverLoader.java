package com.gerrard.util;

import com.gerrard.constants.ErrorCode;
import com.gerrard.exception.JdbcSampleException;

public final class DriverLoader {

    private DriverLoader() {

    }

    public static void loadSqliteDriver() {
        try {
            Class.forName(SqlProperties.getInstance().getDriver());
        } catch (ClassNotFoundException e) {
            throw new JdbcSampleException(ErrorCode.LOAD_DRIVER_EXCEPTION, "Fail to load sqlite driver");
        }
    }
}
