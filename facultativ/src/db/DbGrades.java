
package db;

import db.Source.datas;
import lib_ob.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DbGrades  {
   
   public datas dbCon= new datas();
  


    public boolean addMark(Grades mark) {
        try {
            return query_update("INSERT INTO MARK (STUDENT, FACULTY, MARK) VALUES ('" + mark.get_st().get_id() + "','" +
                    mark.get_facultative().getId() + "','" + mark.get_mark() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

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


    public List<Grades> find_marks_student(Student student) {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Grades> returnValue = new ArrayList<Grades>();
        functionConn.connect(dbCon);
        String query = "SELECT * FROM MARK WHERE STUDENT = " + student.get_id();
        functionConn.ex_q(query);
        try {
            while (functionConn.get_res_set()!=null && functionConn.get_res_set().next()) {
                returnValue.add(new Grades(dbCon.get_db_fac().find_facultative_id(functionConn.get_res_set().getInt("FACULTY")),
                        student,
                        functionConn.get_res_set().getString("MARK")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }
 
    public List<Grades> get_marks_student(Student student) {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Grades> returnValue = new ArrayList<Grades>();
        functionConn.connect(dbCon);
        String query = "SELECT * FROM MARK WHERE STUDENT = " + student.get_id();
        functionConn.ex_q(query);         try { 
    
        	while (functionConn.get_res_set()!=null && functionConn.get_res_set().next()) {
                returnValue.add(new Grades(dbCon.get_db_fac().find_facultative_id_f(functionConn.get_res_set().getInt("FACULTY")),
                        student, 
                        functionConn.get_res_set().getString("MARK")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }


    public Grades mark_student_fac(Student student, Faculty faculty) {
    	 FunctionConn functionConn = new FunctionConn();
         Grades mark=null;
          functionConn.connect(dbCon);
         
          String query = "SELECT * FROM MARK WHERE STUDENT = " + student.get_id() + " AND FACULTY = " + faculty.getId();
          functionConn.ex_q(query);
          try {
            if (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
            return    mark =  new Grades(faculty, student, functionConn.get_res_set().getString("MARK"));         
              }
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              functionConn.close();
          }
      
          return null;
  
    }
}
