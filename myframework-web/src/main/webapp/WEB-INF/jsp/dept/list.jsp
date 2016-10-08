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
        <div>
          {{#deptList}}
            {{>parent}}
          {{/deptList}}
        </div>
    </div>
    <div class="pure-u-4-5">
        <div>

        </div>
    </div>
</div>


{{#partial parent}}
<ul class='folder'>
  {{#children}}
    {{>child}}
  {{/children}}
</ul>
{{/partial}}

{{#partial child}}
<li class='file'>
  <img class='icon-{{id}}'>
  <span>{{name}}</span>
  {{#if parent }}
    {{>parent}}
  {{/if }}
</li>
{{/partial}}



</script>
<script src="${pageContext.request.contextPath}/resources/js/ractive.min.js"></script>
<script type="text/javascript">
    //将json串转换成树形结构
    function transData(a, idStr, pidStr, childrenStr) {
        var r = [], hash = {}, id = idStr, pid = pidStr, children = childrenStr, i = 0, j = 0, len = a.length;
        for (; i < len; i++) {
            hash[a[i][id]] = a[i];
        }
        for (; j < len; j++) {
            var aVal = a[j], hashVP = hash[aVal[pid]];
            if (hashVP) {
                !hashVP[children] && (hashVP[children] = []);
                hashVP[children].push(aVal);
            } else {
                r.push(aVal);
            }
        }
        return r;
    }

    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: {
            deptList: []
        }
    });

    (function () {
        fetch("http://localhost:8080/depts").then(function (res) {
            if (res.ok) {
                res.json().then(function (data) {
//                    console.log(data);
                    ractive.set('deptList', transData(data,'id', 'pid', 'children'));
                });
            } else {
                console.log("Looks like the response wasn't perfect, got status", res.status);
            }
        }, function (e) {
            console.log("Fetch failed!", e);
        });
    })();


</script>
</body>
</html>