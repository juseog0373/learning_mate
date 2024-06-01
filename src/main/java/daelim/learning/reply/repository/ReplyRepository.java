package daelim.learning.reply.repository;

import daelim.learning.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("SELECT r FROM Reply r WHERE r.boardNo.boardNo = :boardNo")
    List<Reply> findByBoardNo(@Param("boardNo") Long boardNo);

    @Modifying
    @Query("UPDATE Reply cr SET cr.comment = :newComment WHERE cr.replyNo = :replyNo")
    void updateCommentById(@Param("replyNo") Long replyNo, @Param("newComment") String newComment);
}
