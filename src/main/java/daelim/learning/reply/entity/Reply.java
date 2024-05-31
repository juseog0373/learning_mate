package daelim.learning.reply.entity;

import daelim.learning.board.Board;
import daelim.learning.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "tb_reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_no")
    private Long replyNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_no")
    private Board boardNo;

    @Lob
    @Column(nullable = false, name = "comment")
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "childNo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildReply> childReplies = new ArrayList<>();


}
