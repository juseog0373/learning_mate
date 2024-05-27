package daelim.learning.reply.service;

import daelim.learning.board.Board;
import daelim.learning.board.BoardRepository;
import daelim.learning.reply.dto.ChildReplyListResponse;
import daelim.learning.reply.dto.ChildReplyRequest;
import daelim.learning.reply.dto.ReplyListResponse;
import daelim.learning.reply.entity.ChildReply;
import daelim.learning.reply.entity.Reply;
import daelim.learning.reply.repository.ChildReplyRepository;
import daelim.learning.reply.repository.ReplyRepository;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChildReplyService {

    private final ChildReplyRepository childReplyRepository;

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    // 대댓글 저장
    public void save(ChildReplyRequest request,
                     Long boardNo, Long replyNo,
                     HttpSession session) {
        Long userNo = (Long) session.getAttribute("userNo");

        User writer = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("not found userNo = "+userNo));
        Board board = boardRepository.findByBoardNo(boardNo);
        Reply reply = replyRepository.findById(replyNo).orElseThrow();

        ChildReply childReply = request.toEntity(board, writer, reply);

        childReplyRepository.save(childReply);
    }

    // 대댓글 조회
    public List<ChildReplyListResponse> findAllChildReply(Long boardNo, Long replyNo) {
        return childReplyRepository.findByBoardNoAndReplyNo(boardNo, replyNo).stream().map(
                childReply -> ChildReplyListResponse.builder()
                        .comment(childReply.getComment())
                        .user(childReply.getUserNo())
                        .childReplyNo(childReply.getChildNo())
                        .build()
        ).toList();
    }

    // 대댓글 수정
    public void update(ChildReplyRequest request, Long childReplyNo) {

        ChildReply childReply = childReplyRepository.findById(childReplyNo).orElseThrow();

        childReply.updateComment(request.getComment());

        childReplyRepository.save(childReply);
    }

    // 대댓글 삭제
    public void delete(Long childReplyNo) {
        childReplyRepository.deleteById(childReplyNo);
    }
}
