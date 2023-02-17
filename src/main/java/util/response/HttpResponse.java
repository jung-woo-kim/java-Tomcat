package util.response;

import util.request.HttpHeaders;

import java.util.HashMap;

public class HttpResponse {
    private HttpStatus httpStatus = HttpStatus.OK;
    private final String httpVersion = "HTTP/1.1";
    private String statusCode = "200";
    private HttpHeaders httpHeaders;

    public HttpResponse() {
        httpHeaders = new HttpHeaders(new HashMap<>());
    }
}
