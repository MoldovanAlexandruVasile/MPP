package ro.ubb.remoting.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.remoting.common.Problem;
import ro.ubb.remoting.common.ProblemService;

import java.util.List;

public class ProblemServiceClient implements ProblemService {

    @Autowired
    ProblemService problemService;

    @Override
    public Problem findProblem(Long id) {
        return problemService.findProblem(id);
    }

    @Override
    public void addProblem(Problem problem) {
        problemService.addProblem(problem);
    }

    @Override
    public void updateProblem(Problem problem) {
        problemService.updateProblem(problem);
    }

    @Override
    public void deleteProblem(Long id) {
        problemService.deleteProblem(id);
    }

    @Override
    public List<Problem> findAllProblems() {
        return problemService.findAllProblems();
    }
}