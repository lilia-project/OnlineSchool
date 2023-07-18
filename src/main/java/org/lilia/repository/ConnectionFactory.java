package org.lilia.repository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class ConnectionFactory {
    @Value("${database.connectionUrl}")
    private String url;
    @Value("${database.user}")
    private String username;
    @Value("${database.password}")
    private String password;

    @Value("${database.driver}")
    private String dataBaseDriver;

    @SneakyThrows
    public Connection createConnection() {

        Class.forName(dataBaseDriver).getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(url, username, password);
    }
}
