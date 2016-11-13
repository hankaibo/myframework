<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link href="//cdn.bootcss.com/pure/0.6.0/pure.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/resources/css/pure.css" rel="stylesheet">
    <title>用户新增</title>
</head>
<body>
<div id='container'></div>
<script id="template" type="text/ractive">
    <div class="pure-g">
        <div class="pure-u-1">
            <form class="pure-form pure-form-aligned">
                <fieldset>
                    <div class="pure-control-group">
                        <label for="username">用户名</label>
                        <input type="text" value="{{username}}" placeholder="username">
                    </div>
                    <div class="pure-control-group">
                        <label for="password">密码</label>
                        <input type="password" value="{{password}}" placeholder="password">
                    </div>
                    <div class="pure-control-group">
                        <label for="sex">性别</label>
                        <label class="pure-radio">
                            <input type="radio" name="sex" value="1"> 男
                        </label>
                        <label class="pure-radio">
                            <input type="radio" name="sex" value="0"> 女
                        </label>
                        <label class="pure-radio">
                            <input type="radio" name="sex" value="2" checked> 保密
                        </label>
                    </div>
                    <div class="pure-control-group">
                        <label for="birthday">生日</label>
                        <input type="date" placeholder="birthday">
                    </div>
                    <div class="pure-controls">
                        <button type="submit" class="pure-button pure-button-primary" on-click="save">Sign in</button>
                        <button type="reset" class="pure-button pure-button-warning">取消</button>
                    </div>
                </fieldset>
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
            username: '',
            password: ''
        }
    });
    ractive.on({
        save: function () {
            console.log(ractive.get());
            var data = ractive.get();
            fetch("/users", {
                method: 'post',
                headers:{
                    'Content-Type':'application/json'
                },
                body: JSON.stringify(data)
            }).then(function (res) {
                console.log(res);
                if (res.ok) {
                   console.log('ok');
                } else {
                    console.log('error');
                }
            }, function (e) {
                console.error(e);
            });
        }
    });
</script>
</body>
</html>