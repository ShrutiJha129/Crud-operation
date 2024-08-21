/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Subject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.firstproj.DBConnection;
import Student.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Subjectcontroller {
    //     Getall for subject
      public static List<Subject> getAllSubject() {
         String sql="SELECT id,Name,Course_ID FROM subject";
         List<Subject> Subjects = new ArrayList<>();
           try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
               Subject subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("Name"));
                subject.setCourseID(rs.getInt("CourseID"));
                Subjects.add(subject);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return Subjects;
    } 

    public static String deletSubject(Integer valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //        Create
          public static String createSubject(String reqBody){
              Gson gson = new GsonBuilder().create();
              Subject data = gson.fromJson(reqBody,Subject.class);
              String sql ="Insert INTO subject (id,Name, course_ID) VALUES(?,?,?)";
              try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Subject subject = gson.fromJson(reqBody, Subject.class);
            stmt.setInt(1, subject.getId ());
            stmt.setString(2, subject.getName());
            stmt.setInt(3, subject.getCourseID());
           
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 1) {
                return "Successfully added";
            }
            }
         catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Failed to add subject";
          }
          //             Update for subjects
             public static String updateSubject(int id, String reqBody) {
                Gson gson = new GsonBuilder().create();
               Subject subject = gson.fromJson(reqBody, Subject.class);
        String sql = "UPDATE subject SET id=?,Name = ?,Course_ID=?  WHERE id= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

       
            stmt.setInt(1,subject.getId());
            stmt.setString(2, subject.getName());
            stmt.setInt(3,subject.getCourseID());
            stmt.setInt(4,id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return "Subject updated";
            } else {
                return "Subject not found";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Failed to update subject";
    }
             //Delete for subject
public static String deleteSubject(int id) {
        String sql = "DELETE FROM subject WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return "subject deleted";
            } else {
                return "subject not found";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Failed to delete suject";
    }            
}
