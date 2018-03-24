package ro.ubb.LabProb.Repository.DataBase;

import ro.ubb.LabProb.Domain.Problem;
import ro.ubb.LabProb.Domain.Validator.ProblemValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.DataBaseRepository;
import ro.ubb.LabProb.Repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ProblemDatabase implements Repository<Long, Problem> {

    private DataBaseRepository connection;
    private Validator<Problem> problemValidator;

    public ProblemDatabase(DataBaseRepository connection) {
        this.connection = connection;
        problemValidator = new ProblemValidator();
    }


    @Override
    public Optional<Problem> findOne(Long ID) throws ValidatorException{
        if (ID == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        try (PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Problems WHERE ID=?")) {
            statement.setLong(1, ID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long problemID = resultSet.getLong("ID");
                    String description = resultSet.getString("Description");
                    Problem problem = new Problem(description);
                    problem.setId(problemID);
                    return Optional.of(problem);
                }
            }
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Problem> findAll() throws ValidatorException{
        List<Problem> problems = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Problems");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long problemID = resultSet.getLong("ID");
                String description = resultSet.getString("Description");
                Problem c = new Problem(description);
                c.setId(problemID);
                problems.add(c);
            }
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }

        return problems;
    }

    @Override
    public Optional<Problem> save(Problem entity) throws ValidatorException {
        problemValidator.validate(entity);
        try {
            Statement stmt = connection.getConnection().createStatement();
            String sql = "INSERT INTO  Problems (ID,Description)"
                    + "VALUES ('" + entity.getId() + "','" + entity.getDescription() + "');";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Problem> delete(Long ID) throws ValidatorException {
        if (ID == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        Optional<Problem> problem = findOne(ID);

        if (!problem.isPresent()) {
            return Optional.empty();
        }
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Problems WHERE ID=?")
        ) {
            stmt.setLong(1, ID);
            stmt.executeUpdate();
            return problem;
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public Optional<Problem> update(Problem entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }
        problemValidator.validate(entity);
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("UPDATE Problems SET Description=?  WHERE ID=?")

        ) {
            stmt.setString(1, entity.getDescription());
            stmt.setLong(2, entity.getId());
            stmt.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
    }
}
