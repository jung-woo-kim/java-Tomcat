package webserver;

public enum RequestURL {
    SIGNUP("/user/create"),DEFAULT("./webapp"),INDEX("/index.html");

    private String url;

    RequestURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
