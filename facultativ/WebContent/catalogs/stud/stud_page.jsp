<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tag_for" %>
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
    <title>Главная страница студента</title>
</head>
<body>
<a class="button" href="/facultativ/authoriz_page">Вернуться на главную </a>

<p/> Пользователь ${login}
<a class="button" href="/facultativ/exit" align="right">Выйти</a>

<p/>

<p/>

<div>
    <table>
        <form action="/facultativ/AddFacultativ" method="post">
            <thead>
            <tr align="center"><h2>Список открытых факультативов</h2></tr>
            <tr>
                <th>№</th>
                <th>Название курса</th>
                <th>Статус</th>
                <th></th>
            </tr>
            </thead>

            <tbody>

            <tag_for:forEach var="item" items="${open_facultative_list}">
                <tr>
                    <td><tag_for:out value="${item.get_id()}"></tag_for:out></td>
                    <td>${item.get_course().get_title()}</td>
                    <td>${item.get_status().toString()}</td>
                    <td>
                        <input type="hidden" name="student" value="${student.get_id()}">
                        <button type="submit" name="faculty" value="${item.get_id()}">GO</button>
                    </td>
                </tr>
            </tag_for:forEach>
            </tbody>
        </form>
    </table>
</div>

<div>
    <table>
        <thead>
        <tr align="center"><h2>Список факультативов</h2></tr>
        <tr>
            <th>№</th>
            <th>Название курса</th>
            <th>Статус</th>
            <th>Оценка</th>
        </tr>
        </thead>


        <tbody>

        <tag_for:forEach var="item" items="${facultative_list_student}"  >
            <tr>
                <td><tag_for:out value="${item.get_id()}"></tag_for:out></td>
                <td>${item.get_course().get_title()}</td>
                <td>${item.get_status().toString()}</td>
    
                 <td>${ student.get_mark_facult(item).get_mark() }</td>
            </tr>
        </tag_for:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
