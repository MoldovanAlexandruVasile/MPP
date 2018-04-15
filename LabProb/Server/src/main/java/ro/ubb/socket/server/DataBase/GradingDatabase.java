package ro.ubb.socket.server.DataBase;

import ro.ubb.socket.common.Domain.Grading;
import ro.ubb.socket.common.Domain.Validator.GradingValidator;
import ro.ubb.socket.common.Domain.Validator.Validator;
import ro.ubb.socket.common.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GradingDatabase implements Repository<Long, Grading> {

    private DataBaseRepository connection;
    private Validator<Grading> gradingValidator;

    public GradingDatabase(DataBaseRepository connection) {
        this.connection = connection;
        gradingValidator = new GradingValidator();
    }


    @Override
    public Optional<Grading> findOne(Long ID) throws ValidatorException{
        if (ID == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        try (PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Gradings WHERE ID=?")) {
            statement.setLong(1, ID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long gradingID = resultSet.getLong("ID");
                    String AID = resultSet.getString("AID");
                    int grade = resultSet.getInt("Grade");
                    Grading grading = new Grading(AID, grade);
                    grading.setId(gradingID);
                    return Optional.of(grading);
                }
            }
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Grading> findAll() throws ValidatorException{
        List<Grading> gradings = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Gradings");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long gradingID = resultSet.getLong("ID");
                String AID = resultSet.getString("AID");
                int grade = resultSet.getInt("Grade");
                Grading grading = new Grading(AID, grade);
                grading.setId(gradingID);
                gradings.add(grading);
            }
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }

        return gradings;
    }

    @Override
    public Optional<Grading> save(Grading entity) throws ValidatorException {
        gradingValidator.validate(entity);
        try {
            Statement stmt = connection.getConnection().createStatement();
            String sql = "INSERT INTO  Gradings (ID,AID,Grade)"
                    + "VALUES ('" + entity.getId() + "','" + entity.getAID() + "','" + entity.getGrade() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Grading> delete(Long ID) throws ValidatorException {
        if (ID == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        Optional<Grading> grading = findOne(ID);

        if (!grading.isPresent()) {
            return Optional.empty();
        }
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Gradings WHERE ID=?")
        ) {
            stmt.setLong(1, ID);
            stmt.executeUpdate();
            return grading;
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public Optional<Grading> update(Grading entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }
        gradingValidator.validate(entity);
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("UPDATE Gradings SET AID=?,Grade=?  WHERE ID=?")

        ) {
            stmt.setString(1, entity.getAID());
            stmt.setInt(2, entity.getGrade());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

}
