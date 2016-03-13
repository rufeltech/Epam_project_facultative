package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Source.datas;
import lib_ob.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/regist")

public class Registration extends HttpServlet {

    datas dbCon = datas.get_data_s();
   

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        if (dbCon.get_db_users().reg_user(new Student(name, login, password))) {
        
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("/catalogs/index_page.jsp");
             dispatcher.forward(request, response);
        } else {
            request.setAttribute("RegMistake", "user registration fault");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/catalogs/regist_page.jsp");
            dispatcher.forward(request, response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
