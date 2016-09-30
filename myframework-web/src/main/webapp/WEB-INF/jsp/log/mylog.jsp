<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="./../header.jsp" %>
<title>日志列表</title>
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
                location.href="${ctx}/log/me?currentPage="+pageNum;
            }
        }); 
    });
</script>
</head>
<body>
<div class="container" style="margin: 0;width:100%">
    <ul class="nav nav-tabs">
      <li class="active"><a href="#">我的日志</a></li>
    </ul>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <tbody>
            <tr>
                <th>ID</th>
                <th>记录</th>
                <th>时间</th>
                <th>IP</th>
                <th>操作</th>
            </tr>
            <c:forEach var="log" items="${logs}">
                <tr>
                    <td>${log.id }</td>
                    <td>${log.description }</td>
                    <td><fmt:formatDate value="${log.createTime}" type="both" /></td>
                    <td>${log.ip }</td>
                    <td><a href="${ctx}/log/${log.id}/delete">删除</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    <ul class="pagination">
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