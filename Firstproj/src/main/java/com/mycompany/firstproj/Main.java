/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.firstproj;

import Course.Coursecontroller;
import com.google.gson.Gson;
import Student.Studentcontroller;
import Subject.Subjectcontroller;
import io.javalin.Javalin;


/**
 *
 * @author Asus
 */
public class Main {
    
    static  Gson gson = new Gson();
    

    public static void main(String[] args) {
              Javalin app = Javalin.create().start(7000);
        
        app.get("/students", ctx -> {
        ctx.json(gson.toJson(Studentcontroller.getAll()));
       
        });
        app.post("/students", ctx -> {
          ctx.result(Studentcontroller.createStudent(ctx.body()));
       
       });
         app.put("/students", ctx -> {
        ctx.result(Studentcontroller.updateStudent(Integer.valueOf(ctx.queryParam("id")), ctx.body()));
       
       });
         app.delete("/students", ctx -> {
          ctx.result(Studentcontroller.deleteStudent(Integer.valueOf(ctx.queryParam("id"))));
       
       });
        
          app.get("/Course", ctx -> {
               ctx.json(gson.toJson(Coursecontroller.getAllCourse()));
              
          });
          app.post("/Course", ctx -> {
          ctx.result(Coursecontroller.createCourse(ctx.body()));
       
       });
           app.put("/Course", ctx -> {
        ctx.result(Coursecontroller.updateCourse(Integer.valueOf(ctx.queryParam("id")), ctx.body()));
       
       });
         app.delete("/Course", ctx -> {
          ctx.result(Coursecontroller.deleteCourse(Integer.valueOf(ctx.queryParam("id"))));
       
       });
         app.get("/subject", ctx -> {
               ctx.json(gson.toJson(Subjectcontroller.getAllSubject()));
              
          });
          app.post("/subject", ctx -> {
          ctx.result(Subjectcontroller.createSubject(ctx.body()));
       
       });
           app.put("/subject", ctx -> {
        ctx.result(Subjectcontroller.updateSubject(Integer.valueOf(ctx.queryParam("id")), ctx.body()));
       
       });
         app.delete("/subject", ctx -> {
          ctx.result(Subjectcontroller.deletSubject(Integer.valueOf(ctx.queryParam("id"))));
       
       });
          
        
        System.out.println("Hello World!");
    }
}
