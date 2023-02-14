package util;

import org.junit.jupiter.api.Test;
import util.request.HttpRequestStartLine;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestStartLineTest {

    @Test
    void 정상_동작() {
        HttpRequestStartLine startLine = HttpRequestStartLine.from("GET ./index.html HTTP1.1");

        assertEquals("GET",startLine.getHttpMethod().getMethod());
        assertEquals("./index.html",startLine.getPath());
        assertEquals("HTTP1.1",startLine.getVersion());
    }

    @Test
    void 지원하지_않는_메소드() {
        assertThrows(IllegalArgumentException.class,()->HttpRequestStartLine.from("PATCH ./index.html HTTP1.1"));
    }

    @Test
    void 이상한_문장() {
        assertThrows(IllegalArgumentException.class,()->HttpRequestStartLine.from("안녕하세요"));
    }
}