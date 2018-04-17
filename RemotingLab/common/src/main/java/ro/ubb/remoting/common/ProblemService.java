package ro.ubb.remoting.common;

import java.util.List;

public interface ProblemService {

    List<Problem> findAllProblems();
    Problem findProblem(Long id);
    void addProblem(Problem problem);
    void updateProblem(Problem problem);
    void deleteProblem(Long id);
}
