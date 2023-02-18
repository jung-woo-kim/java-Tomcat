package util.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestStartLineTest {
    @Test
    void parseStartLineWithQuery() {
        HttpRequestStartLine startLine = HttpRequestStartLine.from("GET /user/create?userId=javajigi&password=password&name=JaeSung HTTP/1.1");

        assertEquals(HttpMethod.GET, startLine.getHttpMethod());
        assertEquals("/user/create", startLine.getPath());
        assertEquals("javajigi", startLine.getQuery().get("userId"));
        assertEquals("password", startLine.getQuery().get("password"));
        assertEquals("JaeSung", startLine.getQuery().get("name"));
    }

    @Test
    void parseStartLineWithoutQuery() {
        HttpRequestStartLine startLine = HttpRequestStartLine.from("POST /user/create HTTP/1.1");

        assertEquals(HttpMethod.POST, startLine.getHttpMethod());
        assertEquals("/user/create", startLine.getPath());
        assertTrue(startLine.getQuery().isEmpty());

    }
}