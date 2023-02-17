package util.request;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class HttpHeaders {
    private Map<String, String> headers;

    private static final String DISCRIMINATOR = ":";

    private HttpHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    public static HttpHeaders from(final BufferedReader bufferedReader) throws IOException {
        return new HttpHeaders(readAllHeaders(bufferedReader));
    }

    private static Map<String, String> readAllHeaders(final BufferedReader bufferedReader) throws IOException {
        final Map<String, String> headers = new LinkedHashMap<>();

        while (true) {
            final String line = bufferedReader.readLine();
            if (line.equals("")) {
                break;
            }
            final List<String> header = parseHeader(line);
            final String headerType = header.get(0).trim();
            final String headerValue = header.get(1).trim();
            headers.put(headerType, headerValue);
        }

        return headers;
    }

    private static List<String> parseHeader(final String line) {
        final List<String> header = Arrays.asList(line.split(DISCRIMINATOR));
        validateHeader(header);
        return header;
    }

    private static void validateHeader(final List<String> header) {
        if (header.size() < 2) {
            throw new IllegalArgumentException("요청 정보가 잘못되었습니다.");
        }
    }

    public boolean contains(final String httpHeaderType) {
        return headers.containsKey(httpHeaderType);
    }

    public String get(final String httpHeaderType) {
        return headers.get(httpHeaderType);
    }

    public Set<String> keySet() {
        return headers.keySet();
    }

    public void put(String httpHeaderType, final String httpHeader) {
        headers.put(httpHeaderType, httpHeader);
    }

}