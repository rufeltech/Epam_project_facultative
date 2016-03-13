package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import db.Source.datas;
import lib_ob.User;

@WebServlet("/add_fac_teach")

public class Add_fac_teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_fac_teacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = (request).getSession();
		datas dbCon = datas.get_data_s();
		User log = dbCon.get_db_users().find_user_login((String) session.getAttribute("/login"));
		int id_teach=log.get_id(); 
		String cours = request.getParameter("facult").toString();

		if(cours!=null) 
		{ 
	Connection	connection = null;
	Statement	 statement = null;
	ResultSet	 resultSet = null;
	datas	df = new datas();
		try {
			connection = (Connection) df.get_connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement = (Statement) connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("INSERT INTO COURSE (COURSE, STATUS, TEACHER) VALUES('" + cours + "','STARTED','" +id_teach+ "')  ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} 
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("index_pg");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 HttpSession session = (request).getSession();
			datas dbCon = datas.get_data_s();
			User log = dbCon.get_db_users().find_user_login((String) session.getAttribute("login"));
			int id_teach=log.get_id(); 
		
			String cours = 	request.getParameter("facult").toString();

			if(cours!=null) 
			{ 
		Connection	connection = null;
		Statement	 statement = null;
		ResultSet	 resultSet = null;
		datas	df = new datas();
			try {
				connection = (Connection) df.get_connect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement = (Statement) connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement.executeUpdate("INSERT INTO faculty (COURSE, STATUS, TEACHER) VALUES('" + cours + "','STARTED','" +id_teach+ "')  ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} 
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/index_pg");
	            dispatcher.forward(request, response);
	}

}
