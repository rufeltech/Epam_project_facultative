
package servlets;

import db.Source.datas;
import lib_ob.Faculty;
import lib_ob.Grades;
import lib_ob.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/AddGrade")

public class AddGradde extends HttpServlet {
    datas dbCon = datas.get_data_s();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Faculty faculty = dbCon.get_db_fac().find_fac_id_inc_stud(Integer.valueOf(request.getParameter("facultative")));
        for (Student student : faculty.get_st_list()) {
            String mark = (String) request.getParameter("stud" + student.get_id());
            dbCon.get_db_grades().addMark(new Grades(faculty, student, mark));
        }
        dbCon.get_db_fac().upd_stat(faculty.getId(), "ENDED");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/catalogs/teach/teach_page.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
