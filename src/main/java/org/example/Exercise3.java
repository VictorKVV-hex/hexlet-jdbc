package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Exercise3 {
    public static void exercise3() throws SQLException {
        var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet");

        var sql = "CREATE TABLE cars"
                + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, brand  VARCHAR(100), model  VARCHAR(100))";
        try (var statement = conn.createStatement()) {
            statement.executeUpdate(sql);
        }
        var sql2 = "INSERT INTO cars (brand, model) VALUES ('kia', 'sorento'), ('bmw', 'x5'), ('audi', 'q7')";
        try (var statement2 = conn.createStatement()) {
            statement2.executeUpdate(sql2);
        }
        var sql3 = "select * from cars order by brand";
        try (var statement3 = conn.createStatement()) {
            var resultSet = statement3.executeQuery(sql3);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("brand") + " " + resultSet.getString("model"));
            }
        }

    }
}
