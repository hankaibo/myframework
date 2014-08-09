<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/singin.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jqueryui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
<%@ include file="header.jsp" %>
<title>登陆</title>
<script type="text/javascript">
    $(function() {
        $('#kaptchaImage').click(function() {
            $(this).attr('src', '${ctx}/login/captcha-image?' + Math.floor(Math.random() * 100));
            event.cancelBubble=true; 
        });
    });
</script>
</head>
<body>
    <div class="container">
        <c:if test="${!empty error }">
            <font color="red"><c:out value="${error }" /></font>
        </c:if>
        <form class="form-signin" role="form" action="${ctx}/login" method="post">
            <h2 class="form-signin-heading">请登录</h2>
            <input class="form-control" autofocus="" required="" type="text" placeholder="用户名" name="userName">
            <input class="form-control" required="" type="password" placeholder="密码" name="password">
<!--             <input class="form-control" required="" type="text" placeholder="验证码" name="kaptcha"> -->
<%--             <img src="${ctx}/login/captcha-image" id="kaptchaImage" /> --%>
            
            <label class="checkbox"> <input type="checkbox" name="rememberMe" value="true"></input>一天内记住我的登录状态</label>
            <button class="btn btn-primary btn-lg btn-block" type="submit">登陆</button>
        </form>
    </div>
</body>
</html>