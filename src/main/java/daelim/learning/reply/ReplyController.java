package daelim.learning.reply;

import daelim.learning.board.Board;
import daelim.learning.board.BoardRepository;
import daelim.learning.reply.dto.ReplyRequest;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ReplyController {

    private final ReplyService replyService;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    @PostMapping("/detail/reply/{boardNo}")
    public String write(@PathVariable(name="boardNo") Long boardNo, ReplyRequest request, HttpSession session) throws NullPointerException {

        Long userNo = (Long) session.getAttribute("userNo");

        User writer = userRepository.findById(userNo).orElseThrow(() -> new IllegalArgumentException("not found userNo = "+userNo));
        Board board = boardRepository.findByBoardNo(boardNo);

        replyService.save(request, board, writer);

        return "redirect:/board/detail/"+boardNo;
    }

    // 삭제 윤성이 작업
    @PostMapping("/detail/{boardNo}/reply/delete/{replyNo}")
    public String delete(@PathVariable(name = "boardNo") Long boardNo, @PathVariable(name = "replyNo") Long replyNo) {
        replyService.delete(replyNo);
        return "redirect:/board/detail/";
    }
}
