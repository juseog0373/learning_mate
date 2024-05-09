package daelim.learning.like;

import daelim.learning.board.Board;
import daelim.learning.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByUser_UserNo(Long userNo);

    Like findByUserAndBoard(User user, Board board);
}
