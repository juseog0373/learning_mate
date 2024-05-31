package daelim.learning.reply.dto;
import daelim.learning.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyListResponse {

    private String parentComment;
    private String parentWriter;
    private Long replyNo;
    private List<ChildReplyResponse> childReplyResponseList;


    @Builder
    public ReplyListResponse(String comment, User user, Long replyNo, List<ChildReplyResponse> childReplyResponseList) {
        this.parentComment = comment;
        this.parentWriter = user.getUserName();
        this.replyNo = replyNo;
        this.childReplyResponseList = childReplyResponseList;
    }
}
