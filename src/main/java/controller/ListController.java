package controller;

import service.UserService;
import util.HttpHeader;
import util.request.HttpRequest;
import util.response.HttpResponse;
import util.response.HttpStatus;
import webserver.RequestURL;

import java.io.IOException;

public class ListController implements Controller {
    UserService userService = UserService.getInstance();

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        String cookie = httpRequest.getHeaders().get(HttpHeader.COOKIE);

        if (httpRequest.isLogined()) {
            httpResponse.redirect(RequestURL.USER_LIST_HTML.getUrl());
            return;
        }
        httpResponse.redirect(RequestURL.LOGIN.getUrl());
    }
}
