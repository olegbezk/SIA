<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Message Place</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="nodeForm">
      <h1><s:message code="sia.message" /></h1>
      <form method="POST" name="nodeForm">
        <input type="hidden" name="latitude">
        <input type="hidden" name="longitude">
        <textarea name="message" cols="80" rows="5"></textarea><br/>
        <input type="submit" value="Add" />
      </form>
    </div>
    <div class="listTitle">
      <h1><s:message code="sia.recent" /></h1>
      <ul class="nodeList">
        <c:forEach items="${nodeList}" var="node" >
          <li id="node_<c:out value="node.id"/>">
            <div class="nodeMessage"><c:out value="${node.message}" /></div>
            <div>
              <span class="nodeTime"><c:out value="${node.time}" /></span>
              <span class="nodeLocation">(<c:out value="${node.latitude}" />, <c:out value="${node.longitude}" />)</span>
            </div>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${fn:length(nodeList) gt 20}">
        <hr />
        <s:url value="/nodes?count=${nextCount}" var="more_url" />
        <a href="${more_url}">Show more</a>
      </c:if>
    </div>
    <a href="<c:url value="/homepage" ></c:url>"><input type="button" value="Home" /></a>
  </body>
</html>