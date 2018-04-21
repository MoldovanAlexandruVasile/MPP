package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Problem;
import ro.ubb.catalog.core.service.ProblemService;
import ro.ubb.catalog.web.converter.ProblemConverter;
import ro.ubb.catalog.web.dto.EmptyJsonResponse;
import ro.ubb.catalog.web.dto.ProblemDto;
import ro.ubb.catalog.web.dto.ProblemsDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProblemController {

    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemConverter problemConverter;


    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    public ProblemsDto getProblems() {
        log.trace("getProblems");

        List<Problem> problems = problemService.getAllProblems();

        log.trace("getProblems: problems={}", problems);

        return new ProblemsDto(problemConverter.convertModelsToDtos(problems));
    }


    @RequestMapping(value = "/problems/{id}", method = RequestMethod.PUT)
    public ProblemDto updateProblem(
            @PathVariable final Long id,
            @RequestBody final ProblemDto problemDto) {
        log.trace("updateProblem: id={}, problemDtoMap={}", id, problemDto);

        Optional<Problem> problemOptional = problemService.updateProblem(id, problemDto.getDescription());

        Map<String, ProblemDto> result = new HashMap<>();
        problemOptional.ifPresentOrElse(
                problem -> result.put("problem", problemConverter.convertModelToDto(problem)),
                () -> result.put("problem", problemConverter.convertModelToDto(new Problem())));

        log.trace("updateProblem: result={}", result);

        return result.get("problem");
    }


    @RequestMapping(value = "/problems", method = RequestMethod.POST)
    public ProblemDto createProblem(
            @RequestBody final ProblemDto problemDto) {
        log.trace("createProblem: problemDtoMap={}", problemDto);

        Problem problem = problemService.createProblem(problemDto.getDescription());

        ProblemDto result = problemConverter.convertModelToDto(problem);

        log.trace("createProblem: result={}", result);
        return result;
    }


    @RequestMapping(value = "problems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProblem(@PathVariable final Long id) {
        log.trace("deleteProblem: problemId={}", id);

        problemService.deleteProblem(id);

        log.trace("deleteProblem - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
