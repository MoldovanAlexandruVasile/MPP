package ro.ubb.remoting.server.repository;

import ro.ubb.remoting.common.Assign;

import java.util.List;
import java.util.Optional;

public interface AssignRepo {
    List<Assign> findAllAssigns();
    void save(Assign assign);
    void update(Assign assign);
    void delete(Long id);
    Optional<Assign> findOne(Long id);
}
