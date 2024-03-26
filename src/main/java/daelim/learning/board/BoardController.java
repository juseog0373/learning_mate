package daelim.learning.board;

import daelim.learning.board.dto.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("board", boardService.findAll());
//        System.out.println(model.getAttribute("list"));
        return "index";
    }

    @PostMapping("/board/write")
    public String writePro(@ModelAttribute BoardRequest request){
        boardService.write(request);

        return "redirect:/";
    }

}
