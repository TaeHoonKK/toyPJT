package toyPJT.toypjt_v100.domain.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Builder
public class selectDto {
    private Long id;
    private String content;
    private String completeYn;
    private String createdDate;
}
