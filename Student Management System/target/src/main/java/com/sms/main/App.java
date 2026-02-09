package com.sms.main;

import java.sql.Date;
import java.util.Scanner;

import com.sms.dao.CourseDAO;
import com.sms.dao.EnrollmentDAO;
import com.sms.dao.MarksDAO;
import com.sms.dao.StudentDAO;
import com.sms.model.Course;
import com.sms.model.Enrollment;
import com.sms.model.Marks;
import com.sms.model.Student;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        MarksDAO marksDAO = new MarksDAO();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");

            System.out.println("5. Add Course");
            System.out.println("6. View Courses");
            System.out.println("7. Update Course");
            System.out.println("8. Delete Course");

            System.out.println("9. Add Enrollment");
            System.out.println("10. View Enrollments");
            System.out.println("11. Delete Enrollment");

            System.out.println("12. Add Marks");
            System.out.println("13. View Marks");
            System.out.println("14. Delete Marks");

            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            /* ---------- EXIT ---------- */
            if (choice == 0) {
                System.out.println("Thank you! Exiting system...");
                break;
            }

            /* ---------- STUDENT MODULE ---------- */
            switch (choice) {

            case 1:
                Student s = new Student();

                System.out.print("First Name: ");
                s.setFirstName(sc.nextLine());

                System.out.print("Last Name: ");
                s.setLastName(sc.nextLine());

                System.out.print("Email: ");
                s.setEmail(sc.nextLine());

                System.out.print("DOB (yyyy-mm-dd): ");
                s.setDob(Date.valueOf(sc.nextLine()));

                System.out.print("Gender (m/f/o): ");
                s.setGender(sc.nextLine());

                System.out.print("Phone: ");
                s.setPhone(sc.nextLine());

                System.out.print("Address: ");
                s.setAddress(sc.nextLine());

                studentDAO.addStudent(s);
                break;

            case 2:
                studentDAO.getAllStudents();
                break;

            case 3:
                Student us = new Student();

                System.out.print("Enter Student ID to update: ");
                us.setStudentId(sc.nextInt());
                sc.nextLine();

                System.out.print("New First Name: ");
                us.setFirstName(sc.nextLine());

                System.out.print("New Last Name: ");
                us.setLastName(sc.nextLine());

                System.out.print("New Phone: ");
                us.setPhone(sc.nextLine());

                System.out.print("New Address: ");
                us.setAddress(sc.nextLine());

                studentDAO.updateStudent(us);
                break;

            case 4:
                System.out.print("Enter Student ID to delete: ");
                studentDAO.deleteStudent(sc.nextInt());
                break;

            /* ---------- COURSE MODULE ---------- */
            case 5:
                Course c = new Course();

                System.out.print("Course Name: ");
                c.setCourseName(sc.nextLine());

                System.out.print("Course Code: ");
                c.setCourseCode(sc.nextLine());

                System.out.print("Duration: ");
                c.setDuration(sc.nextLine());

                System.out.print("Fee: ");
                c.setFee(sc.nextDouble());

                courseDAO.addCourse(c);
                break;

            case 6:
                courseDAO.getAllCourses();
                break;

            case 7:
                Course uc = new Course();

                System.out.print("Enter Course ID to update: ");
                uc.setCourseId(sc.nextInt());
                sc.nextLine();

                System.out.print("New Course Name: ");
                uc.setCourseName(sc.nextLine());

                System.out.print("New Course Code: ");
                uc.setCourseCode(sc.nextLine());

                System.out.print("New Duration: ");
                uc.setDuration(sc.nextLine());

                System.out.print("New Fee: ");
                uc.setFee(sc.nextDouble());

                courseDAO.updateCourse(uc);
                break;

            case 8:
                System.out.print("Enter Course ID to delete: ");
                courseDAO.deleteCourse(sc.nextInt());
                break;

            /* ---------- ENROLLMENT MODULE ---------- */
            case 9:
                Enrollment e = new Enrollment();

                System.out.print("Student ID: ");
                e.setStudentId(sc.nextInt());

                System.out.print("Course ID: ");
                e.setCourseId(sc.nextInt());
                sc.nextLine();

                System.out.print("Enroll Date (yyyy-mm-dd): ");
                e.setEnrollDate(Date.valueOf(sc.nextLine()));

                System.out.print("Status (active/completed/dropped): ");
                e.setStatus(sc.nextLine());

                enrollmentDAO.addEnrollment(e);
                break;

            case 10:
                enrollmentDAO.getAllEnrollments();
                break;

            case 11:
                System.out.print("Enter Enrollment ID to delete: ");
                enrollmentDAO.deleteEnrollment(sc.nextInt());
                break;

            /* ---------- MARKS MODULE ---------- */
            case 12:
                Marks m = new Marks();

                System.out.print("Student ID: ");
                m.setStudentId(sc.nextInt());

                System.out.print("Course ID: ");
                m.setCourseId(sc.nextInt());
                sc.nextLine();

                System.out.print("Subject: ");
                m.setSubject(sc.nextLine());

                System.out.print("Marks Obtained: ");
                m.setMarksObtained(sc.nextInt());

                System.out.print("Total Marks: ");
                m.setTotalMarks(sc.nextInt());
                sc.nextLine();

                System.out.print("Grade: ");
                m.setGrade(sc.nextLine());

                System.out.print("Exam Date (yyyy-mm-dd): ");
                m.setExamDate(Date.valueOf(sc.nextLine()));

                marksDAO.addMarks(m);
                break;

            case 13:
                marksDAO.getAllMarks();
                break;

            case 14:
                System.out.print("Enter Marks ID to delete: ");
                marksDAO.deleteMarks(sc.nextInt());
                break;

            default:
                System.out.println("Invalid choice! Please try again.");
            }
        }

        sc.close();
    }
}
