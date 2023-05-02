package com.hotel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class App extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/devops";
            String username = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, username, password);

            // Get the form data from the request
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String checkin = request.getParameter("checkin");
            String checkout = request.getParameter("checkout");
            String roomtype = request.getParameter("roomtype");
            String comments = request.getParameter("comments");

            // Insert the data into the database
            String sql = "INSERT INTO bookings (name, email, phone, checkin, checkout, roomtype, comments) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, checkin);
            pstmt.setString(5, checkout);
            pstmt.setString(6, roomtype);
            pstmt.setString(7, comments);
            pstmt.executeUpdate();

            // Redirect to a confirmation page
            response.sendRedirect("confirmation.html");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
