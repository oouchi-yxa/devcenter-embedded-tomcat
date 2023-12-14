package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet3", 
        urlPatterns = {"/hello3"}
    )
public class HelloServlet3 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

                    //. BODYを全部取り出す
                    BufferedReader br = new BufferedReader(req.getReader());
                    String strRequest = "";
                    String tmp = br.readLine();
                    while (tmp != null) {
                            strRequest += tmp + "\n";
                            tmp = br.readLine();
                    }

                    resp.setContentType("text/html;charset=UTF-8");
                    ServletOutputStream out = resp.getOutputStream();
                    out.write("<h1>BODY出力 サンプル</h1><br />\n".getBytes("UTF-8"));
                    if (req.getContentType() != null) {
                            out.write(req.getContentType().getBytes("UTF-8"));
                    }
                    out.write("<hr />".getBytes("UTF-8"));
                    if (tmp != null) {
                            out.write(tmp.getBytes("UTF-8"));
                    }
                    out.write("<hr />".getBytes("UTF-8"));
                    out.flush();
                    out.close();
    }
    
}
