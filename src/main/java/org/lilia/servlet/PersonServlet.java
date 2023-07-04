package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.ApplicationContext;
import org.lilia.model.Person;
import org.lilia.model.Role;
import org.lilia.service.PersonService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/people", initParams = @WebInitParam(name = "courseid", value = "role"))
public class PersonServlet extends HttpServlet {
    private final PersonService personService = ApplicationContext.get(PersonService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseid");
        String role = req.getParameter("role").toUpperCase();

        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();

        List<Person> people = personService.outAllByCourseId(Integer.parseInt(courseId), Role.valueOf(role)).get();

        printWriter.write(people.toString());

        printWriter.close();
    }
}
