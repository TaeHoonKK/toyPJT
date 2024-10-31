package toyPJT.toypjt_v100.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class todoDto {
    private Long todoId;
    private String content;
    private String completeYn;
}
