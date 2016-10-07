<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/pure.css" rel="stylesheet">
    <title>用户列表</title>
</head>
<body>
<div id='container'></div>

<script id="template" type="text/ractive">
<div class="pure-g">
    <div class="pure-u-1-5">
    {{#deptList}}
        {{>deptTree}}
    {{/deptList}}
    </div>
    <div class="pure-u-4-5">
        <p>purecss </p>
        <p>ractive.js</p>
    </div>
</div>

{{#partial deptTree}}
<div>
    {{#if pid==0}}
        <span>{{id}}</span><span>{{name}}</span><span>{{pid}}</span>
        {{>deptTree}}
    {{/if}}
</div>
{{/partial}}

</script>
<script src="${pageContext.request.contextPath}/resources/js/ractive.min.js"></script>
<script type="text/javascript">
    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: {
            deptList:[]
        }
    });

    (function(){
        fetch("http://localhost:8080/depts").then(function(res) {
            if (res.ok) {
                res.json().then(function(data) {
                    console.log(data);
                    ractive.set('deptList',data);
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