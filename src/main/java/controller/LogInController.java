package controller;

import controller.dto.LogInDTO;
import service.UserService;
import service.constants.UserQueryKey;
import util.HttpHeader;
import util.request.HttpRequest;
import util.response.HttpResponse;
import util.response.HttpStatus;
import webserver.RequestURL;

import java.io.IOException;
import java.util.Map;

import static util.request.HttpRequestUtils.parseQuery;

public class LogInController implements Controller {
    UserService userService = UserService.getInstance();

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        LogInDTO logInDTO = queryToDTO(parseQuery(httpRequest.getBody()));
        if (userService.isUser(logInDTO)) {
            httpResponse.put(HttpHeader.SET_COOKIE, "logined=true");
            httpResponse.redirect(RequestURL.INDEX.getUrl());
            return;
        }
        httpResponse.put(HttpHeader.SET_COOKIE, "logined=false");
        httpResponse.redirect(RequestURL.LOGIN_FAILED.getUrl());
    }


    private LogInDTO queryToDTO(Map<String, String> query) {
        return new LogInDTO(query.get(UserQueryKey.ID.getKey()), query.get(UserQueryKey.PASSWORD.getKey()));
    }


}
