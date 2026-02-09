package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sms.model.Marks;
import com.sms.util.DBConnection;

public class MarksDAO {

    // ADD marks
    public void addMarks(Marks m) {

        String sql = "INSERT INTO marks (student_id, course_id, subject, marks_obtained, total_marks, grade, exam_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getStudentId());
            ps.setInt(2, m.getCourseId());
            ps.setString(3, m.getSubject());
            ps.setInt(4, m.getMarksObtained());
            ps.setInt(5, m.getTotalMarks());
            ps.setString(6, m.getGrade());
            ps.setDate(7, m.getExamDate());

            ps.executeUpdate();
            System.out.println("Marks added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW all marks
    public void getAllMarks() {

        String sql =
            "SELECT m.marks_id, s.first_name, c.course_name, m.subject, m.marks_obtained, m.total_marks, m.grade " +
            "FROM marks m " +
            "JOIN student s ON m.student_id = s.student_id " +
            "JOIN course c ON m.course_id = c.course_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- MARKS LIST -----");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("marks_id") + " | " +
                    rs.getString("first_name") + " | " +
                    rs.getString("course_name") + " | " +
                    rs.getString("subject") + " | " +
                    rs.getInt("marks_obtained") + "/" +
                    rs.getInt("total_marks") + " | Grade: " +
                    rs.getString("grade")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE marks
    public void deleteMarks(int marksId) {

        String sql = "DELETE FROM marks WHERE marks_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, marksId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Marks deleted successfully!");
            } else {
                System.out.println("Marks ID not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
