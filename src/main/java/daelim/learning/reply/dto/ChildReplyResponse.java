package daelim.learning.reply.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ChildReplyResponse {

    private Long childNo;
    private String childComment;
    private String childWriter;

    @Builder
    public ChildReplyResponse(Long childNo, String childComment, String childWriter) {
        this.childNo = childNo;
        this.childComment = childComment;
        this.childWriter = childWriter;
    }

}
