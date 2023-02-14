package util;

import java.util.Arrays;
import java.util.List;

public class HttpRequestStartLine {

    private static final int START_LINE_MIN_LENGTH = 3;
    private static final String DISCRIMINATOR = " ";
    private final HttpMethod httpMethod;
    private final String path;
    private final String version;

    public HttpRequestStartLine(HttpMethod httpMethod, String path, String version) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.version = version;
    }

    public static HttpRequestStartLine from(String startLine) {
        return parse(startLine);
    }

    private static HttpRequestStartLine parse(String startLine) {
        List<String> startLineAttributes = Arrays.asList(startLine.split(DISCRIMINATOR));

        validateStartLineLength(startLineAttributes);

        HttpMethod httpMethod = HttpMethod.getMethod(startLineAttributes.get(0));
        String path = startLineAttributes.get(1);
        String version = startLineAttributes.get(2);

        return new HttpRequestStartLine(httpMethod, path, version);
    }

    private static void validateStartLineLength(final List<String> startLineInfos) {
        if (startLineInfos.size() < START_LINE_MIN_LENGTH) {
            throw new IllegalArgumentException("요청 정보가 잘못되었습니다.");
        }
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }
}
