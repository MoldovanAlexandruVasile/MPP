package ro.ubb.catalog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProblemsDto {
    private Set<ProblemDto> problems;

    public Set<ProblemDto> getProblems() {
        return problems;
    }

    public void setProblems(Set<ProblemDto> problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "ProblemsDto{" +
                "problems=" + problems +
                '}';
    }
}
