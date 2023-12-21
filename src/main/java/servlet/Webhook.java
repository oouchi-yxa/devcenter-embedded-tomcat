package servlet;

import software.amazon.awssdk.http.HttpStatusCode;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(
        name = "Webhook",
        urlPatterns = {"/webhook"}
)
public class Webhook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //. BODYを全部取り出す
        req.setCharacterEncoding("UTF-8");
        BufferedReader br = new BufferedReader(req.getReader());
        String strRequest = "";
        String tmp = br.readLine();
        while (tmp != null) {
            strRequest += tmp + "\n";
            tmp = br.readLine();
        }

        System.out.print("<h1>Webhook サンプル</h1><br />\n");
        if (req.getContentType() != null) {
            System.out.print(req.getContentType());
        }

        System.out.print("<br /><h2>header</h2><hr />\n");
        Enumeration<String> headerNames = req.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                tmp = headerNames.nextElement();
                Enumeration<String> headerVals = req.getHeaders(tmp);
                if (headerVals != null) {
                    while (headerVals.hasMoreElements()) {
                        String p = tmp + " : " + headerVals.nextElement() + "<br />\n";
                        System.out.print(p);
                    }
                }
            }
        }

        System.out.print("<br /><h2>body</h2><hr />\n");
        if (strRequest != null) {
            System.out.print(strRequest);
        }
        System.out.print("<hr />\n");

        resp.setStatus(HttpStatusCode.NO_CONTENT);
    }

}
