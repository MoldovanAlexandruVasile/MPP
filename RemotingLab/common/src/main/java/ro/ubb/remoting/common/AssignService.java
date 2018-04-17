package ro.ubb.remoting.common;

import java.util.List;

public interface AssignService {

    List<Assign> findAllAssigns();
    Assign findAssign(Long id);
    void addAssign(Assign assign);
    void updateAssign(Assign assign);
    void deleteAssign(Long id);
}
