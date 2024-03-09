package org.example.dao;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ApplicationTest {
    // Не подключилось кактестовый класс. Узнать, как сделать тестовый класс, если нет директории "test'
    @Test
    public void testApplication() throws SQLException {
        try (var conn = DriverManager.getConnection("jdbc:h2:mem:test")) {

            var sql = "CREATE TABLE courses"
                    + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), description TEXT)";

            try (var statement = conn.createStatement()) {
                statement.execute(sql);
            }

            var dao = new CourseDAO(conn);

            var course1 = new Course("test course", "some description");
            dao.save(course1);
            assertThat(course1.getId()).isNotNull()
                    .isInstanceOf(Long.class);

            var id1 = course1.getId();
            var actual1 = dao.find(id1).get();
            assertThat(actual1.getName()).isEqualTo(course1.getName());
            assertThat(actual1.getDescription()).isEqualTo(course1.getDescription());

            var entities = dao.getEntities();

            assertThat(entities).hasSize(1);
            assertThat(entities.get(0)).isInstanceOf(Course.class);

            var course2 = new Course("another course", "another description");
            dao.save(course2);
            assertThat(course2.getId()).isNotNull().isInstanceOf(Long.class);

            var id2 = course2.getId();
            var actual2 = dao.find(id2).get();
            assertThat(actual2.getName()).isEqualTo(course2.getName());
            assertThat(actual2.getDescription()).isEqualTo(course2.getDescription());
            assertThat(dao.getEntities()).hasSize(2);
        }
    }
}
