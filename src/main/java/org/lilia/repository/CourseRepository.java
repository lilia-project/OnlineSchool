package org.lilia.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lilia.constant.Constants;
import org.lilia.entity.Course;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.lilia.util.HibernateUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CourseRepository extends ConnectionFactory {
    /*   public void select1() {
           SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
           Session session = sessionFactory.openSession();
           Query fromCourse = session.createQuery("from Course");
           System.out.println(fromCourse);
           session.close();
       }
   */
    public Boolean save(final Course course) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<Course> get(final Integer id) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final javax.persistence.Query usersQuery = session.createQuery("from Course where id =:id", Course.class);
            usersQuery.setParameter("id", id);
            final Course singleResult = (Course) usersQuery.getSingleResult();
            return Optional.of(singleResult);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<List<Course>> getAllCourses() {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Course> courseList = session.createQuery("from Course", Course.class).list();
            return Optional.ofNullable(courseList);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean update(final Course course) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean delete(final Course course) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void sortByName() {
        final String sql = "SELECT name FROM public.course ORDER BY name";
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);

            final List<String> names = new ArrayList<>();

            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                names.add(courseName);
            }
            names.forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    public void serializeCourses() {
        List<Course> courseList = getAllCourses().get();
        Serializer.serialize(courseList, FilePath.FILE_PATH_COURSE);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserializeCourses() {
        String filePath = FilePath.FILE_PATH_COURSE.getPath();
        Serializer.deserialize(filePath);
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);
    }

}


