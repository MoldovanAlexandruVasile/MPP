package ro.ubb.socket.server.service;

import ro.ubb.socket.common.Domain.Assign;
import ro.ubb.socket.common.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AssignService {
    private Repository<Long, Assign> repository;

    public AssignService(Repository<Long, Assign> repository) {
        this.repository = repository;
    }


    public void addAssign(Assign assign) throws ValidatorException {
        repository.save(assign);
    }

    public void deleteAssign(Long id) throws ValidatorException {
        repository.delete(id);
    }

    public void updateAssign(Assign assign) throws ValidatorException {
        repository.update(assign);
    }

    public Set<Assign> getAllAssigns() {
        Iterable<Assign> assigns = repository.findAll();
        return StreamSupport.stream(assigns.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Assign> filterAssignsBySID(String s) {
        Iterable<Assign> assigns = repository.findAll();

        Set<Assign> filteredAssigns = new HashSet<>();
        assigns.forEach(filteredAssigns::add);
        filteredAssigns.removeIf(student -> !student.getSID().contains(s));

        return filteredAssigns;
    }

    public String mostAssigned() {
        Iterable<Assign> assigns = repository.findAll();
        List<String> problems = new ArrayList<>();
        problems = StreamSupport.stream(assigns.spliterator(), false).map(p -> p.getPID()).collect(Collectors.toList());
        Map<String, Long> counts = problems.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        return counts.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
    }
}
