package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Problem;
import ro.ubb.catalog.core.repository.ProblemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    private static final Logger log = LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    private ProblemRepository problemRepository;


    @Override
    public List<Problem> findAll() {
        log.trace("findAll --- method entered");

        List<Problem> problems = problemRepository.findAll();

        log.trace("findAll: problems={}", problems);

        return problems;
    }

    @Override
    public Problem findProblem(Long problemId) {
        log.trace("findProblem: problemId={}", problemId);

        Optional<Problem> problemOptional = problemRepository.findById(problemId);
        Problem problem = problemOptional.get();

        log.trace("findProblem: problem={}", problem);

        return problem;
    }
}
