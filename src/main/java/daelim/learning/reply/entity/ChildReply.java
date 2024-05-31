package daelim.learning.reply.entity;

import daelim.learning.board.Board;
import daelim.learning.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "tb_child_reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class ChildReply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "child_no")
    private Long childNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    private User userNo; // 사용자

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reply_no")
    private Reply replyNo; // 부모 댓글

    @Lob
    @Column(nullable = false, name = "comment")
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
