<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css" />
<title>上传文件</title>
</head>
<body>
<div class="container" style="margin: 0;width:100%">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/readonline/flashs">swf文件列表</a></li>
        <li><a href="${ctx}/readonline/files">文件列表</a></li>
        <li class="active"><a href="#">上传</a></li>
    </ul>
    <form class="form-horizontal" role="form" action="${ctx}/readonline" method="post" enctype="multipart/form-data">
        <fieldset>
            <label>File:</label><input type="file" name="file"/><br/>
        </fieldset>
        <input type="submit" name="submit" value="upload"/>
    </form>
</div>
</body>
</html>