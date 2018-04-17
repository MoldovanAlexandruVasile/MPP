package ro.ubb.remoting.server.repository;

import ro.ubb.remoting.common.Assign;
import ro.ubb.remoting.common.Grading;
import ro.ubb.remoting.common.Problem;

import java.util.List;
import java.util.Optional;

public interface GradingRepo {
    List<Grading> findAllGradings();
    void save(Grading grading);
    void update(Grading grading);
    void delete(Long id);
    Optional<Grading> findOne(Long id);
}
