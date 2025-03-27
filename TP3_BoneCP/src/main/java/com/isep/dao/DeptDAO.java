package com.isep.dao;

import com.isep.pool.BoneCPConnectionFactory;
import com.isep.model.Dept;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class DeptDAO implements DAO<Dept> {

    private final Connection conn;

    public DeptDAO() throws SQLException {
        this.conn = BoneCPConnectionFactory.getInstance().getConnection();
    }


    @Override
    public Dept findById(int id) {
        String sql = "SELECT * FROM dept WHERE deptno = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Dept(
                        rs.getInt("deptno"),
                        rs.getString("dname"),
                        rs.getString("loc")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dept> findAll() {
        return List.of();
    }

    @Override
    public boolean create(Dept object) {
        return false;
    }

    @Override
    public boolean update(Dept object) {
        return false;
    }

    @Override
    public boolean delete(Dept object) {
        return false;
    }

}
