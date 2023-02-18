package util.response;

import util.HttpHeader;
import util.request.HttpHeaders;

import java.util.HashMap;

public class HttpResponse {
    private HttpStatus httpStatus = HttpStatus.OK;
    private final String httpVersion = "HTTP/1.1";
    private String statusCode = "200";
    private HttpHeaders httpHeaders;
    private String body;

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

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {

        return httpHeaders.toString() + body;
    }
}
