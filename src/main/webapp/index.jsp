<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
这是index.jsp页面。<br>
访问如下链接：<p></p>
http://localhost:8080/home/home<p></p>
http://localhost:8080/home/second?name=univ

去表单页面：<br>
http://localhost:8080/home/form/page<br>

注意观察控制台的输出。

<br>
<%--github oauth2授权流程--%>
<%--这里的https://github.com/login/oauth/authorize由github提供--%>
<a href="https://github.com/login/oauth/authorize?client_id=6be4231b74f9ed0a9e86&redirect_uri=http://localhost:8080/oauth/github/redirect">使用github登录</a>

</body>
</html>
