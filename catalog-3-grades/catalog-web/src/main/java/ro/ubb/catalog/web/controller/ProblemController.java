package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.model.Problem;
import ro.ubb.catalog.core.service.ProblemService;
import ro.ubb.catalog.web.converter.ProblemConverter;
import ro.ubb.catalog.web.dto.ProblemDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProblemController {

    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemConverter problemConverter;


    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    public List<ProblemDto> getProblems() {
        log.trace("getProblems");

        List<Problem> problems = problemService.findAll();

        log.trace("getProblems: problems={}", problems);

        return new ArrayList<>(problemConverter.convertModelsToDtos(problems));
    }
}
