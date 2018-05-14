package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StudentProblemDto {
    private Long studentId;
    private Long problemId;
    private Integer grade;
}
