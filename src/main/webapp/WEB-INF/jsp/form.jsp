<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form表单提交</title>
</head>
<body>

表单提交格式：Content-Type: application/x-www-form-urlencoded。<br>
默认就是enctype="application/x-www-form-urlencoded"<br>
<form method="post" action="/form/urlencoded">
    name: <input type="text" name="name"><br>
    age: <input type="text" name="age"><br>
    <input type="submit">
</form>

</body>
</html>
