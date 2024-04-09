package daelim.learning.reply.dto;
import daelim.learning.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyListResponse {

    private String comment;
    private String writer;

    @Builder
    public ReplyListResponse(String comment, User user) {
        this.comment = comment;
        if (user != null) {
            this.writer = user.getUserName();
        } else {
            this.writer = "Unknown";
        }
    }
}
