package daelim.learning.user;

import daelim.learning.board.Board;
import daelim.learning.board.dto.BoardDetailResponse;
import daelim.learning.user.dto.JoinRequest;
import daelim.learning.user.dto.LoginRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String login(LoginRequest request, HttpSession session, BindingResult bindingResult) {
        String userId = request.getUserId();
        String password = request.getPassword();

        // 존재하는 사용자인지 확인
        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isPresent() && bCryptPasswordEncoder.matches(password, findUser.get().getPassword())) {
            // 로그인 성공
            String userName = findUser.get().getUserName();
            Long userNo = findUser.get().getUserNo();
            session.setAttribute("userName", userName); // 세션에 사용자 이름 저장
            session.setAttribute("userNo", userNo.toString()); // 세션에 사용자 이름 저장
            return "redirect:/"; // 메인 페이지로 리다이렉션
        } else {
            System.out.println("로그인 실패");
            // 로그인 실패
            bindingResult.reject("loginFail", "아이디 또는 비밀번호를 확인해주세요.");
            return "login"; // 로그인 페이지로 리턴
        }
    }

    public String join(JoinRequest request, BindingResult bindingResult) {
        if (isUserIdDuplicate(request.getUserId())) {
            bindingResult.addError(new FieldError("joinRequest", "userId", request.getUserId(), false, null, null, "중복된 아이디입니다."));
            return "join"; // 오류가 있을 경우, 회원가입 페이지로 다시 리턴
        } else {
            String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            request.setPassword(encodedPassword);
            userRepository.save(request.toEntity());
            return "redirect:/user/login";
        }

    }

    public Boolean isUserIdDuplicate(String userId) {
        Optional<User> findUser = userRepository.findByUserId(userId);
        return findUser.isPresent(); // 사용자를 찾았다면 true (중복), 찾지 못했다면 false (중복 아님)
    }

    public User userDetail(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다." + id));
    }

}
