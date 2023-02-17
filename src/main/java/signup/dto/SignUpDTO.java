package signup.dto;

import model.User;

public class SignUpDTO {
    private String userId;
    private String password;
    private String name;

    public SignUpDTO(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public User toUser() {
        return new User(userId,password,name,"");
    }
}
