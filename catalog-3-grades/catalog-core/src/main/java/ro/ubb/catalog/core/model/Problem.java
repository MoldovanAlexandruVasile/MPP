package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Problem extends BaseEntity<Long> {

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "problem", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<StudentProblem> studentProblems = new HashSet<>();

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(
                studentProblems.stream()
                        .map(StudentProblem::getStudent)
                        .collect(Collectors.toSet())
        );
    }

    public void addStudent(Student student) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setStudent(student);
        studentProblem.setProblem(this);
        studentProblems.add(studentProblem);
    }

    public void addGrade(Student student, Integer grade) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setStudent(student);
        studentProblem.setGrade(grade);
        studentProblem.setProblem(this);
        studentProblems.add(studentProblem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Problem that = (Problem) o;

        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public String toString() {
        return "Problem{" +
                "description='" + description + '\'' +
                "} " + super.toString();
    }
}
