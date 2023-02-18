package util.request;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestUtilsTest {
    @Test
    void parseQuery() {
        Map<String, String> parseQuery = HttpRequestUtils.parseQuery("userId=javajigi&password=password&name=JaeSung");

        assertEquals(parseQuery.get("userId"),"javajigi");
        assertEquals(parseQuery.get("password"),"password");
        assertEquals(parseQuery.get("name"),"JaeSung");
    }

}