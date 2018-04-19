package ro.ubb.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.jpa.domain.Student;
import ro.ubb.jpa.repository.StudentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> findAll() {
        LOG.trace("findAll --- method entered");

        List<Student> result = studentRepo.findAll();

        LOG.trace("findAll: result={}", result);

        return result;
    }

    @Override
    public void addStudent(Student student) {
        LOG.trace("addStudent: Student={}", student);

        studentRepo.save(student);

        LOG.trace("addStudent --- method finished");
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        LOG.trace("updateStudent: Student={}", student);

        Optional<Student> studentOptional = studentRepo.findById(student.getId());
        if (studentOptional.isPresent()) {
            Student std = studentOptional.get();
            std.setName(student.getName());
            std.setSerialNumber(student.getSerialNumber());
        }

        LOG.trace("updateStudent: studentOptional={}", studentOptional);
    }

    @Override
    public void deleteStudent(Integer id) {
        LOG.trace("deleteStudent: ID = {}", id);

        studentRepo.deleteById(id);

        LOG.trace("deleteStudent --- method finished");
    }
}
