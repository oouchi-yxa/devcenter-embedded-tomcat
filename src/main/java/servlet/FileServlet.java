package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "FileServlet", 
        urlPatterns = {"/file/*"}
    )
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

                    resp.setContentType("text/html;charset=UTF-8");
                    ServletOutputStream out = resp.getOutputStream();
                    out.write("<h1>ファイル サンプル</h1><br />\n".getBytes("UTF-8"));

                    String tmp = "";

                    req.setCharacterEncoding("UTF-8");
                    out.write("<br /><h2>parameter</h2><hr />".getBytes("UTF-8"));
                    Enumeration<String> parameterNames = req.getParameterNames();
                    while (parameterNames.hasMoreElements()) {
                                    tmp = parameterNames.nextElement();
                                    String[] paramVals = req.getParameterValues(tmp);
                                    if (paramVals != null) {
                                            for (String v : paramVals) {
                                                    String p = tmp + " : " + v + "<br />\n";
                                                    out.write(p.getBytes("UTF-8"));
                                            }
                                    }
                    }

        out.write("<hr />".getBytes("UTF-8"));
        out.flush();
        out.close();
    }
    
}
