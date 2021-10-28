<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
  <head>
    <title>Index Page</title>
  </head>
  <body>
          Index Page

          Welcome : <shiro:principal></shiro:principal>  <br>

          <shiro:hasRole name="user">
                <a href="user.jsp">User Jsp</a>     <br>
          </shiro:hasRole>

          <shiro:hasRole name="admin">
                <a href="admin.jsp">Admin Jsp</a>   <br>
          </shiro:hasRole>



          <a href="./logout">logout</a>

  </body>
</html>
