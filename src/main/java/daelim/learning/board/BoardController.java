package daelim.learning.board;

import daelim.learning.board.dto.BoardDetailResponse;
import daelim.learning.board.dto.BoardRequest;
import daelim.learning.board.dto.BoardSearchCond;
import daelim.learning.board.dto.BoardUpdateRequest;
import daelim.learning.like.LikeService;
import daelim.learning.reply.dto.ChildReplyRequest;
import daelim.learning.reply.dto.ReplyRequest;
import daelim.learning.reply.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final LikeService likeService;

    @GetMapping("")
    public String index(Model model, HttpSession session,
                        @RequestParam(name="keyword", required = false) String keyword,
                        @RequestParam(name="sortType", required = false) String sortType,
                        @RequestParam(name="studyType", required = false) StudyType studyType) {
        // 로그인한 사용자 가져오기
        Long userNo = (Long) session.getAttribute("userNo");
        // 로그인한 사용자가 찜한 게시글의 boardNo 가져와서 List 추출
        List<Long> likedBoards = (userNo != null) ? likeService.findLikedBoardList(userNo) : Collections.emptyList();

        // 조회수 top4 게시글
        model.addAttribute("topBoard", boardService.findTopBoard());

        if (sortType != null || studyType != null || keyword != null) {
            BoardSearchCond boardSearchCond = new BoardSearchCond();
            boardSearchCond.setStudyType(studyType);
            boardSearchCond.setSortType(sortType);
            boardSearchCond.setKeyword(keyword);

            model.addAttribute("allBoard", boardService.findAll(boardSearchCond));
        } else {
            model.addAttribute("allBoard", boardService.findAll());
        }
        // 일반 게시글 list

        model.addAttribute("likedBoards", likedBoards);

        return "index";
    }

    @GetMapping("/board/write")
    public String writeForm(Model model, BoardRequest boardRequest) {
        model.addAttribute("boardRequest", boardRequest);
        return "board/write";
    }

    @PostMapping("/board/write")
    public String write(@ModelAttribute("request") BoardRequest request, HttpSession session){
        boardService.write(request, session);

        return "redirect:/";
    }

    @GetMapping("/board/detail/{boardNo}")
    public String detailForm(Model model, @PathVariable(name="boardNo") Long boardNo,
                             ReplyRequest replyRequest,
                             ChildReplyRequest childReplyRequest,
                             HttpServletRequest request) {
        model.addAttribute("boardList", boardService.detailBoard(boardNo, request));
        model.addAttribute("replyRequest", replyRequest);
        model.addAttribute("childRequest", childReplyRequest);
        model.addAttribute("replyList", replyService.findAll(boardNo));

        return "board/detail";
    }

    @GetMapping("/board/update/{boardNo}")
    public String updateForm(@PathVariable(name="boardNo") Long boardNo, Model model, HttpServletRequest request) {
        model.addAttribute("update", boardService.detailBoard(boardNo, request));

        return "/board/update";
    }

    @PostMapping("/board/update/{boardNo}")
    public String update(@PathVariable(name="boardNo") Long boardNo, @ModelAttribute("update") BoardUpdateRequest request, RedirectAttributes redirectAttributes) {
        boardService.updateBoard(boardNo, request);

        redirectAttributes.addAttribute("boardNo", boardNo);
        return "redirect:/board/detail/{boardNo}";
    }

    @GetMapping("/board/delete/{boardNo}")
    public String delete(@PathVariable(name = "boardNo") Long boardNo) {
        boardService.deleteBoard(boardNo);
        return "redirect:/";
    }
}
