package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.ApplicationContext;
import org.lilia.model.Lecture;
import org.lilia.service.LectureService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/lecture/one", initParams = @WebInitParam(name = "id", value = "-1"))
public class SingleLectureServlet extends HttpServlet {
    private final LectureService lectureService = ApplicationContext.get(LectureService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/html");
        int lectureId = Integer.parseInt(id);
        Lecture requireById = lectureService.getRequireById(lectureId);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(requireById.toString());

        printWriter.close();
    }
}
