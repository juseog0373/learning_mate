package daelim.learning.board;

import daelim.learning.board.dto.BoardUpdateRequest;
import daelim.learning.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    private String totalPeople; //모집 인원
    @Enumerated(EnumType.STRING)
    private StudyType studyType; // 스터디 방식(ON, OFF, BOTH)
    private String studySubject; // 스터디 과목
    private String dueDate;  // 마감일
    private String contactLink; // 오픈채팅링크
    private String content; // 본문
    private int viewCount; //조회수
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User writer; // 작성자
    @CreationTimestamp
    private Date createdAt; //작성일

    public void incrementViewCount() {
        this.viewCount += 1;
    }

    public Board update(BoardUpdateRequest updateRequest){
        this.boardNo = updateRequest.getBoardNo();
        this.title = updateRequest.getTitle();
        this.content = updateRequest.getContent();
        this.studySubject = updateRequest.getStudySubject();
        this.studyType = updateRequest.getStudyType();
        this.dueDate = updateRequest.getDueDate();
        this.totalPeople = updateRequest.getTotalPeople();

        return this;
    }
}
