package daelim.learning.user;

import daelim.learning.user.dto.JoinRequest;
import daelim.learning.user.dto.LoginRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request) {
        userService.login(request);
        return "";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute JoinRequest request, BindingResult bindingResult) {
        if(userService.isUserIdDuplicate(request.getUserId())) {
            bindingResult.addError(new FieldError("joinRequest", "userId", "중복된 아이디입니다."));
        }
        userService.signup(request);
        return "";
    }
}
