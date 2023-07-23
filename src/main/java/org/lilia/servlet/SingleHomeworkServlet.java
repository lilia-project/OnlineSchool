package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.entity.Homework;
import org.lilia.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/homework/one", initParams = @WebInitParam(name = "id", value = "-1"))

public class SingleHomeworkServlet extends HttpServlet {
    @Autowired
    private HomeworkService homeworkService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/html");
        int homeworkId = Integer.parseInt(id);
        Homework requireById = homeworkService.getRequireById(homeworkId);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(requireById.toString());

        printWriter.close();
    }
}
