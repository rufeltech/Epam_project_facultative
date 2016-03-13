
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<a class="button" href="/facultativ/index_pg">Вернуться на главную</a>
<table align="center">
    <form action="/facultativ/regist" method="post">
        <tr>
            <td>Регистрация</td>
        </tr>
        <tr>
            <td>Логин пользователя</td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>Подтверждение пароля</td>
            <td><input type="password" name="password2"></td>
        </tr>
        <tr>
            <td>Имя</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td/>
            <td>
                <button type="submit">Регистрация</button>
            </td>
        </tr>
      
    </form>
</table>

</body>
</html>
