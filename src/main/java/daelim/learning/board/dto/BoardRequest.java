package daelim.learning.board.dto;

import daelim.learning.board.Board;
import daelim.learning.board.StudyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private Integer totalPeople;
    private StudyType studyType;
    private String studySubject;
    private String dueDate;
    private String contactLink;
    private String title;
    private String content;

    public Board toEntity() {
        return Board.builder()
                .totalPeople(totalPeople)
                .studyType(studyType)
                .studySubject(studySubject)
                .dueDate(dueDate)
                .contactLink(contactLink)
                .title(title)
                .content(content)
                .build();
    }
}
