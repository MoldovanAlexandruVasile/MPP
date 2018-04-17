package ro.ubb.remoting.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.remoting.common.Problem;

import java.util.List;
import java.util.Optional;

public class ProblemRepositoryImpl implements ProblemRepo{
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Problem> findOne(Long id) {

        String sql = "SELECT * FROM Problems WHERE ID=?";
        return
                Optional.ofNullable(jdbcOperations.queryForObject(sql, new Object[]{id}, (rs, i) -> {
                    return new Problem(rs.getString("Description"));
                }));
    }

    @Override
    public List<Problem> findAllProblems() {
        String sql = "SELECT * FROM Problems";
        return
                jdbcOperations.query(sql, (rs, i) -> {
                    Long ID = Long.valueOf(rs.getInt("ID"));
                    String description = rs.getString("Description");
                    Problem problem = new Problem(description);
                    problem.setId(ID);
                    return problem;
                });
    }

    @Override
    public void save(Problem problem) {
        String sql = "INSERT INTO Problems (ID, Description) VALUES (?,?)";
        jdbcOperations.update(sql, problem.getId(), problem.getDescription());
    }

    @Override
    public void update(Problem problem) {
        String sql = "UPDATE Problems SET Description=? WHERE ID=?";
        jdbcOperations.update(sql, problem.getDescription(), Integer.valueOf(problem.getId().toString()));
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Problems WHERE ID = ?";
        jdbcOperations.update(sql, id);
    }
}
