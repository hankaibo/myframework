<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//cdn.bootcss.com/pure/0.6.0/pure.css" rel="stylesheet">
    <title>登陆</title>
</head>
<body>
<div id='container'></div>

<script id="template" type="text/ractive">
<div class="pure-g">
    <div class="pure-u-1-3"></div>
    <div class="pure-u-1-3">
        <form class="pure-form pure-form-stacked" action="/login" method="post">
            <fieldset class="pure-group">
                <legend>Login</legend>
                <input class="pure-input-1" type="text" placeholder="用户名" name="username" required autofocus>
                <input class="pure-input-1" type="password" placeholder="密码" name="password" required>
            </fieldset>

            <button class="pure-button pure-button-primary pure-input-1" type="submit">登录</button>
        </form>
    </div>
    <div class="pure-u-1-3"></div>
</div>


</script>
<script src="//cdn.bootcss.com/ractive/0.7.3/ractive.min.js"></script>
<script type="text/javascript">
    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: {
            username: '',
            password: ''
        }
    });
</script>
</body>
</html>