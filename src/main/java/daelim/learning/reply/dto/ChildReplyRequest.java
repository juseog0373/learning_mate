package daelim.learning.reply.dto;

import daelim.learning.board.Board;
import daelim.learning.reply.entity.ChildReply;
import daelim.learning.reply.entity.Reply;
import daelim.learning.user.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildReplyRequest {

    private String comment;

    public ChildReply toEntity(Board board, User user, Reply reply) {
        return ChildReply.builder()
                .boardNo(board)
                .userNo(user)
                .replyNo(reply)
                .comment(comment)
                .build();
    }
}
