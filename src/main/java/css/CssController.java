package css;

import util.HttpHeader;
import util.request.HttpRequest;
import util.response.HttpResponse;

public class CssController {
    public void css(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.put(HttpHeader.CONTENT_TYPE,"text/css");

    }
}
