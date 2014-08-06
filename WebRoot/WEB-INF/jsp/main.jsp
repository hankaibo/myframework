<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jqueryui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
<%@ include file="header.jsp" %>
<title>论坛</title>
<script>
    /*手风琴*/
    $(function() {
        $("#accordion").accordion({
            collapsible : true
        });
    });
    /*在右边的iframe打开新网页*/
    function openRight(url){
        window.open(url,"rightFrame"); 
    }
</script>
</head>
<body>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">My Framework</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">系统管理</a></li>
                    <li><a href="#"><abbr title="I'm a dog." class="initialism">About me</abbr></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="javascript:void(0)">${user.username }</a></li>
                    <li><a href="${ctx}/logout">注销</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container" style="width:95%">
        <div class="row">
            <div class="col-lg-2" style="padding-right: 5px;">
                <div id="accordion">
                    <h3>管理</h3>
                    <div>
                        <ul class="list-unstyled">
                            <li><a href="javascript:void(0)" onclick="openRight('${ctx}/user/users')">用户管理</a></li>
                            <li><a href="javascript:void(0)" onclick="openRight('${ctx}/dept/depts')">部门管理</a></li>
                            <li><a href="javascript:void(0)" onclick="openRight('${ctx}/log/logs')">日志管理</a></li>
                            <li><a href="javascript:void(0)" onclick="openRight('${ctx}/res/resources')">资源管理</a></li>
                            <li><a href="javascript:void(0)" onclick="openRight('${ctx}/log/logs')">权限管理</a></li>
                        </ul>
                    </div>
                    <h3>统计</h3>
                    <div>
                        <ul class="list-unstyled">
                            <li><a href="javascript:void(0)" onclick="openRight('${ctx}/user/analysis')">用户分析</a></li>
                        </ul>
                    </div>
                    <h3>个人</h3>
                    <div>
                        <ul class="list-unstyled">
                            <li><a href="javascript:void(0)" onclick="openRight('${ctx}/user/me')">我的信息</a></li>
                            <li><a href="javascript:void(0)" onclick="openRight('${ctx}/log/me')">我的记录</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-10" style="padding-left: 5px;">
                <iframe id="rightFrame" name="rightFrame" src="" width="100%" height="500px;" frameborder="0"></iframe> 
            </div>
        </div>
    </div>
    
    <div class="navbar navbar-default navbar-fixed-bottom">
        <p class="text-muted">这是bottom部分.</p>
    </div>
</body>
</html>