package daelim.learning.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class UserTbl
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNum;
    private String userId;
    private String userPw;
    private String userName;
}
