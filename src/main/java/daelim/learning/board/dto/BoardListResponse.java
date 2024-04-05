package daelim.learning.board.dto;

import daelim.learning.board.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BoardListResponse {
    private Long boardNo;
    private String title;
    private String dueDate;
    private String studySubject;
    private Integer viewCount;

    public BoardListResponse(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.dueDate = board.getDueDate();
        this.studySubject = board.getStudySubject();
        this.viewCount = board.getViewCount();
    }
}
