package util.response;

import util.HttpHeader;
import util.request.HttpHeaders;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

public class HttpResponse {
    private HttpStatus httpStatus = HttpStatus.OK;
    private final String httpVersion = "HTTP/1.1";
    private String statusCode = "200";
    private HttpHeaders httpHeaders;
    private byte[] body;

    public HttpResponse() {
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

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void write(DataOutputStream dos) throws IOException {
        dos.writeBytes(httpVersion +" "+statusCode+" "+httpStatus.getStatus()+"\r\n");
        dos.writeBytes(httpHeaders.toString());
        dos.write(body);
    }
}
