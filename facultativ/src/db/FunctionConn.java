
package db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.Source.datas;


public class FunctionConn {
    Connection connect = null;
    ResultSet res_query = null;
    Statement state = null;

    void close() {
        try {
            if (res_query != null) {
                res_query.close();
            }
            if (state != null) {
                state.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet get_res_set() {
        return res_query;
    }

    void ex_q(String query) {
        try {
            res_query = state.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int upd_query(String query) {
        try {
            return state.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    Statement get_state() {
        return state;
    }

    void connect(datas dbCon) {
        try {
        
            connect = (Connection) dbCon.get_connect();
            if(connect != null)
            
            state = connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
