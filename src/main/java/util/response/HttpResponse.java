package util.response;

import util.HttpHeader;
import util.request.HttpHeaders;
import webserver.RequestURL;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class HttpResponse {
    private HttpStatus httpStatus = HttpStatus.OK;
    private final String httpVersion = "HTTP/1.1";
    private String statusCode = "200";
    private HttpHeaders httpHeaders;
    private byte[] body;
    private OutputStream os;

    public HttpResponse(OutputStream outputStream) {
        this.os = outputStream;
        httpHeaders = new HttpHeaders(new HashMap<>());
        httpHeaders.put(HttpHeader.CONTENT_TYPE, "text/html;charset=utf-8");
    }

    public void put(HttpHeader key, String value) {
        httpHeaders.put(key, value);
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    private void setBody(String path) throws IOException {
        byte[] body = Files.readAllBytes(Paths.get(RequestURL.ROOT.getUrl() + path));
        put(HttpHeader.CONTENT_LENGTH, String.valueOf(body.length));
        this.body = body;
    }

    private void write() throws IOException {
        os.write((httpVersion +" "+statusCode+" "+httpStatus.getStatus()+"\r\n").getBytes());
        os.write(httpHeaders.toString().getBytes());
        os.write(body);
        os.flush();
        os.close();
    }

    public void forward(String path) throws IOException {
        setBody(path);
        if (isHtml(path)) {
            write();
            return;
        }
        put(HttpHeader.CONTENT_TYPE,"text/css");
        write();
    }


    private boolean isHtml(String path) {
        String[] paths = path.split("\\.");
        return paths[paths.length-1].equals("html");
    }
}
