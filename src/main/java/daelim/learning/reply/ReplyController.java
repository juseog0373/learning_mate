package daelim.learning.reply;

import daelim.learning.board.Board;
import daelim.learning.board.BoardRepository;
import daelim.learning.reply.dto.ReplyRequest;
import daelim.learning.reply.dto.ReplyResponseDto;
import daelim.learning.reply.dto.ReplySaveDto;
import daelim.learning.reply.service.ReplyService;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    @PostMapping("/board/detail/reply/{boardNo}")
    public String write(@PathVariable(name="boardNo") Long boardNo, ReplyRequest request, HttpSession session) throws NullPointerException {

        Long userNo = (Long) session.getAttribute("userNo");

        User writer = userRepository.findById(userNo).orElseThrow(() -> new IllegalArgumentException("not found userNo = "+userNo));
        Board board = boardRepository.findByBoardNo(boardNo);

        replyService.save(request, board, writer);

        return "redirect:/board/detail/"+boardNo;
    }

    // 삭제
    @PostMapping("/board/detail/{boardNo}/reply/{replyId}/remove")
    public String delete(@PathVariable(name="boardNo") Long boardNo, @PathVariable(name = "replyId") Long replyId) {
        replyService.remove(replyId);
        return "redirect:/board/detail/";
    }
}
