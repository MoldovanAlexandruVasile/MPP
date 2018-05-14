package ro.ubb.catalog.web.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProblemDto extends BaseDto {
    private String description;

}
