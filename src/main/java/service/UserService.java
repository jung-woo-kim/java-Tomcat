package service;

import db.MemoryUserRepository;
import signup.dto.SignUpDTO;

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
}
