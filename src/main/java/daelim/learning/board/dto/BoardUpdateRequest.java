package daelim.learning.board.dto;

import daelim.learning.board.StudyType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUpdateRequest {
    private Long boardNo;
    private String title;
    private String studySubject="0";
    private StudyType studyType;
    private String totalPeople;
    private String dueDate;
    private String contactLink;
    private String content;
}