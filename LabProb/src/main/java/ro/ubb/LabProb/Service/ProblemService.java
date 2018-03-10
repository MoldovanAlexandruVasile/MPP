package ro.ubb.LabProb.Service;

import ro.ubb.LabProb.Domain.Problem;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProblemService
{
    private Repository<Long, Problem> repository;

    public ProblemService(Repository<Long, Problem> repository) {
        this.repository = repository;
    }

    public void addProblem(Problem problem) throws ValidatorException { repository.save(problem); }

    public void deleteProblem(Long id) throws ValidatorException { repository.delete(id); }

    public void updateProblem(Problem problem) throws ValidatorException { repository.update(problem); }

    public Set<Problem> getAllProblems()
    {
        Iterable<Problem> students = repository.findAll();
        return StreamSupport.stream(students.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Problem> filterProblemsByDescr(String s)
    {
        Iterable<Problem> problems = repository.findAll();

        Set<Problem> filteredProblems = new HashSet<>();
        problems.forEach(filteredProblems::add);
        filteredProblems.removeIf(student -> !student.getDescription().contains(s));

        return filteredProblems;
    }
}
