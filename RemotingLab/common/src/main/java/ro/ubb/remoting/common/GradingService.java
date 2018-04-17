package ro.ubb.remoting.common;

import java.util.List;

public interface GradingService {

    List<Grading> findAllGradings();
    Grading findGrading(Long id);
    void addGrading(Grading grading);
    void updateGrading(Grading grading);
    void deleteGrading(Long id);
}
