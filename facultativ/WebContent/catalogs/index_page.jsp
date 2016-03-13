
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<a class="button" href="/facultativ/index_pg">Вернуться на главную </a>
<table align="center">
    <form method="post" action="/facultativ/authoriz_page">
        <tr>
            <td>Логин пользователя</td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td><a href="regist_page.jsp" class="button">Регистрация</a> </td>
            <td><input type="submit"></td>
        </tr>
      
    </form>
</table>

</body>
</html>
