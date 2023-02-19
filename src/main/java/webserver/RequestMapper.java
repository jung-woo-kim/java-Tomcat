package webserver;

import controller.*;
import util.request.HttpRequest;
import util.response.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final HttpRequest httpRequest;
    private final HttpResponse httpResponse;

    private final Map<String, Controller> controllers = new HashMap<>();
    private final Controller controller;

    public RequestMapper(HttpRequest httpRequest, HttpResponse httpResponse) {
        initControllers();
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
        controller = controllers.get(httpRequest.getUrl());
    }

    private void initControllers() {
        controllers.put(RequestURL.SIGNUP.getUrl(),new SignUpController());
        controllers.put(RequestURL.LOGIN_POST.getUrl(), new LogInController());
        controllers.put(RequestURL.USER_LIST.getUrl(), new ListController());
    }

    public void proceed() throws IOException {
        if (controller != null) {
            controller.service(httpRequest, httpResponse);
            return;
        }
        httpResponse.forward(httpRequest.getUrl());
    }

}
