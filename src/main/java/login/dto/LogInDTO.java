package login.dto;

public class LogInDTO {
    private String userId;
    private String password;

    public LogInDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
