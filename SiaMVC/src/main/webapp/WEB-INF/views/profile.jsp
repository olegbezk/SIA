<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sf" %>
<%@ page session="false" %>
<html>
  <head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="<sf:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Profile</h1>
    <sf:out value="${user.username}" /><br/>
    <sf:out value="${user.firstName}" /> <sf:out value="${user.lastName}" /><br/>
    <sf:out value="${user.email}" /><br/>
    <a href="<sf:url value="/homepage" />"><input type="button" value="Home" /></a>
  </body>
</html>
