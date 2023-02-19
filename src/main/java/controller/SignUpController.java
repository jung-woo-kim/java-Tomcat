package controller;

import service.constants.UserQueryKey;
import controller.dto.SignUpDTO;
import service.UserService;
import util.HttpHeader;
import util.request.HttpRequest;
import util.response.HttpResponse;
import util.response.HttpStatus;
import webserver.RequestURL;

import java.io.IOException;
import java.util.Map;

import static util.request.HttpRequestUtils.parseQuery;

public class SignUpController extends AbstractController{
    UserService userService = UserService.getInstance();

    @Override
    void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        SignUpDTO signUpDTO = queryToDTO(httpRequest.getStartLine().getQuery());
        userService.signUP(signUpDTO);
        httpResponse.redirect(RequestURL.INDEX.getUrl());
    }

    @Override
    void doPost(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        SignUpDTO signUpDTO = queryToDTO(parseQuery(httpRequest.getBody()));
        userService.signUP(signUpDTO);
        httpResponse.redirect(RequestURL.INDEX.getUrl());
    }

    private SignUpDTO queryToDTO(Map<String ,String> query) {
        return new SignUpDTO(query.get(UserQueryKey.ID.getKey()), query.get(UserQueryKey.PASSWORD.getKey()), query.get(UserQueryKey.NAME.getKey()), query.get(UserQueryKey.EMAIL.getKey()));
    }
}
