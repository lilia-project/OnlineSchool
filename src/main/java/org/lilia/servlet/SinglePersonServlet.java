package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.entity.Person;
import org.lilia.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/person/one", initParams = @WebInitParam(name = "lastname", value = "-1"))
public class SinglePersonServlet extends HttpServlet {
    @Autowired
    private PersonService personService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastName = req.getParameter("lastname");
        resp.setContentType("text/html");
        Person person = personService.getByLastName(lastName);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(person.toString());

        printWriter.close();
    }

}

