package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Problem;
import ro.ubb.catalog.core.model.Student;
import ro.ubb.catalog.core.model.StudentProblem;
import ro.ubb.catalog.core.service.ProblemService;
import ro.ubb.catalog.core.service.StudentService;
import ro.ubb.catalog.web.converter.StudentConverter;
import ro.ubb.catalog.web.converter.StudentProblemConverter;
import ro.ubb.catalog.web.dto.StudentDto;
import ro.ubb.catalog.web.dto.StudentProblemDto;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
public class GradesController {
    private static final Logger log = LoggerFactory.getLogger(GradesController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentProblemConverter studentProblemConverter;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    public List<StudentDto> getStudents() {
        log.trace("getStudents");

        List<Student> students = studentService.findAll();

        log.trace("getStudents: students={}", students);

        return new ArrayList<>(studentConverter.convertModelsToDtos(students));
    }

    @RequestMapping(value = "/grades/{studentId}", method = RequestMethod.GET)
    public Set<StudentProblemDto> getStudentProblems(
            @PathVariable final Long studentId) {
        log.trace("getStudentProblems: studentId={}", studentId);

        Optional<Student> studentOptional = studentService.findStudent(studentId);
        Set<StudentProblemDto> result = new HashSet<>();
        studentOptional.ifPresent(student -> {
            Set<StudentProblem> studentProblems = student.getStudentProblems();
            result.addAll(studentProblemConverter
                    .convertModelsToDtos(studentProblems));
        });

        log.trace("getStudentProblems: result={}", result);

        return result;
    }

    @RequestMapping(value = "/grades/{studentId}", method = RequestMethod.PUT)
    public Set<StudentProblemDto> updateStudentGrades(
            @PathVariable final Long studentId,
            @RequestBody final Set<StudentProblemDto> studentProblemDtos) {
        log.trace("updateStudentGrades: studentId={}, studentProblemDtos={}",
                studentId, studentProblemDtos);

        Map<Long, Integer> grades = new HashMap<>();
        studentProblemDtos.forEach(sd ->
                grades.put(sd.getProblemId(), sd.getGrade()));

        Optional<Student> studentOptional = studentService.updateStudentGrades(studentId, grades);

        Set<StudentProblemDto> result = new HashSet<>();
        studentOptional.ifPresent(student -> {
            result.addAll(studentProblemConverter.
                    convertModelsToDtos(student.getStudentProblems()));
        });

        log.trace("getStudentProblems: result={}", result);

        return result;
    }
}
