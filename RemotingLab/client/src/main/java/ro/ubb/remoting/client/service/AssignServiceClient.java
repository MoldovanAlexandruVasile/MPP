package ro.ubb.remoting.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.remoting.common.Assign;
import ro.ubb.remoting.common.AssignService;

import java.util.List;

public class AssignServiceClient implements AssignService {

    @Autowired
    AssignService assignService;

    @Override
    public Assign findAssign(Long id) {
        return assignService.findAssign(id);
    }

    @Override
    public void addAssign(Assign assign) {
        assignService.addAssign(assign);
    }

    @Override
    public void updateAssign(Assign assign) {
        assignService.updateAssign(assign);
    }

    @Override
    public void deleteAssign(Long id) {
        assignService.deleteAssign(id);
    }

    @Override
    public List<Assign> findAllAssigns() {
        return assignService.findAllAssigns();
    }
}