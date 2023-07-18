package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.model.AdditionalMaterial;
import org.lilia.service.AdditionalMaterialService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/additionalmaterial", initParams = @WebInitParam(name = "lectureid", value = "-1"))

public class AdMaterialServlet extends HttpServlet {
    @Autowired
    private AdditionalMaterialService materialService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("lectureid");

        resp.setContentType("text/html");

        PrintWriter printWriter = resp.getWriter();
        int lectureId = Integer.parseInt(id);
        List<AdditionalMaterial> materials = materialService.findAllByLectureId(lectureId);
        printWriter.write(materials.toString());
        printWriter.close();
    }
}
