package ro.ubb.remoting.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.remoting.common.*;
import ro.ubb.remoting.server.repository.AssignRepo;


import java.util.List;
import java.util.Optional;

@Service
public class AssignServiceServer implements AssignService {

   @Autowired
    private AssignRepo assignRepo;

    @Override
    public List<Assign> findAllAssigns() {
        System.out.println("getAll -- method entered");

        List<Assign> result = assignRepo.findAllAssigns();

        System.out.println("getAll: result = " + result);

        return result;
    }

    @Override
    public Assign findAssign(Long id) {

        System.out.println("findGrading: ID = " + id);

        Assign assign = assignRepo.findOne(id).get();

        System.out.println("findStudent: Grading = " + assign);

        return assign;

    }

    @Override
    public void addAssign(Assign assign) {
        System.out.println("addGrading: Grading = " + assign);

        assignRepo.save(assign);

        System.out.println("addGrading -- method finished");
    }

    @Override
    @Transactional
    public void updateAssign(Assign assign) {

        System.out.println("updateGrading: Grading = " + assign);

        Optional<Assign> gradingOptional = assignRepo.findOne(assign.getId());

        if (gradingOptional.isPresent()) {
            assignRepo.update(assign);
        }

        System.out.println("updateStudent: StudentOptional = " + gradingOptional);
    }

    @Override
    public void deleteAssign(Long id) {

        System.out.println("deleteGrading: ID = " + id);
        assignRepo.delete(id);
    }
}
