package com.isep;


import com.isep.dao.DeptDAO;
import com.isep.dao.EmpDAO;
import com.isep.model.Emp;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DeptDAO deptDAO = new DeptDAO();
        EmpDAO empDAO = new EmpDAO();

        Emp emp = empDAO.findById(7839);
        System.out.println(emp);

    }
}
