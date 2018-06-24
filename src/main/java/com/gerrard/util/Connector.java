package com.gerrard.util;

import com.gerrard.constants.ErrorCode;
import com.gerrard.exception.JdbcSampleException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connector {

    private Connector() {

    }

    public static Connection getSqlConnection() {
        String url = SqlProperties.getInstance().getUrl();
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            String errorMessage = "Fail to get SQL connection with url " + url;
            throw new JdbcSampleException(ErrorCode.GET_CONNECTION_EXCEPTION, errorMessage);
        }
    }
}
