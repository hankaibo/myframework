<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="header.jsp" %>
    <title>登陆</title>
    <script type="text/javascript">
        $(function() {
            $('#kaptchaImage').click(function() {
                $(this).attr('src', '${ctx}/login/captchaImage?' + Math.floor(Math.random() * 100));
            });
        });
    </script>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <c:if test="${!empty error }">
                    <span color="red"><c:out value="${error }" /></span>
                </c:if>
                <form action="${ctx}/login" method="post">
                    <h1 class="text-center">请登录</h1>
                    <input class="form-control" type="text" placeholder="用户名" name="username" required autofocus>
                    <input class="form-control" type="password" placeholder="密码" name="password" required>
                    <c:if test="${isCaptcha}">
                        <input class="form-control" type="text" placeholder="验证码" name="kaptcha" required>
                        <img src="${ctx}/login/captcha-image" id="kaptchaImage" />
                    </c:if>
                    <%--<label class="checkbox"> <input type="checkbox" name="rememberMe" value="true"/>一天内记住我的登录状态</label>--%>
                    <button class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>