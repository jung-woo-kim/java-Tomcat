package signup.service;

import db.MemoryUserRepository;
import signup.dto.SignUpDTO;

public class SignUpService {
    private final MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
    private static SignUpService signUpService;

    private SignUpService() {
    }

    public static SignUpService getInstance() {
        if (signUpService == null) {
            signUpService = new SignUpService();
            return signUpService;
        }
        return signUpService;
    }

    public void signUP(SignUpDTO signUpDTO) {
        memoryUserRepository.addUser(signUpDTO.toUser());
    }
}
