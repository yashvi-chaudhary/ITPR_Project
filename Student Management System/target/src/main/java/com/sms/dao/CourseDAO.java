package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sms.model.Course;
import com.sms.util.DBConnection;

public class CourseDAO {

    // ADD course
    public void addCourse(Course course) {

        String sql = "INSERT INTO course (course_name, course_code, duration, fee) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getDuration());
            ps.setDouble(4, course.getFee());

            ps.executeUpdate();
            System.out.println("Course added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW courses
    public void getAllCourses() {

        String sql = "SELECT * FROM course";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- COURSE LIST -----");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("course_id") + " | " +
                        rs.getString("course_name") + " | " +
                        rs.getString("course_code") + " | " +
                        rs.getString("duration") + " | Fee: " +
                        rs.getDouble("fee")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE course
    public void updateCourse(Course course) {

        String sql = "UPDATE course SET course_name=?, course_code=?, duration=?, fee=? WHERE course_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getDuration());
            ps.setDouble(4, course.getFee());
            ps.setInt(5, course.getCourseId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Course updated successfully!");
            } else {
                System.out.println("Course ID not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE course
    public void deleteCourse(int courseId) {

        String sql = "DELETE FROM course WHERE course_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, courseId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Course deleted successfully!");
            } else {
                System.out.println("Course ID not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
