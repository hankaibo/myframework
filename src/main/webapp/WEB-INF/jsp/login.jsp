<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/bootstrap-3.3.4/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/js/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" />
    <title>登陆</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4 col-md-offset-4" ms-controller="login">
                <form action="${ctx}/login" method="post">
                    <h1 class="text-center">请登录</h1>
                    <input class="form-control" type="text" placeholder="用户名" name="username" ms-duplex={{username}} required autofocus>
                    <input class="form-control" type="password" placeholder="密码" name="password" ms-duplex={{password}} required>
                    <div ms-visible="{{isCaptcha}}">
                        <input class="form-control" type="text" placeholder="验证码" name="kaptcha" required>
                        <img src="${ctx}/login/captcha-image" id="kaptchaImage" />
                    </div>
                    <%--<label class="checkbox"> <input type="checkbox" name="rememberMe" value="true"/>一天内记住我的登录状态</label>--%>
                    <button class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
                </form>
            </div>
        </div>
    </div>
<script src="/resources/js/avalon.js"></script>
<script src="/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
    var vm=avalon.define({
        $id:"login",
        username:"123",
        password:"",
        isCaptcha:false
    });
</script>
</body>
</html>