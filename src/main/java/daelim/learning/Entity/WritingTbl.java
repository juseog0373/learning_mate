package daelim.learning.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class WritingTbl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer titleNum;
    private String content;
    private String title;
    private String img;
}
