<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/error.css" />
<title>404</title>
</head>
<body>
    <div id="container">
        <img class="png" src="${pageContext.request.contextPath}/resources/images/404.png" /> 
        <img class="png msg" src="${pageContext.request.contextPath}/resources/images/404_msg.png" />
        <p>
            <a href="${pageContext.request.contextPath}/login/index.html"><img class="png"
                src="${pageContext.request.contextPath}/resources/images/404_to_index.png" /></a>
        </p>
    </div>
    <div id="cloud" class="png"></div>
    <pre style="DISPLAY: none"></pre>
</body>
</html>