package webserver;

import util.request.HttpRequest;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler implements Runnable{
    Socket connection;
    private static final Logger log = Logger.getLogger(RequestHandler.class.getName());
    private static final String webUrl = "./webapp";

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        log.log(Level.INFO, "New Client Connect! Connected IP : " + connection.getInetAddress() + ", Port : " + connection.getPort());
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()){
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            DataOutputStream dos = new DataOutputStream(out);

            HttpRequest httpRequest = HttpRequest.from(br);

            byte[] data = Files.readAllBytes(Paths.get(webUrl + httpRequest.getStartLine().getPath()));
            response200Header(dos,data.length);
            responseBody(dos,data);
        } catch (IOException e) {
            log.log(Level.SEVERE,e.getMessage());
        }
    }

    private String getUrl(BufferedReader br) {
//        List<String> requestLines = br.lines().collect(Collectors.toList());
//        return webUrl+HttpRequestUtils.parseHeader(requestLines).getUrl();
        return "";
    }

    private void response200Header(DataOutputStream dos, int lengthOfBody) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBody + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.log(Level.SEVERE,e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body);
            dos.flush();
            dos.close();
        } catch (IOException e) {
            log.log(Level.SEVERE,e.getMessage());
        }
    }
}
