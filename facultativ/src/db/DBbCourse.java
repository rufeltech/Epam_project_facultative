

package db;

import db.Source.datas;
import lib_ob.Course;
import lib_ob.Faculty;
import lib_ob.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBbCourse  {
 
public datas dbCon = new datas();
    

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

    public Course find_course_id(int id) {
       
        
        
        FunctionConn functionConn = new FunctionConn();
       Course course=null;
        functionConn.connect(dbCon);
        String query = "SELECT * FROM COURSE WHERE COURSE.ID =" + id;
        functionConn.ex_q(query);
        try {
          if (functionConn.get_res_set() != null && functionConn.get_res_set().next()) {
              course =  new Course(functionConn.get_res_set().getInt("ID"),
            		  functionConn.get_res_set().getString("TITLE"));

             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
    
        return course;
        
        
        
    }

  
    public List<Course> find_courses() {
        FunctionConn functionConn = new FunctionConn();
        ArrayList<Course> returnValue = new ArrayList<Course>();
        functionConn.connect(dbCon);
        String query = "SELECT * FROM COURSE";
        functionConn.ex_q(query);
        try {
            while (functionConn.get_res_set()!=null && functionConn.get_res_set().next()) {
                returnValue.add(new Course(functionConn.get_res_set().getInt("ID"),
                        functionConn.get_res_set().getString("TITLE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            functionConn.close();
        }
        return returnValue;
    }
}
