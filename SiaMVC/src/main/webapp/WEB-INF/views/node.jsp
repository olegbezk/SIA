<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html>
  <head>
    <title><s:message code="sia.node" /></title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="nodeView">
      <div class="nodeMessage"><c:out value="${node.message}" /></div>
      <div>
        <span class="nodeTime"><c:out value="${node.time}" /></span>
      </div>
    </div>
  </body>
</html>