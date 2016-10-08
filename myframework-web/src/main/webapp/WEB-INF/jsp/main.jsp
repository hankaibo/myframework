<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link href="//cdn.bootcss.com/pure/0.6.0/pure.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/resources/css/pure.css" rel="stylesheet">
    <title>主页</title>
    <style>
        .accordionMenu h2 {
            position: relative;
            margin: 0;
            padding: 1em 1em .1em;
        }

        .accordionMenu :not(:first-child) h2 {
            padding-top: 0;
        }

        .accordionMenu h2:before {
            border: 5px solid #fff;
            border-color: #fff transparent transparent;
            context: "123";
            /*height:0;*/
            /*width:0;*/
            position: absolute;
            right: 10px;
            /*top:15px;*/
        }

        .accordionMenu h2 a {
            background: linear-gradient(to top, #cecece, #8f8f8f);
            border-radius: 5px;
            color: #424242;
            display: block;
            font-size: 13px;
            margin: 0;
            padding: .5em .5em;
            text-shadow: 2px 2px 2px #aeaeae;
            text-decoration: none;
        }

        .accordionMenu :target h2 a,
        .accordionMenu h2 a:focus,
        .accordionMenu h2 a:hover,
        .accordionMenu h2 a:active {
            background: #2288dd;
            background: linear-gradient(to top, #6bb2ff, #2288dd);
            color: #fff;
        }

        .accordionMenu ul li {
            height: 0;
            overflow: hidden;
            margin-left: 1em;
            transition: height .5s ease-in;
        }

        .accordionMenu :target ul li {
            height: 40px;
            margin-left: 1em;
        }

        .accordionMenu :target h2:before {
            border-color: transparent transparent transparent #fff;
        }
    </style>
</head>
<body>
<div id='container'></div>
<script id="template" type="text/ractive">
    <div class="pure-g">
        <div class="pure-u-1">
            <nav class="pure-menu pure-menu-horizontal">
                <a href="#" class="pure-menu-heading pure-menu-link">BRAND</a>
                <ul class="pure-menu-list">
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">Home</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">系统管理</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">关于我</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">注销</a></li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="pure-g">
        <div class="pure-u-1-5">
            <div class="pure-menu">
                <ul class="pure-menu-list accordionMenu">
                    <li class="menuSection" id="base">
                        <h2 class="pure-menu-heading"><a href="#base">基础管理</a></h2>
                        <ul class="pure-menu-list" on-click="openPage">
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link" value="/depts/gotoList">部门管理</a></li>
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link" value="/logs/toList">日志管理</a></li>
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link">资源管理</a></li>
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link">用户管理</a></li>
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link">角色管理</a></li>
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link">权限管理</a></li>
                        </ul>
                    </li>
                    <li class="menuSection" id="me">
                        <h2 class="pure-menu-heading"><a href="#me">关于我</a></h2>
                        <ul class="pure-menu-list" on-click="openPage">
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link" value="/users/me">个人管理</a></li>
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link" value="/logs/me">个人日志</a></li>
                            <li class="pure-menu-item"><a href="#" class="pure-menu-link" value="/users/me">个人信息</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="pure-u-4-5">
            <iframe id="rightFrame" name="rightFrame" src="/login/home" frameborder=0 scrolling=no height="calc(100vh - 34px)" width="100%" ></iframe>
        </div>
    </div>
    <div class="pure-g">
        <div class="pure-u-1">
            <p class="text-muted text-center">©2015 hankaibo .</p>
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
        "openPage": function (event) {
            var pageSrc = event.original.target.getAttribute('value');
            var rightFrame = document.querySelector('#rightFrame');
            rightFrame.src = pageSrc;
            return false;
        }
    });
</script>
</body>
</html>