package signup.controller;

import signup.constants.UserQueryKey;
import signup.dto.SignUpDTO;
import service.UserService;
import util.HttpHeader;
import util.request.HttpRequest;
import util.response.HttpResponse;
import util.response.HttpStatus;
import webserver.RequestURL;

import java.util.Map;

import static util.request.HttpRequestUtils.parseQuery;

public class SignUpController {
    UserService userService = UserService.getInstance();
    public void signUp(HttpRequest httpRequest, HttpResponse httpResponse) {
        SignUpDTO signUpDTO = queryToDTO(parseQuery(httpRequest.getBody()));
        userService.signUP(signUpDTO);

        httpResponse.setHttpStatus(HttpStatus.REDIRECT);
        httpResponse.setStatusCode("302");
        httpResponse.put(HttpHeader.LOCATION, RequestURL.INDEX.getUrl());

    }

    private SignUpDTO queryToDTO(Map<String ,String> query) {
        return new SignUpDTO(query.get(UserQueryKey.ID.getKey()), query.get(UserQueryKey.PASSWORD.getKey()), query.get(UserQueryKey.NAME.getKey()), query.get(UserQueryKey.EMAIL.getKey()));
    }
}
