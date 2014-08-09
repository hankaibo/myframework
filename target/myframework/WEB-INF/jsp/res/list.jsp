<%--
  Created by IntelliJ IDEA.
  User: kaibo
  Date: 2014/8/5
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title></title>
    <%@ include file="./../header.jsp" %>
    <link href="${ctx}/resources/js/jquerytreetable/css/jquery.treetable.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/js/jquerytreetable/css/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/resources/js/jquerytreetable/jquery.treetable.js"></script>
    <script>
        $("#example-basic").treetable();
    </script>
</head>
<body>
    <table id="example-basic">
        <tr data-tt-id="1">
            <td>Parent</td>
        </tr>
        <tr data-tt-id="2" data-tt-parent-id="1">
            <td>Child</td>
        </tr>
    </table>
</body>
</html>
