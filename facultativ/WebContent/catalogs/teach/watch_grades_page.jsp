<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tag_for" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Добавить оценки</title>
</head>
<body>
<a class="button" href="/facultativ/index_pg">Вернуться на главную</a>

<p/> Пользователь ${login}
<a class="button" href="/facultativ/exit" align="right">Выйти</a>

<p/>

<div>
    <table>
            <thead>
            <tr align="center"><h2>Лист факультативов</h2></tr>
            <tr>
                <th>№ </th>
                <th>Имя студента</th>
                <th>Оценка</th>
            </tr>
            </thead>
            <tbody>

            <tag_for:forEach var="item" items="${students}">
                <tr>
                    <td><tag_for:out value="${item.get_id()}"></tag_for:out></td>
                    <td>${item.get_name()}</td>
                    <td>${item.get_mark_facult(facultative).get_mark()}</td>
                </tr>
            </tag_for:forEach>
            </tbody>
    </table>
</div>

</body>
</html>
