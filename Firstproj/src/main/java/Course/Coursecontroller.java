/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Course;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.firstproj.DBConnection;
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
public class Coursecontroller {


    //    Getall 
     public static List<Course> getAllCourse() {
         String sql="SELECT ID,Name FROM course";
         List<Course> Courses= new ArrayList<>();
           try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course course = new Course();
                course.setID(rs.getInt("ID"));
                course.setName(rs.getString("Name"));
                Courses.add(course);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Courses;
    } 
     //    GET by id
    public void getbyID(int ID) throws SQLException {
       String sql = "SELECT ID, name,  FROM course WHERE ID= ?";
       Course course = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   course= new Course();
                   course.setID(rs.getInt("StudentID"));
                   course.setName(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       //   CREATE 
          public static String createCourse(String reqBody) {    
        Gson gson = new GsonBuilder().create();
        Course data = gson.fromJson(reqBody, Course.class);
        String sql = "INSERT INTO course (ID,Name) VALUES (?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Course course = gson.fromJson(reqBody, Course.class);
             stmt.setInt(1, course.getID ());
            stmt.setString(2, course.getName());
           
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 1) {
                return "Successfully added";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Failed to add course";
    }
    
//UPDATE 
             public static String updateCourse(int id, String reqBody) {
                Gson gson = new GsonBuilder().create();
                Course course = gson.fromJson(reqBody, Course.class);
        String sql = "UPDATE course SET ID=?,Name = ?  WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

       
            stmt.setInt(1,course.getID());
            stmt.setString(2, course.getName());
            stmt.setInt(3,id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return "Course updated";
            } else {
                return "Course not found";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Failed to update course";
             }
             //Delete for Course

public static String deleteCourse(int id) {
        String sql = "DELETE FROM course WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return "Course deleted";
            } else {
                return "Course not found";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Failed to delete course";
    }
    }
    

            