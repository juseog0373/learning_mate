package daelim.learning.reply.repository;

import daelim.learning.reply.entity.ChildReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildReplyRepository extends JpaRepository<ChildReply, Long> {
    List<ChildReply> findByBoardNoAndReplyNo(Long boardNo, Long replyNo);
}
