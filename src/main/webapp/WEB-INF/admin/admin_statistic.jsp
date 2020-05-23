<!DOCTYPE html>
<html>
<head>
    <%@ include file="../page_parts/jstl_and_bootstrap.jsp" %>
    <title><fmt:message key="page.title.admin.statistic"/></title>
</head>
<body>
<%@ include file="../page_parts/header_and_menu.jsp" %>

<h3 class="d-flex justify-content-center"><fmt:message key="page.title.admin.statistic"/></h3><br>

<table class="table">
    <thead>
    <tr>
        <th style="width: 80%"><fmt:message key="admin.statistic.user"/></th>
        <th style="width: 10%"><fmt:message key="admin.statistic.ticket.sum"/></th>
        <th style="width: 10%"><fmt:message key="admin.statistic.ticket.quantity"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ReportDTOS.getItems()}" var="purchase">
        <tr>
            <td>${purchase.userName}</td>
            <td class="text-right pr-4"><fmt:formatNumber value="${purchase.paidSum/100}" type="currency"
                                                          currencySymbol="${currencySign}"
                                                          maxFractionDigits="2"
                                                          minFractionDigits="2"/></td>
            <td class="text-right pr-4">${purchase.ticketQuantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="../page_parts/pagination.jsp" %>
</body>
</html>