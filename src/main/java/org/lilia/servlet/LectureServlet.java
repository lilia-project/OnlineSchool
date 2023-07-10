package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.ApplicationContextFile;
import org.lilia.model.Lecture;
import org.lilia.service.LectureService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/lectures")

public class LectureServlet extends HttpServlet {
    private final LectureService lectureService = ApplicationContextFile.get(LectureService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();
        List<Lecture> lectures = lectureService.outputAll().get();
        printWriter.write(lectures.toString());
        printWriter.close();
    }
}

