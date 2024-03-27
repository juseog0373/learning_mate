package daelim.learning.board;

import daelim.learning.board.dto.BoardRequest;
import daelim.learning.board.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse> findAll(){
        List<BoardResponse> boardList = boardRepository.findAll().stream().map(BoardResponse::new).toList();
        return boardList;
    }
    //글 작성

    public void write(BoardRequest request){
        boardRepository.save(request.toEntity());
    }

    public Board findById (Long id){
        return boardRepository.findById(id).get();
    }
}
