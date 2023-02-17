package signup.controller;

import signup.constants.UserQueryKey;
import signup.dto.SignUpDTO;
import signup.service.SignUpService;
import util.request.HttpRequest;
import util.request.HttpRequestUtils;

import java.util.Map;

public class SignUpController {
    SignUpService signUpService = SignUpService.getInstance();
    public String signUp(Map<String ,String> query) {
        SignUpDTO signUpDTO = queryToDTO(query);
        signUpService.signUP(signUpDTO);

        return "/index.html";
    }

    public String signUp(String body) {
        Map<String, String> query = HttpRequestUtils.parseQuery(body);
        SignUpDTO signUpDTO = queryToDTO(query);
        signUpService.signUP(signUpDTO);

        return "/index.html";
    }

    private SignUpDTO queryToDTO(Map<String ,String> query) {
        return new SignUpDTO(query.get(UserQueryKey.ID.getKey()), query.get(UserQueryKey.PASSWORD.getKey()), query.get(UserQueryKey.NAME.getKey()), query.get(UserQueryKey.EMAIL.getKey()));
    }
}
