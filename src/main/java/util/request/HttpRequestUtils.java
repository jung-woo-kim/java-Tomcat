package util.request;

import java.util.List;

public class HttpRequestUtils {
//    public static Header parseHeader(String headerLine) {
//        String[] firstLine = split(headerLine, " ");
//        return new Header(firstLine[0], firstLine[1]);
//    }

    private static String[] split(String values,String separator) {
        return values.split(separator);
    }
}
