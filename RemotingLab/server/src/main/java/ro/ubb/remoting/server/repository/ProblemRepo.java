package ro.ubb.remoting.server.repository;

import ro.ubb.remoting.common.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemRepo {
    List<Problem> findAllProblems();
    void save(Problem problem);
    void update(Problem problem);
    void delete(Long id);
    Optional<Problem> findOne(Long id);
}
