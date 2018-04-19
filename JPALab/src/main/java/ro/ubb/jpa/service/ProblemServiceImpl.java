package ro.ubb.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.jpa.domain.Problem;
import ro.ubb.jpa.repository.ProblemRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    private static final Logger LOG = LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    private ProblemRepo problemRepo;

    @Override
    public List<Problem> findAll() {
        LOG.trace("findAll --- method entered");

        List<Problem> result = problemRepo.findAll();

        LOG.trace("findAll: result={}", result);

        return result;
    }

    @Override
    public void addProblem(Problem problem) {
        LOG.trace("addProblem: Problem={}", problem);

        problemRepo.save(problem);

        LOG.trace("addProblem --- method finished");
    }

    @Override
    @Transactional
    public void updateProblem(Problem problem) {
        LOG.trace("updateProblem: Problem={}", problem);

        Optional<Problem> problemOptional = problemRepo.findById(problem.getId());
        if (problemOptional.isPresent()) {
            Problem prb = problemOptional.get();
            prb.setDescription(problem.getDescription());
        }

        LOG.trace("updateProblem: problemOptional={}", problemOptional);
    }

    @Override
    public void deleteProblem(Integer id) {
        LOG.trace("deleteProblem: ID = {}", id);

        problemRepo.deleteById(id);

        LOG.trace("deleteProblem --- method finished");
    }
}
