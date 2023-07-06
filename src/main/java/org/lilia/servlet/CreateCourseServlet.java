package org.lilia.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.ApplicationContext;
import org.lilia.service.CourseService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/courses/create", initParams = @WebInitParam(name = "name", value = "-1"))

public class CreateCourseServlet extends HttpServlet {
    private final CourseService courseService = ApplicationContext.get(CourseService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // "/" is relative to the context root (your web-app name)
        RequestDispatcher view = req.getRequestDispatcher("/coursecreate.html");
        // don't add your web-app name to the path

        view.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            String name = request.getParameter("name");
//            String lectureId = request.getParameter("lectureid");
            courseService.createNewCourse(name);

            response.sendRedirect("/coursecreate.html");
            writer.println("<p>Name: " + name + "</p>");
//            writer.println("<p>lectureid: " + lectureId + "</p>");
//                writer.println("<p>deadline: " + deadline + "</p>");
        }
    }
}
