package ro.ubb.remoting.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.remoting.common.*;

import java.util.List;

public class GradingServiceClient implements GradingService {

    @Autowired
    GradingService gradingService;

    @Override
    public Grading findGrading(Long id) {
        return gradingService.findGrading(id);
    }

    @Override
    public void addGrading(Grading grading) {
        gradingService.addGrading(grading);
    }

    @Override
    public void updateGrading(Grading grading) {
        gradingService.updateGrading(grading);
    }

    @Override
    public void deleteGrading(Long id) {
        gradingService.deleteGrading(id);
    }

    @Override
    public List<Grading> findAllGradings() {
        return gradingService.findAllGradings();
    }
}