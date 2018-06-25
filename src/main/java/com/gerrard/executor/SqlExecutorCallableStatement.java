package com.gerrard.executor;

import com.gerrard.constants.ErrorCode;
import com.gerrard.exception.JdbcSampleException;
import com.gerrard.util.Connector;
import com.gerrard.util.DriverLoader;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public final class SqlExecutorCallableStatement {

    /*
       create procedure add_pro(a int, b int, out sum int)
       begin
       set sum = a + b;
       end
     */
    public static void callProcedure() {
        DriverLoader.loadSqliteDriver();
        try (Connection conn = Connector.getSqlConnection();
             CallableStatement cstmt = conn.prepareCall("{call add_pro(?, ?)}")) {

            cstmt.setObject(1, "1");
            cstmt.setObject(2, "2");
            cstmt.registerOutParameter(3, Types.INTEGER);

            cstmt.execute();
            System.out.println("Result:" + cstmt.getObject(3));
        } catch (SQLException e) {
            throw new JdbcSampleException(ErrorCode.CALL_PROCEDURE_FAILURE, e.getMessage());
        }
    }

    public static void main(String[] args) {
        callProcedure();
    }
}
