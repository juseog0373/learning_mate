package daelim.learning.like.dto;

import daelim.learning.board.Board;
import daelim.learning.like.Like;
import daelim.learning.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeRequest {
    private Long userNo;
    private Long boardNo;

    public Like toEntity(User user, Board board) {
        return Like.builder()
                .board(board)
                .user(user)
                .build();
    }
}
