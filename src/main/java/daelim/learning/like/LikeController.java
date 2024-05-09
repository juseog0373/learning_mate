package daelim.learning.like;

import daelim.learning.board.BoardRepository;
import daelim.learning.like.dto.LikeRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private BoardRepository boardRepository; // BoardRepository 주입

    @GetMapping("/like/add/{boardNo}")
    public String addLike(@PathVariable(name="boardNo") Long boardNo, HttpSession session) {
        Long userNo = (Long) session.getAttribute("userNo");

        LikeRequest request = LikeRequest.builder()
                .boardNo(boardNo)
                .userNo(userNo)
                .build();

        likeService.addLike(request);

        return "redirect:/";
    }
}
