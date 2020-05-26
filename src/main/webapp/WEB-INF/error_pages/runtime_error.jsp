<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/page_parts/jstl_and_bootstrap.jsp" %>
    <title><fmt:message key="page.title.error"/></title>
</head>
<body>
<%@ include file="/WEB-INF/page_parts/header_and_menu.jsp" %>

<br><br>
<h3 class="d-flex justify-content-center"><fmt:message key="page.error.title"/></h3>
<br>
<h5 class="d-flex justify-content-center"><fmt:message key="page.error.server.error"/></h5>
<br>
<h5 class="d-flex justify-content-center"><fmt:message key="page.error.try.later"/></h5>

</body>
</html>
