<%--suppress XmlPathReference --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
<title>Spring in Action</title>
<link rel="stylesheet"
type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1><s:message code="sia.welcome" /></h1>

<s:url value="/nodes" var="nodeUrl" />
<a href="${nodeUrl}">Nodes</a> |
<s:url value="/user/register" var="registerUrl" />
<a href="${registerUrl}">Register</a>
</body>
</html>