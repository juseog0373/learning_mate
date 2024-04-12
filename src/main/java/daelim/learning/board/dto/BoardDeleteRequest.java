package daelim.learning.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDeleteRequest {
    private Long boardNo;
}
