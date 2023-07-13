package org.lilia.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebServlet(value = "/homework/create", initParams = @WebInitParam(name = "id", value = "-1"))

public class CreateHomeworkServlet extends HttpServlet {
    @Autowired
    private HomeworkService homeworkService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // "/" is relative to the context root (your web-app name)
        RequestDispatcher view = req.getRequestDispatcher("/homeworkcreate.html");
        // don't add your web-app name to the path

        view.forward(req, resp);

    }
}
