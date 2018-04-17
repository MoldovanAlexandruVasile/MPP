package ro.ubb.remoting.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.remoting.common.Grading;
import ro.ubb.remoting.common.GradingService;
import ro.ubb.remoting.common.StudentService;
import ro.ubb.remoting.common.Student;
import ro.ubb.remoting.server.repository.GradingRepo;
import ro.ubb.remoting.server.repository.StudentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class GradingServiceServer implements GradingService {

    @Autowired
    private GradingRepo gradingRepo;

    @Override
    public List<Grading> findAllGradings() {
        System.out.println("getAll -- method entered");

        List<Grading> result = gradingRepo.findAllGradings();

        System.out.println("getAll: result = " + result);

        return result;
    }

    @Override
    public Grading findGrading(Long id) {

        System.out.println("findGrading: ID = " + id);

        Grading grading = gradingRepo.findOne(id).get();

        System.out.println("findStudent: Grading = " + grading);

        return grading;

    }

    @Override
    public void addGrading(Grading grading) {
        System.out.println("addGrading: Grading = " + grading);

        gradingRepo.save(grading);

        System.out.println("addGrading -- method finished");
    }

    @Override
    @Transactional
    public void updateGrading(Grading grading) {

        System.out.println("updateGrading: Grading = " + grading);

        Optional<Grading> gradingOptional = gradingRepo.findOne(grading.getId());

        if (gradingOptional.isPresent()) {
           gradingRepo.update(grading);
        }

        System.out.println("updateStudent: StudentOptional = " + gradingOptional);
    }

    @Override
    public void deleteGrading(Long id) {

        System.out.println("deleteGrading: ID = " + id);
       gradingRepo.delete(id);
    }
}
