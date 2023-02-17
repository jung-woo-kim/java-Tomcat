package util.response;

public enum HttpStatus {
    OK("OK"), REDIRECT("Redirection");

    private final String status;

    HttpStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
