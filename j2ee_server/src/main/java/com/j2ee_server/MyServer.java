package com.j2ee_server;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/M")
public class MyServer {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                out.println("<html><body>");
                out.println("<h2>Departments</h2>");
                displayDepartement(conn, out);
                out.println("<hr/>");
                out.println("<h2>Employees</h2>");
                displayTable(conn, "emp", out);
                out.println("</body></html>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }

    private void displayDepartement(Connection conn, PrintWriter out) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT deptno, dname, loc FROM dept");
        out.println("<table border='1'><tr><th>Deptno</th><th>Dname</th><th>Location</th></tr>");
        while (rs.next()) {
            out.println("<tr><td>" + rs.getInt("deptno") + "</td><td>" +
                    rs.getString("dname") + "</td><td>" +
                    rs.getString("loc") + "</td></tr>");
        }
        out.println("</table>");
    }

    private void displayTable(Connection conn, String tableName, PrintWriter out) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();

        out.println("<table border='1'><tr>");
        for (int i = 1; i <= cols; i++) {
            out.print("<th>" + meta.getColumnName(i) + "</th>");
        }
        out.println("</tr>");

        while (rs.next()) {
            out.println("<tr>");
            for (int i = 1; i <= cols; i++) {
                out.print("<td>" + rs.getObject(i) + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
    }
}
