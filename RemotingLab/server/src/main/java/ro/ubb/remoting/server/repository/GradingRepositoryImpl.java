package ro.ubb.remoting.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.remoting.common.Assign;
import ro.ubb.remoting.common.Grading;
import ro.ubb.remoting.common.Problem;

import java.util.List;
import java.util.Optional;

public class GradingRepositoryImpl implements GradingRepo{
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Grading> findOne(Long id) {

        String sql = "SELECT * FROM gradings WHERE ID=?";
        return
                Optional.ofNullable(jdbcOperations.queryForObject(sql, new Object[]{id}, (rs, i) -> {
                    return new Grading(rs.getString("aid"), rs.getInt("grade"));
                }));
    }

    @Override
    public List<Grading> findAllGradings() {
        String sql = "SELECT * FROM gradings";
        return
                jdbcOperations.query(sql, (rs, i) -> {
                    Long ID = Long.valueOf(rs.getInt("id"));
                    String aid = rs.getString("aid");
                    int grade=rs.getInt("grade");
                    Grading grading=new Grading(aid,grade);
                    grading.setId(ID);
                    return grading;
                });
    }

    @Override
    public void save(Grading grading) {
        String sql = "INSERT INTO gradings (id, aid, grade) VALUES (?,?,?)";
        jdbcOperations.update(sql, grading.getId(), grading.getAID(), grading.getGrade());
    }

    @Override
    public void update(Grading grading) {
        String sql = "UPDATE gradings SET aid=?, grade=? WHERE id=?";
        jdbcOperations.update(sql, grading.getAID(),grading.getGrade(), grading.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM gradings WHERE ID = ?";
        jdbcOperations.update(sql, id);
    }
}