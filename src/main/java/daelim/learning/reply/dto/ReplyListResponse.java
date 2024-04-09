package daelim.learning.reply.dto;
import daelim.learning.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReplyListResponse {

    private String comment;
    private String writer;

    @Builder
    public ReplyListResponse(String comment, User user) {
        this.comment = comment;
        if (user != null) {
            this.writer = user.getUserName();
        } else {
            throw new EntityNotFoundException("사용자가 없습니다.");
        }
    }
}
