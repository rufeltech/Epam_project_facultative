
package servlets;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Source.datas;

import java.io.IOException;



@WebServlet("/AddFacultativ")
public class AddFaculty extends HttpServlet {
    datas dbCon = datas.get_data_s();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int stID = Integer.valueOf(request.getParameter("student")); 
        int fID = Integer.valueOf(request.getParameter("faculty")); 
        dbCon.get_db_users().add_fac(stID, fID);

        String page = "/authoriz_page";
       RequestDispatcher dispatcher = request.getRequestDispatcher(page); 
       dispatcher.forward(request, response);
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
