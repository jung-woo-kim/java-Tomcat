package webserver;

import signup.controller.SignUpController;
import signup.service.SignUpService;
import util.request.HttpMethod;
import util.request.HttpRequest;

public class RequestMapper {
    HttpRequest request;
    SignUpController signUpController;


    public RequestMapper(HttpRequest request) {
        this.request = request;
    }

    public String proceed() {
        if (request.getStartLine().getHttpMethod().equals(HttpMethod.GET)) {
            return doGET();
        }
        if (request.getStartLine().getHttpMethod().equals(HttpMethod.POST)) {
            return doPost();
        }
        return RequestURL.INDEX.getUrl();
    }

    private String doGET() {
        if (request.getUrl().equals("/") || request.getUrl().equals(RequestURL.INDEX.getUrl())) {
            return RequestURL.INDEX.getUrl();
        }
        if (request.getUrl().startsWith(RequestURL.SIGNUP.getUrl())) {
            signUpController = new SignUpController();
            return signUpController.signUp(request.getStartLine().getQuery());
        }
        if (request.getUrl().contains("html")) {
            return request.getUrl();
        }
        return RequestURL.INDEX.getUrl();
    }

    private String doPost() {
        if (request.getUrl().startsWith(RequestURL.SIGNUP.getUrl())) {
            signUpController = new SignUpController();
            return signUpController.signUp(request.getBody());
        }
        return RequestURL.INDEX.getUrl();
    }
}
