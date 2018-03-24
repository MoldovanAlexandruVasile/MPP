package ro.ubb.LabProb.Repository.DataBase;

import ro.ubb.LabProb.Domain.Assign;
import ro.ubb.LabProb.Domain.Validator.AssignValidator;
import ro.ubb.LabProb.Domain.Validator.Validator;
import ro.ubb.LabProb.Domain.Validator.ValidatorException;
import ro.ubb.LabProb.Repository.DataBaseRepository;
import ro.ubb.LabProb.Repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AssignDatabase implements Repository<Long, Assign> {

    private DataBaseRepository connection;
    private Validator<Assign> assignValidator;

    public AssignDatabase(DataBaseRepository connection) {
        this.connection = connection;
        assignValidator = new AssignValidator();
    }


    @Override
    public Optional<Assign> findOne(Long ID) throws ValidatorException{
        if (ID == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        try (PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Assigns WHERE ID=?")) {
            statement.setLong(1, ID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long assignID = resultSet.getLong("ID");
                    String SID = resultSet.getString("SID");
                    String PID = resultSet.getString("PID");
                    Assign assign = new Assign(SID, PID);
                    assign.setId(assignID);
                    return Optional.of(assign);
                }
            }
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Assign> findAll() throws ValidatorException{
        List<Assign> assigns = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM Assigns");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long assignID = resultSet.getLong("ID");
                String SID = resultSet.getString("SID");
                String PID = resultSet.getString("PID");
                Assign assign = new Assign(SID, PID);
                assign.setId(assignID);
                assigns.add(assign);
            }
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }

        return assigns;
    }

    @Override
    public Optional<Assign> save(Assign entity) throws ValidatorException {
        assignValidator.validate(entity);
        try {
            Statement stmt = connection.getConnection().createStatement();
            String sql = "INSERT INTO  Assigns (ID,SID,PID)"
                    + "VALUES ('" + entity.getId() + "','" + entity.getSID() + "','" + entity.getPID() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Assign> delete(Long ID) throws ValidatorException {
        if (ID == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        Optional<Assign> assign = findOne(ID);

        if (!assign.isPresent()) {
            return Optional.empty();
        }
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Assigns WHERE ID=?")
        ) {
            stmt.setLong(1, ID);
            stmt.executeUpdate();
            return assign;
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
    }

    @Override
    public Optional<Assign> update(Assign entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }
        assignValidator.validate(entity);
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("UPDATE Assigns SET SID=?,PID=?  WHERE ID=?")

        ) {
            stmt.setString(1, entity.getSID());
            stmt.setString(2, entity.getPID());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new ValidatorException(e.getMessage());
        }
    }
}
