package daelim.learning.repository;

import daelim.learning.Entity.WritingTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WritingRepostiroy extends JpaRepository<WritingTbl, Integer> {
}
