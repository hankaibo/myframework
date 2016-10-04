<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<%@ include file="../home.jsp" %>
<script type="text/javascript">
$(function() {
    $( "#inputBirthday" ).datepicker({
        maxDate: new Date()
    });
});
</script>
<title>用户新增</title>
</head>
<body>
<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/users/toList">用户列表</a></li>
        <li class="active"><a href="#">添加</a></li>
    </ul>
    <form class="form-horizontal" role="form" action="${ctx}/users" method="post">
        <div class="form-group">
            <label for="inputUsername" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputUsername" placeholder="用户名" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="inputPassword" placeholder="密码" name="password">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">性别</label>
            <div class="col-sm-4">
                <label class="radio-inline">
                    <input type="radio" name="sex" id="inlineCheckbox1" value="1"> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="inlineCheckbox2" value="0"> 女
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="inlineCheckbox3" value="2" checked> 保密
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="inputBirthday" class="col-sm-2 control-label">生日</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputBirthday" placeholder="生日" name="birthday">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" class="btn btn-primary active">保存</button>
                <button type="reset" class="btn btn-primary active">取消</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>