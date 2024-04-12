package daelim.learning.board;

import daelim.learning.board.dto.*;
import daelim.learning.user.User;
import daelim.learning.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

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
    public void write(BoardRequest request, HttpSession session) {
        Long userNo = (Long) session.getAttribute("userNo");
        User writer = userRepository.findById(userNo).orElseThrow(() -> new EntityNotFoundException("not found user"));
        request.setWriter(writer);
        boardRepository.save(request.toEntity());
    }

    public BoardDetailResponse boardDetail(Long boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        board.incrementViewCount(); // 조회수 1 증가
        boardRepository.save(board); // 변경된 조회수 저장

        return BoardDetailResponse.builder()
                .boardNo(board.getBoardNo())
                .writer(board.getWriter())
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
    public BoardDetailResponse boardModify(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        return BoardDetailResponse.builder()
                .boardNo(board.getBoardNo())
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
    public BoardDeleteRequest boardDelete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        boardRepository.deleteById(id);

        return BoardDeleteRequest.builder()
                .boardNo(board.getBoardNo())
                .build();
    }

    public void boardUpdate(BoardUpdateRequest updateRequest) {
        Board board = boardRepository.findById(updateRequest.getBoardNo())
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        board.update(updateRequest);
    }
}
