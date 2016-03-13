
package servlets;

import db.Source.datas;
import lib_ob.Faculty;
import lib_ob.Grades;
import lib_ob.Student;
import lib_ob.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/watch_teach")

public class WatchTeacherFacultative extends HttpServlet {
    
    datas dbCon = datas.get_data_s();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (request).getSession();
        int id_fac = Integer.valueOf(request.getParameter("watch"));
        Faculty fac = dbCon.get_db_fac().find_facultative_id_f(id_fac);
        request.setAttribute("facultative", fac);
        request.setAttribute("students", fac.get_st_list());

        if (fac.get_status().equals("ENDED"))
        {
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalogs/teach/watch_grades_page.jsp");
        dispatcher.forward(request, response);
        }
        else
            {
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalogs/teach/add_grade_page.jsp");
        dispatcher.forward(request, response);
            }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
