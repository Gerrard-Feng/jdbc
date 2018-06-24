package com.gerrard.executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class SqlExecutorCallableStatementTest {

    private SqlExecutorCallableStatement executor = new SqlExecutorCallableStatement();

    @Test
    void test() {
        try {
            executor.callProcedure();
            Assertions.fail("");
        } catch (Exception e) {

        }
    }

}