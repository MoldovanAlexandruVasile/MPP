package ro.ubb.remoting.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ro.ubb.remoting.common.Assign;
import ro.ubb.remoting.common.Problem;

import java.util.List;
import java.util.Optional;

@Repository
public class AssignRepositoryImpl implements AssignRepo{
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Assign> findOne(Long id) {

        String sql = "SELECT * FROM assigns WHERE ID=?";
        return
                Optional.ofNullable(jdbcOperations.queryForObject(sql, new Object[]{id}, (rs, i) -> {
                    return new Assign(rs.getString("sid"), rs.getString("pid"));
                }));
    }

    @Override
    public List<Assign> findAllAssigns() {
        String sql = "SELECT * FROM assigns";
        return
                jdbcOperations.query(sql, (rs, i) -> {
                    Long ID = Long.valueOf(rs.getInt("id"));
                    String sid = rs.getString("sid");
                    String pid = rs.getString("pid");
                    Assign assign=new Assign(sid,pid);
                    assign.setId(ID);
                    return assign;
                });
    }

    @Override
    public void save(Assign assign) {
        String sql = "INSERT INTO assigns (id, sid, pid) VALUES (?,?,?)";
        jdbcOperations.update(sql, assign.getId(), assign.getSID(), assign.getPID());
    }

    @Override
    public void update(Assign assign) {
        String sql = "UPDATE Students SET sid=?, pid=? WHERE id=?";
        jdbcOperations.update(sql, assign.getSID(), assign.getPID(), Integer.valueOf(assign.getId().toString()));
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM assigns WHERE ID = ?";
        jdbcOperations.update(sql, id);
    }
}