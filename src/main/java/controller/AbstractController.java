package controller;

import util.request.HttpMethod;
import util.request.HttpRequest;
import util.response.HttpResponse;

import java.io.IOException;

public abstract class AbstractController implements Controller{
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        if (httpRequest.getStartLine().getHttpMethod().equals(HttpMethod.GET)) {
            doGet(httpRequest, httpResponse);
            return;
        }
        doPost(httpRequest, httpResponse);
    }

    abstract void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException;

    abstract void doPost(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException;
}
