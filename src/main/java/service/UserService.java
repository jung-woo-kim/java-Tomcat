package service;

import db.MemoryUserRepository;
import controller.dto.LogInDTO;
import model.User;
import controller.dto.SignUpDTO;

public class UserService {
    private final MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
            return userService;
        }
        return userService;
    }

    public void signUP(SignUpDTO signUpDTO) {
        memoryUserRepository.addUser(signUpDTO.toUser());
    }

    public boolean isUser(LogInDTO logInDTO) {
        User user = memoryUserRepository.findUserById(logInDTO.getUserId());
        if (user == null) {
            return false;
        }

        return user.getPassword().equals(logInDTO.getPassword());
    }
}
