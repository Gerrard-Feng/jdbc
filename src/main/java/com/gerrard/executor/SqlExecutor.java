package com.gerrard.executor;

import java.util.List;

public interface SqlExecutor<T> {

    int executeUpdate(String sql);

    List<T> executeQuery(String sql);
}
