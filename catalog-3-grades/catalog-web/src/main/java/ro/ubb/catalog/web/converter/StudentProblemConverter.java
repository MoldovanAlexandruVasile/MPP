package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.StudentProblem;
import ro.ubb.catalog.web.dto.StudentProblemDto;

@Component
public class StudentProblemConverter
        extends AbstractConverter<StudentProblem, StudentProblemDto> {

    @Override
    public StudentProblem convertDtoToModel(StudentProblemDto studentProblemDto) {
        throw new RuntimeException("Runtime Exception");
    }

    @Override
    public StudentProblemDto convertModelToDto(StudentProblem studentProblem) {
        return StudentProblemDto.builder()
                .studentId(studentProblem.getStudent().getId())
                .problemId(studentProblem.getProblem().getId())
                .grade(studentProblem.getGrade())
                .build();
    }
}
