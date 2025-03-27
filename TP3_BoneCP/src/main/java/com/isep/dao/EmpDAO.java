package com.isep.dao;

import com.isep.pool.BoneCPConnectionFactory;
import com.isep.model.Dept;
import com.isep.model.Emp;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class EmpDAO implements DAO<Emp> {

    private final Connection conn;
    private final DeptDAO deptDAO;

    public EmpDAO() throws SQLException {
        this.conn = BoneCPConnectionFactory.getInstance().getConnection();
        this.deptDAO = new DeptDAO();
    }

    private final Map<Integer, Emp> cache = new HashMap<>(); // for ensure recursion

    @Override
    public Emp findById(int id) {
        if (cache.containsKey(id)) return cache.get(id);

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
                int sal = rs.getInt("sal");
                int comm = rs.getInt("comm");
                int tel = rs.getInt("tel");
                int deptno = rs.getInt("deptno");

                Emp manager = (mgrId != 0 && mgrId != id) ? findById(mgrId) : null;
                Dept department = deptDAO.findById(deptno);

                emp = new Emp(empNo, ename, efirst, job, manager, hiredate, sal, comm, tel, department);
                cache.put(id, emp);
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
