
package db.Source;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import db.DbUsers;
import db.DBbCourse;
import db.DbFacultative;
import db.DbGrades;

import java.sql.Connection;
import java.sql.SQLException;


public class datas  {


    public Connection get_connect() throws SQLException {
     

           MysqlDataSource ds = null;
            Connection connect = null;
            Statement state = null;

     
                ds = new MysqlDataSource();
                ds.setUrl("jdbc:mysql://localhost:3306/facultative");
                ds.setUser("root");
                ds.setPassword("dbuser");
                
                connect = (Connection) ds.getConnection();

    
        return (Connection) ds.getConnection();
       
    }

    
    public DbUsers get_db_users() {
        return new DbUsers();
    }

  
    public DBbCourse get_db_course() {
        return new DBbCourse();
    }

  
    public DbFacultative get_db_fac() {
        return new DbFacultative();
    }

    public static datas get_data_s() {
        return new datas();
    }
   

    public DbGrades get_db_grades() {
        return new DbGrades();
    }


}
