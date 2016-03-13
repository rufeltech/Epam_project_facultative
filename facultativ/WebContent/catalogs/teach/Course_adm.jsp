
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ page import="java.util.*" %>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.io.*" %>
     <%@ page import="lib_ob.*" %>
      <%@ page import="servlets.*" %>
       <%@ page import="db.*" %>
         <%@ page import="db.Source.*" %>
<html>
<head>
    <title>Изменение информации о курсах</title>
</head>
<body>

<body>

<p/> Пользователь ${login}
<a class="button" href="/facultativ/catalogs/teach/Course_adm.js">Вернуться  </a>
<a class="button" href="/facultativ/exit" align="right">Выйти</a>

<p/>

<p/>

<div>
    <table>
        <form action="/facultativ/catalogs/teach/Course_adm.jsp" method="post">
            <thead>
            <tr align="center"><h2>Список</h2></tr>
            <tr>
                <th>№</th>
                <th>Название курса</th>
               
            </tr>
            </thead>

            <tbody>
       <%
       datas dbCon = datas.get_data_s();
       boolean access = Boolean.valueOf((String) session.getAttribute("access"));

       Course log_user;
      
       
       
       
       
     List<Course> peop = (ArrayList<Course>) dbCon.get_db_course().find_courses();;
     Iterator<Course> iterg =   peop.iterator();
     int z =0;
     
     Course iter_f= iterg.next();
     Connection  connection;
       while(iterg.hasNext()){
    	   z++;
    
          	   %>
    	<tr>
    	 <td><%  out.print(iter_f.get_id());%></td>>
    	<td>   <input style="width: 80px;" type="" name="textd<% out.print(z); %>" value="<%  out.print(iter_f.get_title());%>"/></td>
 <td width="5px"><a href="/facultativ/catalogs/teach/Course_adm.jsp?delete=<%   out.print(iter_f.get_id());%>">X</a></td>
   <%  	if(request.getParameter("textd"+z)!=null)
{

String title_course = (String) request.getParameter("textd"+z);
connection = dbCon.get_connect();
Statement statement = connection.createStatement();
try {
	statement.executeUpdate("UPDATE `course` SET  `TITLE` = '"+title_course+"' WHERE `ID` = "+iter_f.get_id() );
} catch (SQLException e) {
    e.printStackTrace();
}
   
}
  
  %>
           
          
    	 
         
           </tr>
            <%
   iter_f= iterg.next();
         
       }
   %>
       
       
       
        <%  	if(request.getParameter("delete")!=null)
{

String del = (String) request.getParameter("delete");
connection = dbCon.get_connect();
Statement statement = connection.createStatement();
try {
	
	statement.executeUpdate("DELETE FROM `course` WHERE `ID`="+del);
} catch (SQLException e) {
    e.printStackTrace();
}
   
}
  
  %>
            </tbody>
            <input type="submit" value="Добавить">
        </form>
    </table>
</div>



</body>
</html>
