package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> findAll();
}
