package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sms.model.Student;
import com.sms.util.DBConnection;

public class StudentDAO {

    // INSERT student
    public void addStudent(Student student) {

        String sql = "INSERT INTO student " +
                     "(first_name, last_name, email, dob, gender, phone, address) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setDate(4, student.getDob());
            ps.setString(5, student.getGender());
            ps.setString(6, student.getPhone());
            ps.setString(7, student.getAddress());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student inserted successfully!");
            } else {
                System.out.println("Insert failed!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ all students
    public void getAllStudents() {

        String sql = "SELECT * FROM student";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- STUDENT LIST -----");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("student_id") + " | " +
                    rs.getString("first_name") + " " +
                    rs.getString("last_name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("phone")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE student
    public void updateStudent(Student student) {

        String sql = "UPDATE student SET first_name=?, last_name=?, phone=?, address=? " +
                     "WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getAddress());
            ps.setInt(5, student.getStudentId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student ID not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // DELETE student
    public void deleteStudent(int studentId) {

        String sql = "DELETE FROM student WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student ID not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

