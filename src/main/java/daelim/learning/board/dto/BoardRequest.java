package daelim.learning.board.dto;

import daelim.learning.board.Board;
import daelim.learning.board.StudyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private String totalPeople="0";
    private StudyType studyType;
    private String studySubject;
    private String dueDate;
    private String contactLink;
    private String title;
    private String content;
    private int viewCount=0;

    public Board toEntity() {
        return Board.builder()
                .totalPeople(totalPeople)
                .studyType(studyType)
                .studySubject(studySubject)
                .dueDate(dueDate)
                .contactLink(contactLink)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .build();
    }
}
