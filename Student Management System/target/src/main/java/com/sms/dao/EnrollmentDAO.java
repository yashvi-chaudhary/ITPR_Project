package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sms.model.Enrollment;
import com.sms.util.DBConnection;

public class EnrollmentDAO {

    // ADD enrollment
    public void addEnrollment(Enrollment e) {

        String sql = "INSERT INTO enrollment (student_id, course_id, enroll_date, status) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getStudentId());
            ps.setInt(2, e.getCourseId());
            ps.setDate(3, e.getEnrollDate());
            ps.setString(4, e.getStatus());

            ps.executeUpdate();
            System.out.println("Enrollment added successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // VIEW enrollments
    public void getAllEnrollments() {

        String sql =
            "SELECT e.enrollment_id, s.first_name, c.course_name, e.enroll_date, e.status " +
            "FROM enrollment e " +
            "JOIN student s ON e.student_id = s.student_id " +
            "JOIN course c ON e.course_id = c.course_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- ENROLLMENT LIST -----");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("enrollment_id") + " | " +
                    rs.getString("first_name") + " | " +
                    rs.getString("course_name") + " | " +
                    rs.getDate("enroll_date") + " | " +
                    rs.getString("status")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // DELETE enrollment
    public void deleteEnrollment(int enrollmentId) {

        String sql = "DELETE FROM enrollment WHERE enrollment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Enrollment deleted successfully!");
            } else {
                System.out.println("Enrollment ID not found!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
