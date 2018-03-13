package ro.ubb.LabProb.Service;

import ro.ubb.LabProb.Domain.Grading;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GradingService
{
    private Repository<Long,Grading> repository;
    public GradingService(Repository<Long,Grading> repo)
    {
        repository=repo;
    }

    public void addGrading(Grading gr)throws ValidatorException { repository.save(gr); }

    public void deleteGrading(Long id)throws ValidatorException { repository.delete(id); }

    public void updateGrading(Grading gr) throws ValidatorException {repository.update(gr); }

    public Set<Grading> getAllGradings()
    {
        Iterable<Grading> gradings = repository.findAll();
        return StreamSupport.stream(gradings.spliterator(),false).collect(Collectors.toSet());
    }

    public Set<Grading> filterGradingsByAID(String s)
    {
        Iterable<Grading> gradings = repository.findAll();
        Set<Grading> filteredGradings = new HashSet<>();
        gradings.forEach(filteredGradings::add);
        filteredGradings.removeIf(assign -> !assign.getAID().contains(s));
        return filteredGradings;
    }

}
