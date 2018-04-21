package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public List<Problem> getAllProblems() {
        log.trace("getAllProblems --- method entered");

        List<Problem> problems = problemRepository.findAll();

        log.trace("getAllProblems: problems={}", problems);

        return problems;
    }

    @Override
    public Problem createProblem(String description) {
        log.trace("saveProblem: description={}", description);

        Problem problem = problemRepository.save(new Problem(description));

        log.trace("saveProblem: problem={}", problem);

        return problem;
    }

    @Override
    @Transactional
    public Optional<Problem> updateProblem(Long problemID, String description) {
        log.trace("updateProblem: problemId={}, description={}", problemID, description);

        Optional<Problem> optionalProblem = problemRepository.findById(problemID);

        optionalProblem.ifPresent(st -> {
            st.setDescription(description);
        });

        log.trace("updateProblem: optionalProblem={}", optionalProblem);

        return optionalProblem;
    }

    @Override
    public void deleteProblem(Long id) {
        log.trace("deleteProblem: id={}", id);

        problemRepository.deleteById(id);

        log.trace("deleteProblem --- method finished");
    }
}
