package lab2_mathys;
import lab2_mathys.DAO.DAO;
import lab2_mathys.DAO.DAOFactory;
import lab2_mathys.DAO.DeptDAO;
import lab2_mathys.DAO.EmpDAO;
import lab2_mathys.model.Dept;
import lab2_mathys.model.Emp;

import java.sql.*;


public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/lab_bigdata";
        String user = "forsplinter";
        String password = "postgres";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL database");

            DAOFactory daoFactory = new DAOFactory(conn);
            DAO<Dept> deptDAO = daoFactory.getDeptDao();
            Dept dept20 = deptDAO.findById(20);
            DAO<Emp> empDao = daoFactory.getEmpDao();
            Emp emp20 = empDao.findById(7369);

            if (dept20 != null) {
                System.out.println(dept20);
            }else{
                System.out.println("No Dept Found");
            }
            if (emp20 != null) {
                System.out.println(emp20);
            }else{
                System.out.println("No Emp Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignore) {
                    ignore.printStackTrace();
                }
            }
        }
    }

    // JDBC method here
    public static void displayDepartement(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT deptno, dname, loc FROM dept");

        while (rs.next()) {
            int deptno = rs.getInt("deptno");
            String dname = rs.getString("dname");
            String loc = rs.getString("loc");
            System.out.println("Departement : " + deptno + " is for  " + dname +" and located in "+ loc);
        }
        rs.close();
    }
    public static void moveDepartement(Connection conn, int newDeptno, int empno) throws SQLException {
        String sql = "UPDATE emp SET deptno = ? WHERE empno = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newDeptno);
            pstmt.setInt(2,empno);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0){
                System.out.println("Departement updated " + empno + " to departement " + newDeptno);
            }else {
                System.out.println("Departement not updated");
            }
        }
    }
    public static void displayTable(Connection conn, String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();

            for (int i = 1; i <= cols ; i++){
                if (i > 1)System.out.print(" | ");
                System.out.print(rsmd.getColumnName(i));
            }
            System.out.println();

            while (rs.next()){
                for (int i = 1; i <= cols ; i++){
                    if (i > 1)System.out.print(" | ");
                    System.out.print(rs.getObject(i));
                }
                System.out.println();
            }
        }
    }
}