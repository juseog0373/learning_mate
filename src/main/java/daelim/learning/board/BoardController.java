package daelim.learning.board;

import daelim.learning.board.dto.BoardRequest;
import daelim.learning.board.dto.BoardListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

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
        model.addAttribute("request", boardRequest);
        return "board/write";
    }

    @PostMapping("/board/write")
    public String writePro(@ModelAttribute("request") BoardRequest request){
        boardService.write(request);

        return "redirect:/";
    }

    @GetMapping("/board/detail/{id}")
    public String detail(Model model, @PathVariable(name="id") Long id) {
        model.addAttribute("list", boardService.boardDetail(id));
        return "board/detail";
    }
}
