package daelim.learning.reply.service;

import daelim.learning.board.Board;
import daelim.learning.board.BoardRepository;
import daelim.learning.reply.dto.ChildReplyResponse;
import daelim.learning.reply.entity.ChildReply;
import daelim.learning.reply.repository.ChildReplyRepository;
import daelim.learning.reply.repository.ReplyRepository;
import daelim.learning.reply.entity.Reply;
import daelim.learning.reply.dto.ReplyListResponse;
import daelim.learning.reply.dto.ReplyRequest;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ChildReplyRepository childReplyRepository;

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    public void save(ReplyRequest replyRequest, Long boardNo, HttpSession session) {

        Long userNo = (Long) session.getAttribute("userNo");

        User writer = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("not found userNo = "+userNo));
        Board board = boardRepository.findByBoardNo(boardNo);

        Reply reply = replyRequest.toEntity(board, writer);

        replyRepository.save(reply);
    }

    // 부모 댓글, 자식 댓글 모두 조회
    public List<ReplyListResponse> findAll(Long boardNo) {
        List<Reply> replies = replyRepository.findByBoardNo(boardNo); // 부모 댓글 가져오기
        List<ReplyListResponse> replyListResponses = new ArrayList<>();

        for(Reply reply : replies) {
            List<ChildReply> childReplies = childReplyRepository.findByReplyNo(reply.getReplyNo());

            List<ChildReplyResponse> childReplyResponseList = childReplies.stream()
                    .map(childReply -> ChildReplyResponse.builder()
                            .childNo(childReply.getChildNo())
                            .childComment(childReply.getComment())
                            .childWriter(childReply.getUserNo().getUserName())
                            .build())
                    .collect(Collectors.toList());

            ReplyListResponse response = ReplyListResponse.builder()
                    .replyNo(reply.getReplyNo())
                    .comment(reply.getComment())
                    .user(reply.getUserNo())
                    .childReplyResponseList(childReplyResponseList)
                    .build();

            replyListResponses.add(response);
        }

        return replyListResponses;
    }

    // 수정
    public void update(ReplyRequest replyRequest, Long replyNo) {
        replyRepository.updateCommentById(replyNo, replyRequest.getComment());
    }

    public void delete(Long replyNo) {
        replyRepository.deleteById(replyNo);
    }
}
