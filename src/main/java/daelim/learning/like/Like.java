package daelim.learning.like;

import daelim.learning.board.Board;
import daelim.learning.reply.Reply;
import daelim.learning.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_LIKES")
@Builder
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_no",
            foreignKey = @ForeignKey(name = "fk_like_user", value = ConstraintMode.CONSTRAINT),
            nullable = false
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "board_no",
            foreignKey = @ForeignKey(name = "fk_like_board", value = ConstraintMode.CONSTRAINT),
            nullable = false
    )
    private Board board;
}
