package daelim.learning.reply.repository;

import daelim.learning.reply.entity.ChildReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildReplyRepository extends JpaRepository<ChildReply, Long> {

    @Query("SELECT c FROM ChildReply c WHERE c.replyNo.replyNo = :replyNo")
    List<ChildReply> findByReplyNo(Long replyNo);

    @Modifying
    @Query("UPDATE ChildReply cr SET cr.comment = :newComment WHERE cr.childNo = :childReplyNo")
    void updateCommentById(@Param("childReplyNo") Long childReplyNo, @Param("newComment") String newComment);
}
