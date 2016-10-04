<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <link href="//cdn.bootcss.com/pure/0.6.0/pure.css" rel="stylesheet">
    <title>日志列表</title>
</head>
<body>
<div id='container'></div>
<script id="template" type="text/ractive">
    <div class="pure-g">
        <div class="pure-u-1">
            <table class="pure-table pure-table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>记录</th>
                        <th>时间</th>
                        <th>IP</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    {{#logList}}
                    <tr>
                        <td>{{id}}</td>
                        <td>{{username}}</td>
                        <td>{{description}}</td>
                        <td>{{createTime}}</td>
                        <td>{{ip}}</td>
                        <td>del</td>
                    </tr>
                    {{/}}
                </tbody>
            </table>
        </div>
    </div>
</script>
<script src="//cdn.bootcss.com/ractive/0.7.3/ractive.min.js"></script>
<script type="text/javascript">
    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: {
            logList:[],
            currentPage:0,
            pageSize:10
        }
    });
    ractive.on({
        "openPage": function (event) {
            var pageSrc = event.original.target.getAttribute('value');
            var rightFrame = document.querySelector('#rightFrame');
            rightFrame.src = pageSrc;
            return false;
        }
    });
    (function(){
        fetch("http://localhost:8080/logs").then(function(res) {
            // res instanceof Response == true.
            if (res.ok) {
                res.json().then(function(data) {
                    console.log(data.list);
                    ractive.set('logList',data.list);
                });
            } else {
                console.log("Looks like the response wasn't perfect, got status", res.status);
            }
        }, function(e) {
            console.log("Fetch failed!", e);
        });
    })();

</script>
</body>
</html>