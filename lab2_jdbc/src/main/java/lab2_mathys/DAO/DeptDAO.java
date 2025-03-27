package lab2_mathys.DAO;

import lab2_mathys.model.Dept;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class DeptDAO implements DAO<Dept> {

    private final Connection conn;


    @Override
    public Dept findById(int id) {
        Dept dept = null;
        String query = "SELECT deptno, dname, loc FROM dept WHERE deptno = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                dept = new Dept(
                        rs.getInt("deptno"),
                        rs.getString("dname"),
                            rs.getString("loc"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return dept;
    }

    @Override
    public List<Dept> findAll() {
        // ... implémentation ici si tu veux
        return List.of();
    }

    @Override
    public boolean create(Dept dept) {
        // ... implémentation ici
        return false;
    }

    @Override
    public boolean update(Dept dept) {
        // ... implémentation ici
        return false;
    }

    @Override
    public boolean delete(Dept dept) {
        // ... implémentation ici
        return false;
    }
}
