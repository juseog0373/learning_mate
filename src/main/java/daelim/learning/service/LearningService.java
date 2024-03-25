package daelim.learning.service;

import daelim.learning.Entity.WritingTbl;
import daelim.learning.repository.WritingRepostiroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningService {

    @Autowired
    private final WritingRepostiroy writingRepostiroy;

    public LearningService(WritingRepostiroy writingRepostiroy) {
        this.writingRepostiroy = writingRepostiroy;
    }

    public List<WritingTbl> writingTblList(){
        return writingRepostiroy.findAll();
    }
    //글 작성

    public void write(WritingTbl writingTbl){
        writingRepostiroy.save(writingTbl);
    }
}
