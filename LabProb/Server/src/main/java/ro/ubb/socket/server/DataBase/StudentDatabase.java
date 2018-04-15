package ro.ubb.socket.server.DataBase;

import ro.ubb.socket.common.Domain.Student;
import ro.ubb.socket.common.Domain.Validator.StudentValidator;
import ro.ubb.socket.common.Domain.Validator.Validator;
import ro.ubb.socket.common.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class StudentDatabase implements Repository<Long, Student> {

    private DataBaseRepository connection;
    private Validator<Student> studentValidator;

    public StudentDatabase(DataBaseRepository connection) {
        this.connection = connection;
        studentValidator = new StudentValidator();
    }

    @Override
    public Optional<Student> findOne(Long ID) {
        if (ID == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        try (PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Students WHERE ID=?")) {
            statement.setLong(1, ID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long StudentID = resultSet.getLong("ID");
                    String serialNumber = resultSet.getString("SerialNumber");
                    String name = resultSet.getString("Name");
                    Student student = new Student(serialNumber, name);
                    student.setId(StudentID);
                    return Optional.of(student);
                }
            }
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Student> findAll() throws ValidatorException{
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Students");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long studentID = resultSet.getLong("ID");
                String serialNumber = resultSet.getString("SerialNumber");
                String name = resultSet.getString("Name");
                Student c = new Student(serialNumber, name);
                c.setId(studentID);
                students.add(c);
            }
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }

        return students;
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidatorException {
        studentValidator.validate(entity);
        try {
            Statement stmt = connection.getConnection().createStatement();
            String sql = "INSERT INTO  Students (ID,SerialNumber,name)"
                    + "VALUES ('" + entity.getId() + "','" + entity.getSerialNumber() + "','" + entity.getName() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Student> delete(Long ID) throws ValidatorException {
        if (ID == null) {
            throw new ValidatorException("ID must not be null");
        }
        Optional<Student> student = findOne(ID);

        if (!student.isPresent()) {
            return Optional.empty();
        }
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Students WHERE ID=?")
        ) {
            stmt.setLong(1, ID);
            stmt.executeUpdate();
            return student;
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public Optional<Student> update(Student entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }
        studentValidator.validate(entity);
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("UPDATE Students SET SerialNumber=?,Name=?  WHERE ID=?")

        ) {
            stmt.setString(1, entity.getSerialNumber());
            stmt.setString(2, entity.getName());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();

            return Optional.empty();
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
    }
}
