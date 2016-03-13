
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
    <title>Добавление факультативов</title>
</head>
<body>

<a class="button" href="/facultativ/index_pg">Вернуться на главную </a>
<a class="button" href="/facultativ/exit" align="right">Выйти</a>

 Пользователь ${login}

       <form action="/facultativ/add_fac_teach" method="post">
       <select size="3" multiple name="facult">
       <%
 
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       datas df = new datas();
       connection = (Connection) df.get_connect();
       statement = connection.createStatement();
       ResultSet r = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM `course` " );
       r.next();
       int count = r.getInt("rowcount");
       count++;
       connection = null;
      statement = null;
     resultSet = null;
      df = new datas();
       connection = (Connection) df.get_connect();
       statement = connection.createStatement();
       r = statement.executeQuery("SELECT * FROM `course` " );
       r.next();
       do {
    	 
       
       %>
  

      <option value="<%out.print(r.getString("ID"));%>"><% out.println(r.getString("TITLE") );  %></option>
 
  
 
   <% } while(r.next()) ;
	   connection.close();
   r.close();
   
   %>
   
      </select>
        <p><input type="submit" value="Добавить"></p>
  </form> 
      
      
   
</div>
</body>
</html>
