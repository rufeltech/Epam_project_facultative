package db;

import db.Source.datas;
import lib_ob.Faculty;
import lib_ob.Student;
import lib_ob.Teacher;
import lib_ob.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DbUsers  {
	protected datas dbCon;
   
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

    public DbUsers() {
       dbCon = new datas();
    }

    public User find_user_id(int id) {
        User u = null;
        try {
            u = db_query("SELECT * FROM ACCOUNT WHERE ACCOUNT.ID =" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

   

  
    
    public User find_user_login(String login)  {
        User u = null;
     try {
            u = db_query("SELECT * FROM ACCOUNT WHERE LOWER(ACCOUNT.LOGIN) = LOWER('" + login + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
        return u;
    }

  

   
    public boolean reg_user(User user) {
        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(dbCon);
        String role = "STUDENT";
        String query = "INSERT INTO ACCOUNT (NAME, LOGIN, PASSWORD, ROLE) VALUES ('" + user.get_name() + "','" +
                user.get_log() + "','" + user.get_passw() + "','" + role + "')";
        if (functionConn.upd_query(query) == 0) {
            functionConn.close();
            return false;
        } else {
            functionConn.close();
            return true;
        }
    }

    public List<Student> find_students_facult(Faculty faculty) {
        List<Student> userList = new ArrayList<>();

        FunctionConn functionConn = new FunctionConn();
        functionConn.connect(dbCon);
        String query = "SELECT * FROM `FACULTY-STUDENT` WHERE FACULTY = " + faculty.getId();
        functionConn.ex_q(query);
        try {
            while (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
                Student st = (Student) dbCon.get_db_users().find_user_id(functionConn.get_res_set().getInt("STUDENT"));
                st.addMark(dbCon.get_db_grades().mark_student_fac(st, faculty));
                userList.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

  

    public Teacher find_teacher_fac(Faculty faculty) {
        int teacherID = 0;
       
        

        FunctionConn functionConn = new FunctionConn();
       
        functionConn.connect(dbCon);
        
        
        
        
        
        String query = "SELECT * FROM FACULTY WHERE FACULTY.ID =" + faculty.getId();
        functionConn.ex_q(query);
        try {
            if (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
            	teacherID=  functionConn.get_res_set().getInt("TEACHER");
            }
              else
            	  teacherID=  -1;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
     

        
        
        
        return (Teacher) find_user_id(teacherID);
    }

  
    public boolean add_fac(int idStudent, int idFacult) {
        try {
            return query_update("INSERT INTO `FACULTY-STUDENT` (STUDENT, FACULTY) VALUES ('" + idStudent + "','" + idFacult + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
