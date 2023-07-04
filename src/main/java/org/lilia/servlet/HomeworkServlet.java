package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.ApplicationContext;
import org.lilia.model.Homework;
import org.lilia.service.HomeworkService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/homework", initParams = @WebInitParam(name = "id", value = "-1"))

public class HomeworkServlet extends HttpServlet {
    private final HomeworkService homeworkService = ApplicationContext.get(HomeworkService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();
        int lectureId = Integer.parseInt(id);
        List<Homework> homeworkList = homeworkService.findAllByLectureId(lectureId).get();
        printWriter.write(homeworkList.toString());
        printWriter.close();

    }
}
