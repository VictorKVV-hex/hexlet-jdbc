package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
//        Main.Lesson1Theory();
        Main.Lesson1();
    }
    public static void Lesson1() throws SQLException {
        var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet");

        var sql = "CREATE TABLE films"
                + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255), release_year INT, duration INT)";
        try (var statement = conn.createStatement()) {
            statement.executeUpdate(sql);
        }
        // BEGIN (write your solution here)
        var sql2 = "INSERT INTO films (title, release_year, duration) VALUES ('Godfather', 1972, 175), ('The Green Mile', 1999, 189)";
        try (var statement2 = conn.createStatement()) {
            statement2.executeUpdate(sql2);
        }

        var sql3 = "SELECT * FROM films";
        try (var statement3 = conn.createStatement()) {
            var resultSet = statement3.executeQuery(sql3);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("title") + " " + resultSet.getString("release_year") + " " + resultSet.getString("duration"));
            }
        }
        // END
        conn.close();
    }
    public static void Lesson1Theory() throws SQLException {
        // Создаем соединение с базой в памяти
        // База создается прямо во время выполнения этой строчки
        // Здесь hexlet_test — это имя базы данных
        var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test");

        var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
        // Чтобы выполнить запрос, создадим объект statement
        var statement = conn.createStatement();
        statement.execute(sql);
        statement.close(); // В конце закрываем

        var sql2 = "INSERT INTO users (username, phone) VALUES ('tommy', '123456789')";
        var statement2 = conn.createStatement();
        statement2.executeUpdate(sql2);
        statement2.close();

        var sql3 = "SELECT * FROM users";
        var statement3 = conn.createStatement();
        // Здесь вы видите указатель на набор данных в памяти СУБД
        var resultSet = statement3.executeQuery(sql3);
        // Набор данных — это итератор
        // Мы перемещаемся по нему с помощью next() и каждый раз получаем новые значения
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("phone"));
        }
        statement3.close();

        // Закрываем соединение
        conn.close();
    }
}