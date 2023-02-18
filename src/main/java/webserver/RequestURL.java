package webserver;

public enum RequestURL {
    LOGIN("/user/login"),SIGNUP("/user/create"), ROOT("./webapp"),INDEX("/index.html");

    private String url;

    RequestURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
