<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Node</title>
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