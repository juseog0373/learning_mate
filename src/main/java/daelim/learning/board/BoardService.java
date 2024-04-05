package daelim.learning.board;

import daelim.learning.board.dto.BoardDetailResponse;
import daelim.learning.board.dto.BoardRequest;
import daelim.learning.board.dto.BoardListResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardListResponse> findTopBoard() {
        return boardRepository.findAllByOrderByViewCountDesc().stream()
                .limit(4)
                .map(BoardListResponse::new)
                .collect(Collectors.toList());

    }

    public List<BoardListResponse> findAll() {
        List<BoardListResponse> boardList = boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardListResponse::new).toList();
        return boardList;
    }

    //글 작성
    public void write(BoardRequest request){
        boardRepository.save(request.toEntity());
    }

    public BoardDetailResponse boardDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        board.incrementViewCount(); // 조회수 1 증가
        boardRepository.save(board); // 변경된 조회수 저장

        return BoardDetailResponse.builder()
                .title(board.getTitle())
                .contactLink(board.getContactLink())
                .studySubject(board.getStudySubject())
                .studyType(board.getStudyType().getDescription())
                .totalPeople(board.getTotalPeople())
                .content(board.getContent())
                .dueDate(board.getDueDate())
                .viewCount(board.getViewCount())
                .build();
    }

}
