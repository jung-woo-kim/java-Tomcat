package util.request;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestTest {
    private final String testDirectory = "./src/test/resources/";
    private final String getPath = "HttpGetWithQuery.txt";
    private final String postPath = "HttpPostWithQuery.txt";

    @Test
    void HTTP_GET_Query() throws IOException {
        HttpRequest httpRequest = HttpRequest.from(bufferedReaderFromFile(testDirectory + getPath));

        assertEquals(httpRequest.getBody(),"");
        assertEquals(httpRequest.getUrl(),"/user/create");
        assertEquals(httpRequest.getStartLine().getHttpMethod(),HttpMethod.GET);
        assertEquals(httpRequest.getStartLine().getQuery().get("userId"),"javajigi");
        assertEquals(httpRequest.getStartLine().getQuery().get("password"),"password");
        assertEquals(httpRequest.getStartLine().getQuery().get("name"),"JaeSung");
        assertEquals(httpRequest.getStartLine().getVersion(),"HTTP/1.1");
    }

    @Test
    void HTTP_POST_Query() throws IOException {
        HttpRequest httpRequest = HttpRequest.from(bufferedReaderFromFile(testDirectory + postPath));

        assertEquals(httpRequest.getBody(),"userId=javajigi&password=password&name=JaeSung");
        assertEquals(httpRequest.getUrl(),"/user/create");
        assertEquals(httpRequest.getStartLine().getHttpMethod(),HttpMethod.POST);
        assertEquals(httpRequest.getStartLine().getVersion(),"HTTP/1.1");
    }

    private BufferedReader bufferedReaderFromFile(String path) throws IOException {
        return new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path))));
    }
}