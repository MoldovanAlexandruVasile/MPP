package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "grade")
@IdClass(StudentProblemPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class StudentProblem implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(name = "grade")
    private Integer grade;

    @Override
    public String toString() {
        return "StudentProblem{" +
                "student=" + student.getId() +
                ", problem=" + problem.getId() +
                ", grade=" + grade +
                '}';
    }
}
