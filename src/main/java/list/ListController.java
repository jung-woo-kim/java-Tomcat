package list;

import service.UserService;
import util.HttpHeader;
import util.request.HttpRequest;
import util.response.HttpResponse;
import util.response.HttpStatus;
import webserver.RequestURL;

public class ListController {
    UserService userService = UserService.getInstance();
    public void list(HttpRequest httpRequest, HttpResponse httpResponse) {
        String cookie = httpRequest.getHeaders().get(HttpHeader.COOKIE);
        System.out.println(httpRequest.getHeaders());
        System.out.println(cookie);
        if (cookie != null && cookie.equals("logined=true")) {
            httpResponse.setHttpStatus(HttpStatus.REDIRECT);
            httpResponse.setStatusCode("302");
            httpResponse.put(HttpHeader.LOCATION, RequestURL.USER_LIST_HTML.getUrl());
            return;
        }

        httpResponse.setHttpStatus(HttpStatus.REDIRECT);
        httpResponse.setStatusCode("302");
        httpResponse.put(HttpHeader.LOCATION, RequestURL.LOGIN.getUrl());
    }
}
