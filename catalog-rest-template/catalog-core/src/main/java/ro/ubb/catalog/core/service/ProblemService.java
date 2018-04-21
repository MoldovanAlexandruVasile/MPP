package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemService {

    List<Problem> getAllProblems();

    Problem createProblem(String description);

    Optional<Problem> updateProblem(Long problemID, String description);

    void deleteProblem(Long id);
}
