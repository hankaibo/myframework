<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<title>用户新增</title>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="<c:url value="/user/list.html"/>">用户列表</a></li>
        <li class="active"><a href="#">添加</a></li>
    </ul>
    <form class="form-horizontal" role="form" action="<c:url value="/user/add.html"/>" method="post">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputUsername" placeholder="用户名" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">性别</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputSex" placeholder="性别" name="sex">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">生日</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputBirthday" placeholder="生日" name="birthday">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" class="btn btn-primary">保存</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>