package util.request;

import webserver.RequestURL;

import java.io.BufferedReader;
import java.io.IOException;

import static util.IOUtils.readData;

public class HttpRequest {

    private static final String CONTENT_LENGTH = "Content-Length";
    private final HttpRequestStartLine startLine;
    private final HttpHeaders headers;
    private final String body;

    private static final String webUrl = "./webapp";
    private static final String DEFAULT_URL = "/index.html";


    private HttpRequest(final HttpRequestStartLine startLine, final HttpHeaders headers, final String body) {
        this.startLine = startLine;
        this.headers = headers;
        this.body = body;
    }

    public static HttpRequest from(final BufferedReader bufferedReader) throws IOException {
        final String startLine = bufferedReader.readLine();
        if (startLine == null) {
            throw new IllegalArgumentException("request가 비어있습니다.");
        }

        final HttpRequestStartLine httpRequestStartLine = HttpRequestStartLine.from(startLine);
        final HttpHeaders headers = HttpHeaders.from(bufferedReader);
        final String body = readBody(bufferedReader, headers);

        return new HttpRequest(httpRequestStartLine, headers, body);
    }

    private static String readBody(final BufferedReader bufferedReader,
                                   final HttpHeaders headers) throws IOException {

        if (!headers.contains(CONTENT_LENGTH)) {
            return "";
        }

        final int contentLength = convertIntFromContentLength(headers.get(CONTENT_LENGTH));
        return readData(bufferedReader, contentLength);
    }

    private static int convertIntFromContentLength(final String contentLength) {
        return Integer.parseInt(contentLength);
    }

    public HttpRequestStartLine getStartLine() {
        return startLine;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getUrl() {
        if (startLine.getPath().equals("/")) {
            return RequestURL.INDEX.getUrl();
        }
        return startLine.getPath();
    }
}