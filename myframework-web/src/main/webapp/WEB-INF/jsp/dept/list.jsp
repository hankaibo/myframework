<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../js.jsp" %>
<title>用户列表</title>
</head>
<body>
<div id='container'></div>

<script id="template" type="text/ractive">
<div class="pure-g">
    <div class="pure-u-1-5">
        <p>purecss </p>
        <p>ractive.js</p>
    </div>
    <div class="pure-u-4-5">
        <p>purecss </p>
        <p>ractive.js</p>
    </div>
</div>


</script>
<script type="text/javascript">
    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: {}
    });
</script>
</body>
</html>