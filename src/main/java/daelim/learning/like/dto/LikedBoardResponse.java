package daelim.learning.like.dto;

import daelim.learning.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikedBoardResponse {
    private Long boardNo;
    private String title;
    private String dueDate;
    private String content;

    public static LikedBoardResponse from(final Board board) {
        return new LikedBoardResponse(
                board.getBoardNo(),
                board.getTitle(),
                board.getDueDate(),
                board.getContent()
        );
    }
}