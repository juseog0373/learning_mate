package daelim.learning.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_BOARD")
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;
    private String title;
    private Integer totalPeople; //모집 인원
    @Enumerated(EnumType.STRING)
    private StudyType studyType; // 스터디 방식(ON, OFF, BOTH)
    private String studySubject; // 스터디 과목
    private String dueDate;  // 마감일
    private String contactLink; // 오픈채팅링크
    private String content; // 본문
    private Integer viewCount; //조회수
    private Date createdAt; //작성일

}
