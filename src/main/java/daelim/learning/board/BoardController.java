package daelim.learning.board;

import daelim.learning.board.dto.BoardRequest;
import daelim.learning.reply.ReplyService;
import daelim.learning.reply.dto.ReplyRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService; // 스프링 빈 컨테이너에 등록되어있는 스프링 빈 기본 생성자에 빈 싱글톤이 유지된다
    private final ReplyService replyService;

    @GetMapping("/")
    public String index(Model model) {

        // 조회수 top4 게시글
        model.addAttribute("topBoard", boardService.findTopBoard());
        // 일반 게시글 list
        model.addAttribute("allBoard", boardService.findAll());

        return "index";
    }

    @GetMapping("/board/write")
    public String write(Model model, BoardRequest boardRequest) {
        model.addAttribute("boardRequest", boardRequest);
        return "board/write";
    }

    @PostMapping("/board/write")
    public String writePro(@ModelAttribute("request") BoardRequest request, HttpSession session){
        boardService.write(request, session);

        return "redirect:/";
    }

    @GetMapping("/board/detail/{boardNo}")
    public String detail(Model model, @PathVariable(name="boardNo") Long boardNo, ReplyRequest replyRequest) {
        model.addAttribute("boardList", boardService.boardDetail(boardNo));
        model.addAttribute("replyRequest", replyRequest);
        model.addAttribute("replyList", replyService.findAll(boardNo));
        
        return "board/detail";
    }
}
