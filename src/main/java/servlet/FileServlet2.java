package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(
        name = "FileServlet",
        urlPatterns = {"/file2/*"}
)
public class FileServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, String> env = System.getenv();

        String cloudcubeAccessKeyId = env.get("CLOUDCUBE_ACCESS_KEY_ID");
        String cloudcubeSecretAccessKey = env.get("CLOUDCUBE_SECRET_ACCESS_KEY");
        String cloudcubeUrl = env.get("CLOUDCUBE_URL");

        resp.setContentType("text/html;charset=UTF-8");

        ServletOutputStream out = resp.getOutputStream();
        out.write("<h1>ファイル サンプル</h1><br />\n".getBytes("UTF-8"));

        String tmp = "";

        req.setCharacterEncoding("UTF-8");

        tmp = req.getPathInfo();
        out.write("<br /><h2>pathInfo</h2><hr />".getBytes("UTF-8"));
        out.write(tmp.getBytes("UTF-8"));

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

        tmp = "" + cloudcubeAccessKeyId + ":" + cloudcubeUrl + ":" + cloudcubeSecretAccessKey;

        out.write(tmp.getBytes("UTF-8"));


        out.flush();
        out.close();
    }

}
