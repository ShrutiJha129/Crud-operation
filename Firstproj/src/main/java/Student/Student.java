
package Student;

/**
 *
 * @author Asus
 */
public class Student {
     private int StudentID;
    private String name;
    private int age;
   
    private int courseID;

    public Student() {
    }
    
    
    public Student (int id,String name,int age, String email){
        this.StudentID=id;
        this.name= name;
        this.age=age;
      
    }
    public int getStudentID(){
        return StudentID;
    }
    public void setStudentID(int StudentID){
        this.StudentID=StudentID;
    }
     public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
     public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    
   

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    
    
}
    

