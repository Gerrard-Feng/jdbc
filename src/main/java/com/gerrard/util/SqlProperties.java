package com.gerrard.util;

import com.gerrard.constants.ErrorCode;
import com.gerrard.exception.JdbcSampleException;
import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

@Getter
public final class SqlProperties {

    private static final String PROPERTIES_PATH = new File("").getAbsolutePath().replace("\\", "/") + "/src/main/resources/sql.properties";
    private static final String DB_ROOT_URL = new File("").getAbsolutePath().replace("\\", "/") + "/src/main/resources/db/";
    private static SqlProperties sqlProps;

    private static final String DB_TEST = "test.db";

    private String driver;
    private String url;

    private SqlProperties() {

    }

    public static final SqlProperties getInstance() {
        if (sqlProps == null) {
            try {
                Properties props = new Properties();
                props.load(new FileInputStream(PROPERTIES_PATH));
                transferProperties(props);
            } catch (IOException e) {
                String errorMessage = "Fail to load properties file";
                throw new JdbcSampleException(ErrorCode.LOAD_PROPERTIES_ERROR, errorMessage);
            }
        }
        return sqlProps;
    }

    private static void transferProperties(Properties props) {
        sqlProps = new SqlProperties();
        sqlProps.driver = props.getProperty("driver");
        sqlProps.url = props.getProperty("url");
        transferDbUrl();
    }

    private static void transferDbUrl() {
        String params[] = {DB_ROOT_URL + DB_TEST};
        sqlProps.url = MessageFormat.format(sqlProps.url, params);
    }
}
