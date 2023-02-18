package login.controller;

import login.dto.LogInDTO;
import service.UserService;
import signup.constants.UserQueryKey;
import util.HttpHeader;
import util.request.HttpRequest;
import util.response.HttpResponse;
import util.response.HttpStatus;
import webserver.RequestURL;

import java.util.Map;

import static util.request.HttpRequestUtils.parseQuery;

public class LogInController {
    UserService userService = UserService.getInstance();

    public void login(HttpRequest httpRequest, HttpResponse httpResponse) {
        LogInDTO logInDTO = queryToDTO(parseQuery(httpRequest.getBody()));
        if(userService.isUser(logInDTO)) {
            httpResponse.put(HttpHeader.SET_COOKIE, "logined=true");
            httpResponse.setHttpStatus(HttpStatus.REDIRECT);
            httpResponse.setStatusCode("302");
            httpResponse.put(HttpHeader.LOCATION, RequestURL.INDEX.getUrl());
            return;
        }
        httpResponse.put(HttpHeader.SET_COOKIE, "logined=false");
        httpResponse.setHttpStatus(HttpStatus.REDIRECT);
        httpResponse.setStatusCode("302");
        httpResponse.put(HttpHeader.LOCATION, RequestURL.LOGIN_FAILED.getUrl());
    }

    private LogInDTO queryToDTO(Map<String ,String> query) {
        return new LogInDTO(query.get(UserQueryKey.ID.getKey()), query.get(UserQueryKey.PASSWORD.getKey()));
    }
}
