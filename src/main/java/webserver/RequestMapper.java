package webserver;

import list.ListController;
import login.controller.LogInController;
import signup.controller.SignUpController;
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
    private LogInController logInController;
    private ListController listController;
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
            return;
        }
        if (request.getUrl().startsWith(RequestURL.SIGNUP.getUrl())) {
            signUpController = new SignUpController();
            signUpController.signUp(request, httpResponse);
            return;
        }
        if (request.getUrl().equals(RequestURL.USER_LIST.getUrl())) {
            listController = new ListController();
            listController.list(request, httpResponse);
        }

        if (request.getUrl().contains("css")) {
            httpResponse.put(HttpHeader.CONTENT_TYPE,"text/css");
            try {
                byte[] body = Files.readAllBytes(Paths.get(RequestURL.ROOT.getUrl() + request.getUrl()));
                httpResponse.put(HttpHeader.CONTENT_LENGTH, String.valueOf(body.length));
                httpResponse.setBody(body);
            } catch (IOException e) {
                // 파일이 존재하지 않을때
                throw new RuntimeException(e);
            }
        }

        if (request.getUrl().contains("html")) {
            try {
                byte[] body = Files.readAllBytes(Paths.get(RequestURL.ROOT.getUrl() + request.getUrl()));
                httpResponse.put(HttpHeader.CONTENT_LENGTH, String.valueOf(body.length));
                httpResponse.setBody(body);
            } catch (IOException e) {
                // 파일이 존재하지 않을때
                throw new RuntimeException(e);
            }
        }

    }

    private void doPost() {
        if (request.getUrl().startsWith(RequestURL.SIGNUP.getUrl())) {
            signUpController = new SignUpController();
            signUpController.signUp(request, httpResponse);

        }

        if (request.getUrl().startsWith(RequestURL.LOGIN_POST.getUrl())) {
            logInController = new LogInController();
            logInController.login(request, httpResponse);
        }
    }
}
