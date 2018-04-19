package ro.ubb.jpa.service;

import ro.ubb.jpa.domain.Problem;

import java.util.List;

public interface ProblemService {

    List<Problem> findAll();

    void addProblem(Problem problem);

    void updateProblem(Problem problem);

    void deleteProblem(Integer id);
}
