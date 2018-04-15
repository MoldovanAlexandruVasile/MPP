package ro.ubb.remoting.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.remoting.common.Student;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepo {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Student> findOne(Long id) {

        String sql = "SELECT * FROM Students WHERE ID=?";
        return
                Optional.ofNullable(jdbcOperations.queryForObject(sql, new Object[]{id}, (rs, i) -> {
                    return new Student(rs.getString("SerialNumber"), rs.getString("Name"));
                }));
    }

    @Override
    public List<Student> findAllStudents() {
        String sql = "SELECT * FROM Students";
        return
                jdbcOperations.query(sql, (rs, i) -> {
                    Long ID = Long.valueOf(rs.getInt("ID"));
                    String serialNumber = rs.getString("SerialNumber");
                    String name = rs.getString("Name");
                    Student student = new Student(serialNumber, name);
                    student.setId(ID);
                    return student;
                });
    }

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO Students (ID, SerialNumber, Name) VALUES (?,?,?)";
        jdbcOperations.update(sql, student.getId(), student.getSerialNumber(), student.getName());
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE Students SET SerialNumber=?, Name=? WHERE ID=?";
        jdbcOperations.update(sql, student.getSerialNumber(), student.getName(), Integer.valueOf(student.getId().toString()));
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Students WHERE ID = ?";
        jdbcOperations.update(sql, id);
    }
}
