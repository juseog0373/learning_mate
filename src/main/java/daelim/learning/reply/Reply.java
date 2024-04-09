package daelim.learning.reply;

import daelim.learning.board.Board;
import daelim.learning.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "tb_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_no")
    private Long id;

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
    @Column(name = "create_at")
    private LocalDateTime createTime;

}
