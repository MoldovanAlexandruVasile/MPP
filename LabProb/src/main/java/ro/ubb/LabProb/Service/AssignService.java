package ro.ubb.LabProb.Service;

import ro.ubb.LabProb.Domain.Assign;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AssignService
{
    private Repository<Long, Assign> repository;

    public AssignService(Repository<Long, Assign> repository)
    {
        this.repository = repository;
    }

    public void addAssign(Assign assign) throws ValidatorException { repository.save(assign); }

    public void deleteAssign(Long id) throws ValidatorException { repository.delete(id); }

    public void updateAssign(Assign assign) throws ValidatorException { repository.update(assign); }

    public Set<Assign> getAllAssigns()
    {
        Iterable<Assign> assigns = repository.findAll();
        return StreamSupport.stream(assigns.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Assign> filterAssignsBySID(String s)
    {
        Iterable<Assign> assigns = repository.findAll();

        Set<Assign> filteredAssigns = new HashSet<>();
        assigns.forEach(filteredAssigns::add);
        filteredAssigns.removeIf(student -> !student.getSID().contains(s));

        return filteredAssigns;
    }
}
