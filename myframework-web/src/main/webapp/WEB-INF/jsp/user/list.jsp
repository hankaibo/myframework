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
                <table class="pure-table pure-table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>邮箱</th>
                            <th>手机</th>
                            <th>电话</th>
                            <th>生日</th>
                            <th>积分</th>
                            <th>真实姓名</th>
                            <th>是否锁定</th>
                            <th>最后登录IP</th>
                            <th>最后登录日期</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </form>
        </div>
    </div>
</script>

<script src="${pageContext.request.contextPath}/resources/js/ractive.min.js"></script>
<script type="text/javascript">
    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: {
            currentPage:0,
            pageSize:10,
            sort:'id',
            order:'sort',
            list:[]
        }
    });
    var params={
        page:ractive.get('currentPage'),
        rows:ractive.get('pageSize'),
        sort:ractive.get('sort'),
        order:ractive.get('order')
    };
    var strParams=Object.keys(params).map(function (key) {
        return encodeURIComponent(key)+"="+encodeURIComponent(params[key]);
    }).join('&');
    fetch("/users?"+strParams, {
        method: 'get'
    }).then(function (res) {
        if (res.ok) {
            res.json().then(function (data){
                console.log(data);
            });
        } else {
            console.log('error');
        }
    }, function (e) {
        console.error(e);
    });
    ractive.on({});
</script>
</body>
</html>