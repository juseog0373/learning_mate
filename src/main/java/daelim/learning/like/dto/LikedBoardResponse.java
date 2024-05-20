package daelim.learning.like.dto;

import daelim.learning.board.Board;
import daelim.learning.like.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikedBoardResponse {
    private Long boardNo;
    private String title;
    private String dueDate;

    public LikedBoardResponse(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.dueDate = board.getDueDate();
    }
}