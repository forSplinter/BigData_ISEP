package com.isep.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {

    private final Stack<Connection> pool = new Stack<>();
    private final int MAX_POOL_SIZE = 10;

    private final String url = "jdbc:postgresql://localhost:5432/lab_bigdata";
    private final String user = "postgres";
    private final String password = "postgres";

    public ConnectionPool() throws SQLException {        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            pool.push(createNewConnection());
        }
    }

    private Connection createNewConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public synchronized Connection getConnection() throws SQLException {
        if (pool.isEmpty()) {
            return createNewConnection(); // fallback
        }
        return pool.pop();
    }

    public synchronized void releaseConnection(Connection conn) {
        if (conn != null && pool.size() < MAX_POOL_SIZE) {
            pool.push(conn);
        }
    }
}
