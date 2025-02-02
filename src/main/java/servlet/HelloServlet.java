package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello heroku サンプル 日本語を書いてみる".getBytes("UTF-8"));
        out.write("<hr />".getBytes("UTF-8"));
        out.write("<a href=\"/index.jsp\">JSPのURLに遷移</a>".getBytes("UTF-8"));
        out.flush();
        out.close();
    }
    
}
