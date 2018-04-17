package ro.ubb.remoting.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.remoting.common.Problem;
import ro.ubb.remoting.common.ProblemService;
import ro.ubb.remoting.server.repository.ProblemRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceServer implements ProblemService {

    @Autowired
    private ProblemRepo problemRepo;

    @Override
    public List<Problem> findAllProblems() {
        System.out.println("getAll -- method entered");

        List<Problem> result = problemRepo.findAllProblems();

        System.out.println("getAll: result = " + result);

        return result;
    }

    @Override
    public Problem findProblem(Long id) {

        System.out.println("findProblem: ID = " + id);

        Problem problem = problemRepo.findOne(id).get();

        System.out.println("findProblem: Problem = " + problem);

        return problem;

    }

    @Override
    public void addProblem(Problem problem) {
        System.out.println("addProblem: Problem = " + problem);

        problemRepo.save(problem);

        System.out.println("addProblem -- method finished");
    }

    @Override
    @Transactional
    public void updateProblem(Problem problem) {

        System.out.println("updateProblem: Problem = " + problem);

        Optional<Problem> problemOptional = problemRepo.findOne(problem.getId());

        if (problemOptional.isPresent()) {
            problemRepo.update(problem);
        }

        System.out.println("updateProblem: ProblemOptional = " + problemOptional);
    }

    @Override
    public void deleteProblem(Long id) {

        System.out.println("deleteProblem: ID = " + id);
        problemRepo.delete(id);
    }
}
