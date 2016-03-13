
package servlets;

import db.Source.datas;
import lib_ob.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/index_pg")
public class Index extends HttpServlet {
  

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean access = false;
        String login = null;
        String role = null;

      
        if (session.getAttribute("access") != null && session.getAttribute("login") != null &&
                session.getAttribute("role") != null) {
            login = (String) session.getAttribute("login");
            access = Boolean.valueOf((String) session.getAttribute("access"));
            role = (String) session.getAttribute("role");
        }
        String page = "/index_pg";


        
        if (access) {
        
            RequestDispatcher dispatcher = request.getRequestDispatcher("/authoriz_page");
            dispatcher.forward(request, response);
   
        } else {
        
         
            response.sendRedirect("/facultativ/catalogs/index_page.jsp");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
