package org.lilia.servlet;

import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(value = "/additionalmaterial", initParams = @WebInitParam(name = "lectureid", value = "-1"))

public class AdMaterialServlet extends HttpServlet {
  /*  @Autowired
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
    }*/
}
