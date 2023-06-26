package org.lilia.repository;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class AbstractRepository {
    @SneakyThrows
    public static Connection createConnection() {
        final String url = "jdbc:postgresql://localhost:5432/online_school";
        final String username = "postgres";
        final String password = "admin";
        Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(url, username, password);
    }
}
