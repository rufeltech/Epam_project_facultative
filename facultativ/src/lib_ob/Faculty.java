
package lib_ob;


import java.util.ArrayList;
import java.util.List;


public class Faculty {
   
    protected int id;
 
    protected Course course;
    
    protected Teacher teacher;
   
    protected String status;
  
    protected List<Student> studentList = new ArrayList<>();

    public void set_teach(Teacher teacher) {
        this.teacher = teacher;
    }


    public int get_id() {
        return id;
    }

    public Faculty(int id, Course course, Teacher teacher, String status) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.status = status;
    }

    public Faculty(int id, Course course, String status) {
        this.id = id;
        this.course = course;
        this.status = status;
    }

    public void set_student_list(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Faculty(int id, Course course, Teacher teacher, String status, List<Student> studentList) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.status = status;
        this.studentList = studentList;

    }

    public int getId() {
        return id;
    }

  
    public Course get_course() {
        return course;
    }

    public String get_status() {
        return status;
    }

    public List<Student> get_st_list() {
        return studentList;
    }

    public void add_stud(Student student) {
        this.studentList.add(student);
    }

    public void change_stat(String status) {
        this.status = status;
    }

  
}
