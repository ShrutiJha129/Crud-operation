/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

/**
 *
 * @author Asus
 */
import com.mycompany.firstproj.DBConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Studentcontroller {
    private static List<Student> students = new ArrayList<>();
    
//   GET ALL
    public static List<Student> getAll() {
        String sql = "SELECT StudentID,Name,Age,course_ID FROM Students ";
        List<Student> students;
       students = new ArrayList<>();
           try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("StudentID"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourseID(rs.getInt("course_ID"));
                students.add(student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        
      
    }
             return students;
    }
 

//    GET by ID
    public void getbyStudentID(int StudentID) throws SQLException {
       String sql = "SELECT StudentID, name, age,  FROM Students WHERE StudentID= ?";
        Student student = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, StudentID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setStudentID(rs.getInt("StudentID"));
                    student.setName(rs.getString("Name"));
                    student.setAge(rs.getInt("Age"));
                   
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
    }

//    CREATE for students
        public static String createStudent(String reqBody) {    
        Gson gson = new GsonBuilder().create();
        Student data = gson.fromJson(reqBody, Student.class);
        String sql = "INSERT INTO Students (StudentID,Name, Age, Course_ID) VALUES (?,?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Student student = gson.fromJson(reqBody, Student.class);
              stmt.setInt(1, student.getStudentID ());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            stmt.setInt(4, student.getCourseID());
             
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 1) {
                return "Successfully added";
            }
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return "Failed to add student";
        }                 
        
//    UPDATE
             public static String updateStudent(int id, String reqBody) {
                Gson gson = new GsonBuilder().create();
                Student data = gson.fromJson(reqBody, Student.class);
        String sql = "UPDATE Students SET StudentID=?,Name = ?, Age = ?, Course_ID=? WHERE StudentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Student student = gson.fromJson(reqBody, Student.class);
            stmt.setInt(1,student.getStudentID());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            
            stmt.setInt(4, student.getCourseID());
            stmt.setInt(5,id);
        
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return "Student updated";
            } else {
                return "Student not found";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Failed to update student";
             }       
  
//   DELETE
     public static String deleteStudent(int id) {
        String sql = "DELETE FROM Students WHERE StudentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return "Student deleted";
            } else {
                return "Student not found";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Failed to delete student";
    }
}