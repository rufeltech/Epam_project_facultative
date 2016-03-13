
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
    <title>Добавление курсов</title>
</head>
<body>
<a class="button" href="/facultativ/index_pg">Вернуться на главную </a>
<a class="button" href="/facultativ/exit" align="right">Выйти</a>
<p/> Пользователь ${login}



        <form action="/facultativ/catalogs/teach/Add_course.jsp" method="post">
         
            <table>
       <%

       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       datas df = new datas();
       connection = (Connection) df.get_connect();
       statement = connection.createStatement();
       ResultSet r = statement.executeQuery("SELECT max(ID) AS max_id  FROM `course` " );

       int id2 = -1;
       if (r.next()) {
          id2 = r.getInt("max_id");  
       }
       int count = id2;
       count++;
       connection.close();
       r.close();
    	   %>
    	   	<tr>
    	   	 <th >
    	   
    	№
    	
       </th>
    	   <th >
    	   
    	 Название курса
    	
       </th>
       </tr>
    	<tr>
    	   <td >
    	   
    	  <% out.println(count); %>>
    	
       </td>
    	   <td >
    	   
    	   <input style="width: 80px;" type="" name="course_title" >
    	
       </td>
       </tr>
           </table>
            <input type="submit"  value="Добавить"/>
        </form>
<%
String text_title = request.getParameter("course_title");
if(text_title!=null) 
{ 
connection = null;
 statement = null;
 resultSet = null;
df = new datas();
connection = (Connection) df.get_connect();
statement = connection.createStatement();
statement.executeUpdate("INSERT INTO COURSE (ID, TITLE) VALUES('" + count + "','" +text_title+ "')  " );
} 
%>
      
    </table>
</div>
</body>
</html>
