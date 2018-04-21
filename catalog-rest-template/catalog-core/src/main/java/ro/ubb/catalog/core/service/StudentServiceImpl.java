package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Student;
import ro.ubb.catalog.core.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        log.trace("getAllStudents --- method entered");

        List<Student> students = studentRepository.findAll();

        log.trace("getAllStudents: students={}", students);

        return students;
    }

    @Override
    public Student createStudent(String serialNumber, String name) {
        log.trace("saveStudent: serialNumber={}, name={}", serialNumber, name);

        Student student = studentRepository.save(new Student(serialNumber, name));

        log.trace("saveStudent: student={}", student);

        return student;
    }

    @Override
    @Transactional
    public Optional<Student> updateStudent(Long studentId, String serialNumber, String name) {
        log.trace("updateStudent: studentId={}, serialNumber={}, name={}", studentId, serialNumber, name);

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        optionalStudent.ifPresent(st -> {
            st.setName(name);
            st.setSerialNumber(serialNumber);
        });

        log.trace("updateStudent: optionalStudent={}", optionalStudent);

        return optionalStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        log.trace("deleteStudent: id={}", id);

        studentRepository.deleteById(id);

        log.trace("deleteStudent --- method finished");
    }


}
