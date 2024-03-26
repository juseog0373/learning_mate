package daelim.learning.board.dto;

import daelim.learning.board.Board;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponse {
    private String title;
    private String dueDate;
    private String studySubject;
    private Integer viewCount;

    public BoardResponse(Board board) {
        this.title = board.getTitle();
        this.dueDate = board.getDueDate();
        this.studySubject = board.getStudySubject();
        this.viewCount = board.getViewCount();
    }
}
