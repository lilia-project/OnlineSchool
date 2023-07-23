package org.lilia.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lilia.entity.AdditionalMaterial;
import org.lilia.service.AdditionalMaterialService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/additionalmaterial/one", initParams = @WebInitParam(name = "id", value = "-1"))

public class SingleAdMaterialServlet extends HttpServlet {
    @Autowired

    private AdditionalMaterialService materialService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/html");
        int materialId = Integer.parseInt(id);
        AdditionalMaterial requireById = materialService.getRequireById(materialId);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(requireById.toString());

        printWriter.close();
    }
}
