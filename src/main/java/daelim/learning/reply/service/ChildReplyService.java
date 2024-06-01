package daelim.learning.reply.service;

import daelim.learning.reply.dto.ChildReplyRequest;
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

@Service
@RequiredArgsConstructor
@Transactional
public class ChildReplyService {

    private final ChildReplyRepository childReplyRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    // 대댓글 저장
    public void save(ChildReplyRequest request,
                     Long replyNo,
                     HttpSession session) {
        Long userNo = (Long) session.getAttribute("userNo");

        User writer = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("not found userNo = "+userNo));

        Reply reply = replyRepository.findById(replyNo).orElseThrow();

        ChildReply childReply = request.toEntity(writer, reply);

        childReplyRepository.save(childReply);
    }


    // 대댓글 수정
    public void update(ChildReplyRequest request, Long childReplyNo) {
        childReplyRepository.updateCommentById(childReplyNo, request.getComment());
    }

    // 대댓글 삭제
    public void delete(Long childReplyNo) {
        childReplyRepository.deleteById(childReplyNo);
    }
}
