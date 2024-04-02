package daelim.learning.user;

import daelim.learning.user.dto.JoinRequest;
import daelim.learning.user.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public String login(LoginRequest request) {
        Optional<User> findUser = userRepository.findByUserId(request.getUserId());
        return "";
    }

    public String signup(JoinRequest request) {
        Optional<User> user = userRepository.findByUserId(request.getUserId());

        if(user.isPresent()) {
            return "이미 중복된 아이디입니다.";
        } else {
            userRepository.save(request.toEntity());
            return "회원가입이 완료되었습니다.";
        }
    }

    public Boolean isUserIdDuplicate(String userId) {
        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}
