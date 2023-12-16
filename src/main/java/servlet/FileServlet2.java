package servlet;


import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedDirectoryDownload;
import software.amazon.awssdk.transfer.s3.model.DirectoryDownload;
import software.amazon.awssdk.transfer.s3.model.DownloadDirectoryRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.core.ResponseBytes;

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
        name = "FileServlet2",
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

        System.setProperty("aws.accessKeyId", System.getenv("CLOUDCUBE_ACCESS_KEY_ID"));
        System.setProperty("aws.secretAccessKey", System.getenv("CLOUDCUBE_SECRET_ACCESS_KEY"));

        // 試しに組み込み
        S3Client s3Client =
                S3Client.builder()
                        .credentialsProvider(DefaultCredentialsProvider.create())
                        .region(Region.US_EAST_1)
                        .build();

        resp.setContentType("text/html;charset=UTF-8");

        ServletOutputStream out = resp.getOutputStream();
        out.write("<h1>ファイル サンプル</h1><br />\n".getBytes("UTF-8"));

        String tmp = "";

        req.setCharacterEncoding("UTF-8");

        tmp = req.getPathInfo();
        out.write("<br /><h2>pathInfo</h2><hr />".getBytes("UTF-8"));
        out.write(tmp.getBytes("UTF-8"));

        out.write("<hr />".getBytes("UTF-8"));

        tmp = "" + cloudcubeAccessKeyId + ":" + cloudcubeUrl + ":" + cloudcubeSecretAccessKey;

        out.write(tmp.getBytes("UTF-8"));


        out.flush();
        out.close();
    }

}
