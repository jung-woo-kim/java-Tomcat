package signup.dto;

import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignUpDTOTest {

    @Test
    void toUser() {
        SignUpDTO signUpDTO = new SignUpDTO("id","pass","name","name@naver.com");
        User user = signUpDTO.toUser();
        assertEquals(user.getUserId(), "id");
        assertEquals(user.getName(), "name");
        assertEquals(user.getPassword(), "pass");
        assertEquals(user.getEmail(),"name@naver.com");
    }
}