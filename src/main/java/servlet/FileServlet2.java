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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(
        name = "FileServlet2",
        urlPatterns = {"/file2/*"}
)
public class FileServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, String> env = System.getenv();

        // CloudCube設定の参照
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

        try {
            // 設定値取り出し
            Pattern p = Pattern.compile("^https://(.*)\\.s3\\.amazonaws\\.com/(.*)$");
            Matcher m = p.matcher(cloudcubeUrl);
            String bucket = "";
            String basePrefix = "";
            if (m.find()){
                bucket = m.group(1);
                basePrefix = m.group(2);
            }

            // リスト参照
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucket)
                    .prefix(basePrefix + req.getPathInfo())
                    .build();

            ListObjectsResponse res = s3Client.listObjects(listObjects);
            List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
                System.out.print("\n The name of the key is " + myValue.key());
                System.out.print("\n The owner is " + myValue.owner());
            }

            /*
            System.out.print("\n data get 0");

            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key("hxlzsmew3hjg/public/tree_sample.html")
                    .bucket("cloud-cube-us2")
                    .build();

            System.out.print("\n data get 1");

            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();

            System.out.print("\n data get 2");

            // Write the data to a local file.
            File myFile = new File("/tmp/test.txt");
            OutputStream os = new FileOutputStream(myFile);
            os.write(data);
            System.out.println("Successfully obtained bytes from an S3 object");
            os.close();


        } catch (IOException ex) {
            ex.printStackTrace();
             */
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }


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
