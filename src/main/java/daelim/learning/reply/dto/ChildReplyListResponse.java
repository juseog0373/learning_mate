package daelim.learning.reply.dto;

import daelim.learning.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildReplyListResponse {
    private String childComment;
    private String childWriter;
    private Long childReplyNo;

    @Builder
    public ChildReplyListResponse(String comment, User user, Long childReplyNo) {
        this.childComment = comment;
        this.childWriter = user.getUserName();
        this.childReplyNo = childReplyNo;
    }
}
