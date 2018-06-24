package com.gerrard.orm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public interface ResultSetAdapter<T> {

    T transferEntity(ResultSet rs, ResultSetMetaData meta);
}
