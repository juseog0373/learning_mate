package daelim.learning.reply.controller;

import daelim.learning.reply.dto.ChildReplyRequest;
import daelim.learning.reply.service.ChildReplyService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ChildReplyController {

    private final ChildReplyService childReplyService;

    // 대댓글 작성
    @PostMapping("/board/detail/reply/{boardNo}/{replyNo}/write")
    public String write(@PathVariable(name = "boardNo") Long boardNo,
                        @PathVariable(name = "replyNo") Long replyNo,
                        ChildReplyRequest request,
                        HttpSession session) throws NullPointerException {
        childReplyService.save(request, replyNo, boardNo, session);
        return "redirect:/board/detail/reply/" + boardNo;
    }

    // 대댓글 수정
    @PostMapping("/board/detail/{boardNo}/reply/{replyNo}/{childReplyNo}/update")
    public String update(@PathVariable(name = "boardNo") Long boardNo,
                         @PathVariable(name = "replyNo") Long replyNo,
                         @PathVariable(name = "childReplyNo") Long childReplyNo,
                         ChildReplyRequest request,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {

        childReplyService.update(request, childReplyNo);
        return "redirect:/board/detail/reply/" + boardNo;
    }

    // 대댓글 삭제
    @GetMapping("/board/detail/{boardNo}/reply/{replyNo}/{childReplyNo}/delete")
    public String delete(@PathVariable(name = "boardNo") Long boardNo,
                         @PathVariable(name = "replyNo") Long replyNo,
                         @PathVariable(name = "childReplyNo") Long childReplyNo,
                         RedirectAttributes redirectAttributes) {

        childReplyService.delete(childReplyNo);

        redirectAttributes.addAttribute("replyNo", replyNo);

        return "redirect:/board/detail/{boardNo}";
    }

    // 대댓글 조회
    @GetMapping("/board/detail/{boardNo}/{replyNo}")
    public String viewChildReplyList(Model model,
                                     @PathVariable(name = "boardNo") Long boardNo,
                                     @PathVariable(name = "replyNo") Long replyNo) {

        model.addAttribute("childReplies", childReplyService.findAllChildReply(boardNo, replyNo));

        return "redirect:/board/detail/" + boardNo + "/" + replyNo;
    }

}
