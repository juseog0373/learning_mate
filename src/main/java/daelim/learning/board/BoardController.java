package daelim.learning.board;

import daelim.learning.board.dto.BoardRequest;
import daelim.learning.board.dto.BoardResponse;
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
        // 'boardService.findAll()'이 'BoardResponse' 객체의 리스트를 반환한다고 가정
        List<BoardResponse> sortedAndLimitedBoard = boardService.findAll().stream()
                .sorted(Comparator.comparing(BoardResponse::getViewCount).reversed())
                .limit(4)
                .collect(Collectors.toList());

        // 정렬된 리스트를 모델에 추가
        model.addAttribute("topBoard", sortedAndLimitedBoard);

        // 전체 게시글 리스트를 모델에 추가
        model.addAttribute("allBoard", boardService.findAll());

        return "index";
    }

    @GetMapping("/board/write")
    public String write(Model model, BoardRequest boardRequest) {
        model.addAttribute("request", boardRequest);
        return "board/write";
    }

    @PostMapping("/board/write") //pro로 하지말래 수정할것
    public String writePro(@ModelAttribute("request") BoardRequest request){
        boardService.write(request);

        return "redirect:/";
    }

    @GetMapping("/board/detail/{id}")
    public String detail(Model model, @PathVariable(name="id")Long id){
        model.addAttribute("list",boardService.findById(id));
        return "board/detail";
    }

    @GetMapping("/login")
        public String login(){
            return "/login";
        }

    @GetMapping("/join")
    public String join(){
        return "/join";
    }
}
