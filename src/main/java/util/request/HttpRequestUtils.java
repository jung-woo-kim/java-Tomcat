package util.request;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequestUtils {
    public static Map<String ,String> parseQuery(String inputQuery) {
        String[] inputQueries = inputQuery.split("&");
        return Arrays.stream(inputQueries).map(query -> query.split("="))
                .collect(Collectors.toMap(queries -> queries[0],queries -> queries[1]));
    }
}
