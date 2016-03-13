
package servlets;


import db.DbUsers;
import db.DBbCourse;
import db.Source.datas;
import lib_ob.Faculty;
import lib_ob.Student;
import lib_ob.Teacher;
import lib_ob.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/authoriz_page")



public class Authorization_page extends HttpServlet {

    datas dbCon = datas.get_data_s();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (request).getSession();
        boolean access = Boolean.valueOf((String) session.getAttribute("access"));

        User log_user;

        if (access) {
            log_user = dbCon.get_db_users().find_user_login((String) session.getAttribute("login")); // выборка пользователя по логину
        } else {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            DbUsers db_users = new DbUsers();
      
      
            log_user = db_users.find_user_login(login);

            if (log_user.get_passw().equals(password)) { 
                session.setAttribute("login", login);
                session.setAttribute("access", "true");
                session.setAttribute("role", log_user.getClass().toString());
             
            }
        }


        if (log_user != null && log_user.getClass().equals(Student.class)) {
          
            Student st = (Student) log_user;
            List<Faculty> fac_list_stud = dbCon.get_db_fac().find_fac_list_student(st);
            List<Faculty> open_fac_list = new ArrayList<>();
            List<Faculty> fac = dbCon.get_db_fac().find_facultatives();
            st.set_facultative_list(fac_list_stud);
            st.set_mark_list(dbCon.get_db_grades().find_marks_student(st));
            for (Faculty faculty : fac) {
                boolean flag = !faculty.get_status().equals("ENDED");
                if (flag)
                    for (Faculty faculty1 : fac_list_stud) {
                        if (faculty.getId()== faculty1.getId()) {
                            flag = false;
                            break;
                        }
                    }
                if (flag) open_fac_list.add(faculty);
            }

            request.setAttribute("student", st);
            request.setAttribute("open_facultative_list", open_fac_list);
            request.setAttribute("facultative_list_student", fac_list_stud);
            RequestDispatcher dispatcher = request.getRequestDispatcher("catalogs/stud/stud_page.jsp");
            dispatcher.forward(request, response);
        } else if (log_user.getClass().equals(Teacher.class)) {
            request.setAttribute("facultative", dbCon.get_db_fac().find_faculty_list_teacher((Teacher) log_user));
            RequestDispatcher dispatcher = request.getRequestDispatcher("catalogs/teach/teach_page.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (request).getSession();
        boolean access = Boolean.valueOf((String) session.getAttribute("access"));

        User log_user = null;

        if (access) {
            log_user = dbCon.get_db_users().find_user_login((String) session.getAttribute("login"));
        }


        if (log_user != null && log_user.getClass().equals(Student.class)) {
            
            Student st = (Student) log_user;
            List<Faculty> fac_list_stud = dbCon.get_db_fac().find_fac_list_student(st);
            List<Faculty> open_fac_list = new ArrayList<>();
            List<Faculty> fac = dbCon.get_db_fac().find_facultatives();
            for (Faculty faculty : fac) {
                boolean flag = !faculty.get_status().equals("ENDED");
                if (flag)
                    for (Faculty faculty1 : fac_list_stud) {
                        if (faculty.getId()== faculty1.getId()) {
                            flag = false;
                            break;
                        }
                    }
                if (flag) open_fac_list.add(faculty);
            }

            request.setAttribute("student", st);
            request.setAttribute("open_facultative_list", open_fac_list);
            request.setAttribute("facultative_list_student", fac_list_stud);

            RequestDispatcher dispatcher = request.getRequestDispatcher("catalogs/stud/stud_page.jsp");
            dispatcher.forward(request, response);
        
        
        } else if (log_user.getClass().equals(Teacher.class)) {
            request.setAttribute("facultative", dbCon.get_db_fac().find_faculty_list_teacher((Teacher) log_user));

            RequestDispatcher dispatcher = request.getRequestDispatcher("catalogs/teach/teach_page.jsp");
            dispatcher.forward(request, response);
        }
    }
}
