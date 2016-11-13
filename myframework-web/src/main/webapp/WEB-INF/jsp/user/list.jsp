<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link href="//cdn.bootcss.com/pure/0.6.0/pure.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/resources/css/pure.css" rel="stylesheet">
    <title>用户列表</title>
</head>
<body>

<div id='container'></div>
<script id="template" type="text/ractive">
    <div class="pure-g">
        <div class="pure-u-1">
            <form class="form-inline">
                 <div class="form-group pull-right">
                    <input type="button" class="btn btn-default pull-right " id="add_btn" value="新增" on-click="gotoAdd"/>
                    <a href="${ctx}/users/toAdd" >跳转到完整添加</a>

                </div>
            </form>
        </div>
    </div>
</script>

<script src="${pageContext.request.contextPath}/resources/js/ractive.min.js"></script>
<script type="text/javascript">
    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: {}
    });
    ractive.on({

    });
</script>
</body>
</html>