package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.ApplicationContext;
import org.lilia.model.Course;
import org.lilia.service.CourseService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(value = "/courses", initParams = @WebInitParam(name = "id", value = "-1"))
public class CourseServlet extends HttpServlet {
    private final CourseService courseService = ApplicationContext.get(CourseService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();

        if (id == null) {
            List<Course> courses = courseService.outputAll();
            String collect = courses.stream()
                    .map(course -> "{" + course.toString() + "}" + "\n")
                    .collect(Collectors.joining("<br>"));

            printWriter.write(collect);
        } else {
            int courseId = Integer.parseInt(id);
            Course requireById = courseService.getRequireById(courseId);
            printWriter.write(requireById.toString());
        }
        printWriter.close();
    }

}
