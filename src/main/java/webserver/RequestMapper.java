package webserver;

import signup.controller.SignUpController;
import signup.service.SignUpService;
import util.HttpHeader;
import util.request.HttpMethod;
import util.request.HttpRequest;
import util.response.HttpResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestMapper {
    private final HttpRequest request;
    private SignUpController signUpController;
    private HttpResponse httpResponse;

    public RequestMapper(HttpRequest request, HttpResponse httpResponse) {
        this.request = request;
        this.httpResponse = httpResponse;
    }

    public void proceed() {
        if (request.getStartLine().getHttpMethod().equals(HttpMethod.GET)) {
            doGET();
        }
        if (request.getStartLine().getHttpMethod().equals(HttpMethod.POST)) {
            doPost();
        }
    }

    private void doGET() {
        if (request.getUrl().equals("/") || request.getUrl().equals(RequestURL.INDEX.getUrl())) {
            try {
                byte[] body = Files.readAllBytes(Paths.get(RequestURL.ROOT.getUrl() + "/index.html"));
                httpResponse.put(HttpHeader.CONTENT_TYPE, String.valueOf(body.length));
                httpResponse.setBody(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (request.getUrl().startsWith(RequestURL.SIGNUP.getUrl())) {
            signUpController = new SignUpController();
            signUpController.signUp(request, httpResponse);
        }

        if (request.getUrl().contains("html")) {
            try {
                byte[] body = Files.readAllBytes(Paths.get(RequestURL.ROOT.getUrl() + request.getUrl()));
                httpResponse.put(HttpHeader.CONTENT_TYPE, String.valueOf(body.length));
                httpResponse.setBody(body);
            } catch (IOException e) {
                // 파일이 존재하지 않을때
                throw new RuntimeException(e);
            }
        }

    }

    private String doPost() {
        if (request.getUrl().startsWith(RequestURL.SIGNUP.getUrl())) {
            signUpController = new SignUpController();
            signUpController.signUp(request, httpResponse);
        }
        return RequestURL.INDEX.getUrl();
    }
}
