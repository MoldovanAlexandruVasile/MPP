package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Problem;
import ro.ubb.catalog.web.dto.ProblemDto;

@Component
public class ProblemConverter extends AbstractConverterBaseEntityConverter<Problem, ProblemDto> {
    @Override
    public Problem convertDtoToModel(ProblemDto dto) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public ProblemDto convertModelToDto(Problem problem) {
        ProblemDto dto = ProblemDto.builder()
                .description(problem.getDescription())
                .build();
        dto.setId(problem.getId());
        return dto;
    }
}
