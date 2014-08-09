<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="./../header.jsp" %>
<title>用户列表</title>
<script type="text/javascript">
    $(function(){
        $('.pagination').jqPagination({
            /*参数*/
            current_page: "${page.currentPage}",//当前页，默认为1.
            max_page    : "${page.totalPage}",//最大页，默认为null
            page_string :"当前第{current_page}页,共{max_page}页",//默认值为'Page {current_page} of {max_page}
            /*回调方法*/
            paged       : function(pageNum) {
                //如果是一般动态页或者是静态页的话，就直接跳转到相应的页面   
                location.href="${ctx}/user/users?currentPage="+pageNum;
            }
        }); 
    });
</script>
</head>
<body>
<div class="container" style="margin: 0;width:100%">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">用户列表</a></li>
        <li><a href="<c:url value="/user"/>">添加</a></li>
        <a href="${ctx}/user/down/${page.currentPage}" class="btn btn-default" role="button">
            <span class="glyphicon glyphicon-download-alt"></span> Down
        </a>
    </ul>
    
    <table class="table table-striped table-bordered table-hover table-condensed">
        <tbody>
            <tr>
                <th>ID</th>
                <th>用户名</th>
                <th>性别</th>
                <th>生日</th>
                <th>积分</th>
                <th>操作</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id }</td>
                    <td>${user.username }</td>
                    <td>${user.sex }</td>
                    <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" /></td>
                    <td>${user.credits }</td>
                    <td><a href="${ctx}/user/${user.id}">修改</a>|
                        <a href="${ctx}/user/${user.id}/delete">删除</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    <ul class="gigantic pagination">
                        <li><a href="#" class="first" data-action="first">&laquo;</a></li>
                        <li><a href="#" class="previous" data-action="previous">&lsaquo;</a></li>
                        <input type="text" readonly="readonly" />
                        <li><a href="#" class="next" data-action="next">&rsaquo;</a></li>
                        <li><a href="#" class="last" data-action="last">&raquo;</a></li>
                    </ul>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>