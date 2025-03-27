package lab2_mathys.DAO;

import lab2_mathys.model.Dept;
import lab2_mathys.model.Emp;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;

@RequiredArgsConstructor
public class DAOFactory {
    private final Connection conn;

    public DAO<Dept> getDeptDao() {
        return new DeptDAO(conn);
    }

    public DAO<Emp> getEmpDao() {
        return new EmpDAO(conn, (DeptDAO) getDeptDao());
    }
}
