package org.lilia.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class DataBaseInitializer {
    private final ConnectionFactory connectionFactory;

    public void createTables() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = connectionFactory.createConnection();
            statement = connection.createStatement();

            final String sql = """
                    CREATE TABLE IF NOT EXISTS public.course
                    (
                        id serial NOT NULL,
                        name text NOT NULL,
                        PRIMARY KEY (id)
                    );
                      
                    CREATE TABLE IF NOT EXISTS public.person
                    (
                        id serial NOT NULL,
                        role text NOT NULL,
                        last_name text NOT NULL,
                        first_name text,
                        phone text,
                        email text,
                        course_id integer,
                        PRIMARY KEY (id),
                        CONSTRAINT f_course_id FOREIGN KEY (course_id)
                        REFERENCES public.course (id) MATCH SIMPLE
                    );

                    CREATE TABLE IF NOT EXISTS public.lecture
                      (
                       id serial NOT NULL,
                       create_at timestamp with time zone NOT NULL,
                       lecture_date timestamp with time zone NOT NULL,
                       name text NOT NULL,
                       course_id integer,
                       person_id integer,
                       description text,
                       PRIMARY KEY (id),
                       CONSTRAINT f_course_id FOREIGN KEY (course_id)
                      REFERENCES public.course (id) MATCH SIMPLE
                      );
                                        
                    CREATE TABLE IF NOT EXISTS public.homework
                      (
                       id serial NOT NULL,
                       task text NOT NULL,
                       lecture_id integer NOT NULL,
                       deadline time with time zone,
                       PRIMARY KEY (id),
                       CONSTRAINT f_lecture_id FOREIGN KEY (lecture_id)
                       REFERENCES public.lecture (id) MATCH SIMPLE
                      );

                    CREATE TABLE IF NOT EXISTS public.addition_material
                      (
                        id serial NOT NULL,
                        name text NOT NULL,
                        lecture_id integer NOT NULL,
                        resource_type text,
                        PRIMARY KEY (id),
                        CONSTRAINT lecture_id FOREIGN KEY (lecture_id)
                        REFERENCES public.lecture (id) MATCH SIMPLE
                      );

                    CREATE TABLE IF NOT EXISTS public.student_course
                      (
                        student_id integer NOT NULL,
                        course_id integer NOT NULL,
                        CONSTRAINT id_id PRIMARY KEY (student_id, course_id),
                        CONSTRAINT f_course_id FOREIGN KEY (course_id)
                        REFERENCES public.course (id) MATCH SIMPLE,
                        CONSTRAINT f_student_id FOREIGN KEY (student_id)
                        REFERENCES public.person (id) MATCH SIMPLE
                      ); """;

            statement.execute(sql);

        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        } finally {
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
                if (Objects.nonNull(statement)) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Connection failed..." + ex);
            }
        }
//        throw new IllegalArgumentException();
    }

    public void fillTables() {

        try (Connection connection = connectionFactory.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSE LIMIT 1");
            if (resultSet.next()) {
                return;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String fileContent;
        try {
            byte[] bytes = Files.readAllBytes(Path.of(DataBaseInitializer.class.getClassLoader().getResource("data.sql").getPath()));
            fileContent = new String(bytes);

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        try (Connection connection = connectionFactory.createConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(fileContent);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
