<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jqueryui/css/ui-lightness/jquery-ui-1.10.4.custom.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jqueryui/jquery-ui-1.10.4.custom.js"></script>
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
                    <li><a href="#">About</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="javascript:void(0)">${user.username }</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout/index.html">注销</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container" style="padding: 0 15px">
        <div class="row">
            <div class="col-lg-3">
                <div id="accordion">
                    <h3>系统管理</h3>
                    <div>
                        <ul class=" ">
                            <li><a href="javascript:void(0)" onclick="openRight('<c:url value="/user/list.html"/>')">用户列表</a></li>
                            <li><a href="javascript:void(0)" onclick="openRight('<c:url value="/dept/list.html"/>')">部门列表</a></li>
                            <li><a href="javascript:void(0)" onclick="openRight('<c:url value="/log/list.html"/>')">日志列表</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-9">
                <iframe id="rightFrame" name="rightFrame" src="" width="100%" height="400px;" ></iframe> 
            </div>
        </div>
    </div>
    
    <div class="navbar navbar-default navbar-fixed-bottom">
        <p class="text-muted">这是bottom部分.</p>
    </div>
</body>
</html>