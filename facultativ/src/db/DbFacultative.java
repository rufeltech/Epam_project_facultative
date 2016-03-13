
package db;

import db.Source.datas;
import lib_ob.Faculty;
import lib_ob.Grades;
import lib_ob.Student;
import lib_ob.Teacher;
import lib_ob.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbFacultative  {
	 public datas  dbCon = new   datas();
    
    public Faculty find_facultative_id(int id) {
    	Faculty f = null;
    	FunctionConn functionConn = new FunctionConn();
            functionConn.connect(dbCon);
            String query = "SELECT * FROM `FACULTY` WHERE FACULTY.ID =" + id;
            functionConn.ex_q(query);
            try {
               if (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
f = new Faculty(functionConn.get_res_set().getInt("ID"),
		   dbCon.get_db_course().find_course_id(functionConn.get_res_set().getInt("COURSE")),
                            functionConn.get_res_set().getString("STATUS").toUpperCase());
                    f.set_teach(dbCon.get_db_users().find_teacher_fac(f));
                 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                functionConn.close();
            }
     
        return f;
    }
    protected User db_query(String sql) throws SQLException {

    	Connection connect = null;
        Statement state = null;
        ResultSet res_query = null;
        try {
            connect = (Connection) dbCon.get_connect();
            state = connect.createStatement();
            res_query = state.executeQuery(sql);
            if (res_query.next()) {
            	if(res_query.getString("ROLE").equals("STUDENT"))
            	{
            	Student stud = new Student(res_query.getInt("ID"),res_query.getString("NAME"),res_query.getString("LOGIN"),res_query.getString("PASSWORD"));
            	return       stud;
            	}
            	if(res_query.getString("ROLE").equals("TEACHER"))
            	{Teacher teach = new Teacher(res_query.getInt("ID"),res_query.getString("NAME"),res_query.getString("LOGIN"),res_query.getString("PASSWORD"));
                return  teach;
                }
              
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (state != null) {
                state.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
        return null;
    }
    
    
    protected boolean query_update(String sql) throws SQLException{
  	  Connection connect = null;
      int res_query = 0;
      Statement state = null;
      try {
          connect = (Connection) dbCon.get_connect();
          state = connect.createStatement();
          res_query = state.executeUpdate(sql);
      } catch (SQLException e) {
          e.printStackTrace();
      } finally {
          if (state != null) {
              state.close();
          }
          if (connect != null) {
              connect.close();
          }
      }
      if (res_query > 0) {
          return true;
      }
      return false;
  }


    public Faculty find_facultative_id_f(int id) {
       
        FunctionConn functionConn = new FunctionConn();
        Faculty faculty=null;
         functionConn.connect(dbCon);
         String query = "SELECT * FROM `FACULTY` WHERE FACULTY.ID =" + id;
         functionConn.ex_q(query);
         try {
           if (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
               faculty = new Faculty(functionConn.get_res_set().getInt("ID"),
                         dbCon.get_db_course().find_course_id(functionConn.get_res_set().getInt("COURSE")),
                         (Teacher) dbCon.get_db_users().find_user_id(functionConn.get_res_set().getInt("TEACHER")),
                   functionConn.get_res_set().getString("STATUS").toUpperCase());

               if (faculty != null)
               {
            	   faculty.set_teach(dbCon.get_db_users().find_teacher_fac(faculty));
               faculty.set_student_list(dbCon.get_db_users().find_students_facult(faculty));
               }
               }
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             functionConn.close();
         }
     
         return faculty;
        
        
        
    }


    public List<Faculty> find_facultatives() {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();
        functionConn.connect(dbCon);
        String query = "SELECT * FROM `FACULTY`";
        functionConn.ex_q(query);
        try {
            while (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
                Faculty f = new Faculty(functionConn.get_res_set().getInt("ID"),
                        dbCon.get_db_course().find_course_id(functionConn.get_res_set().getInt("COURSE")),
                        functionConn.get_res_set().getString("STATUS").toUpperCase());
                f.set_teach(dbCon.get_db_users().find_teacher_fac(f));
                returnValue.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

    



   
    public List<Faculty> find_faculty_list_teacher(Teacher teacher) {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();
        functionConn.connect(dbCon);
        String query = "SELECT * FROM FACULTY WHERE TEACHER = " + teacher.get_id();
        Faculty f;
        functionConn.ex_q(query);
        try {
            while (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
                f = new Faculty(functionConn.get_res_set().getInt("ID"),
                        dbCon.get_db_course().find_course_id(functionConn.get_res_set().getInt("COURSE")),
                        teacher,
                      functionConn.get_res_set().getString("STATUS").toUpperCase());

                f.set_student_list(dbCon.get_db_users().find_students_facult(f));
                returnValue.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

  
    public List<Faculty> find_fac_list_student(Student student) {
        ArrayList<Faculty> returnValue = new ArrayList<Faculty>();
        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(dbCon);
        String query = "SELECT * FROM `FACULTY-STUDENT` WHERE STUDENT = " + student.get_id();
        functionConn.ex_q(query);
        try {
            while (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
                returnValue.add(find_fac_id_inc_teacher(functionConn.get_res_set().getInt("FACULTY")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }

 
    public Faculty find_fac_id_inc_teacher(int id) {

        FunctionConn functionConn = new FunctionConn();
       Faculty faculty=null;
        functionConn.connect(dbCon);
        String query = "SELECT * FROM `FACULTY` WHERE ID =" + id;
        functionConn.ex_q(query);
        try {
          if (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
              faculty = new Faculty(functionConn.get_res_set().getInt("ID"),
                        dbCon.get_db_course().find_course_id(functionConn.get_res_set().getInt("COURSE")),
                        (Teacher) dbCon.get_db_users().find_user_id(functionConn.get_res_set().getInt("TEACHER")),
                  functionConn.get_res_set().getString("STATUS").toUpperCase());

                if (faculty != null)
                    faculty.set_teach(dbCon.get_db_users().find_teacher_fac(faculty));
                
                
        
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return faculty;
        
        
        
        
    }


    public Faculty find_fac_id_inc_stud(int id) {
        FunctionConn functionConn = new FunctionConn();
       Faculty faculty=null;
        functionConn.connect(dbCon);
        String query = "SELECT * FROM `FACULTY` WHERE ID =" + id;
        functionConn.ex_q(query);
        try {
          if (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
              faculty = new Faculty(functionConn.get_res_set().getInt("ID"),
                        dbCon.get_db_course().find_course_id(functionConn.get_res_set().getInt("COURSE")),
                        (Teacher) dbCon.get_db_users().find_user_id(functionConn.get_res_set().getInt("TEACHER")),
                  functionConn.get_res_set().getString("STATUS").toUpperCase());

              if (faculty != null)
                  faculty.set_student_list(dbCon.get_db_users().find_students_facult(faculty));  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
    
        return faculty;
    }

    
    public boolean upd_stat(int facultyID, String stat) {
        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(dbCon);
        String query = "UPDATE FACULTY SET `STATUS` = '" + stat + "' WHERE ID=" + facultyID;
        if (functionConn.upd_query(query) == 0) {
            functionConn.close();
            return false;
        } else {
            functionConn.close();
            return true;
        }
    }

}
