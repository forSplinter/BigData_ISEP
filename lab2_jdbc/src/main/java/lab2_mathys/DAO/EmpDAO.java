package lab2_mathys.DAO;

import lab2_mathys.model.Dept;
import lab2_mathys.model.Emp;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class EmpDAO implements DAO<Emp> {
    
    private final Connection conn;
    private final DeptDAO deptDAO;

    @Override
    public Emp findById(int id) {
        Emp emp = null;
        String query = "SELECT * FROM emp WHERE empno = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int empNo = rs.getInt("empno");
                String ename = rs.getString("ename");
                String efirst = rs.getString("efirst");
                String job = rs.getString("job");
                int mgrId = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                int sal  = rs.getInt("sal");
                int comm = rs.getInt("comm");
                int tel = rs.getInt("tel");
                int deptno = rs.getInt("deptno");
                
                Emp manager = null;
                if (mgrId != 0) {
                    manager = findById(mgrId);
                }
                Dept department = deptDAO.findById(deptno);

                emp = new Emp(empNo, ename, efirst, job, manager, hiredate, sal, comm, tel, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }



    @Override
    public List<Emp> findAll() {
        return List.of();
    }

    @Override
    public boolean create(Emp object) {
        return false;
    }

    @Override
    public boolean update(Emp object) {
        return false;
    }

    @Override
    public boolean delete(Emp object) {
        return false;
    }
}
