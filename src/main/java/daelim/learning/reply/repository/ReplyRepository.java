package daelim.learning.reply.repository;

import daelim.learning.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByBoardNoBoardNo(Long boardNo);
}
