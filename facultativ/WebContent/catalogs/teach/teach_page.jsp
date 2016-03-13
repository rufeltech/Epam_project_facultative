<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tag_for" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница учителя</title>
</head>
<body>
<a class="button" href="/facultativ/index_pg">Вернуться на главную </a>

<a class="button" href="/facultativ/catalogs/teach/Add_course.jsp" align="right">Добавление курса</a>
<a class="button" href="/facultativ/catalogs/teach/Add_facultative.jsp" align="right">Добавление факультатива</a>

<p/> Пользователь ${login}
<a class="button" href="/facultativ/exit" align="right">Выйти</a>

<p/>

<div>
    <table id="tablet">
        <form action="/facultativ/watch_teach" method="post">
            <thead>
            <tr align="center"><h2>Список факультативов</h2></tr>
            <tr>
                <th>№</th>
                <th>Название курса</th>
                <th>Статус</th>
                <th></th>
            </tr>
            </thead>


            <tbody>

            <tag_for:forEach var="item" items="${facultative}">
                <tr>
                    <td><tag_for:out value="${item.get_id()}"></tag_for:out></td>
                    <td>${item.get_course().get_title()}</td>
                    <td>${item.get_status().toString()}</td>
                    <td>
                        <button type="submit" name="watch" value="${item.get_id()}">Ок</button>
                    </td>
                </tr>
            </tag_for:forEach>
            </tbody>
        </form>
    </table>
</div>
</body>
</html>
