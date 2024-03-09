package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Exercise4 {
    public static void exercise4() throws SQLException {
        List<String> products = List.of("computer", "mobile phone", "tv", "kettle");
        try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet")) {
            var sql = "CREATE TABLE products "
                    + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))";

            try (var statement = conn.createStatement()) {
                statement.execute(sql);
            }
            // BEGIN (write your solution here)
            for (String s: products) {
                var sql2 = "INSERT INTO products (name) VALUES (?)";
                try (var preparedStatement = conn.prepareStatement(sql2)) {
                    preparedStatement.setString(1, s);
                    preparedStatement.executeUpdate();
                }
            }
            // END
            var sql3 = "SELECT * FROM products ORDER BY name";
            try (var statement3 = conn.createStatement()) {
                var resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name"));
                }
            }
        }
    }
}
