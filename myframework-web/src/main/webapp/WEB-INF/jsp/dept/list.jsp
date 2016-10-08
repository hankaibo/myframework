<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/pure.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <title>用户列表</title>
</head>
<body>
<div id='container'></div>

<script id="template" type="text/ractive">
<div class="pure-g">
    <div class="pure-u-1-5">
          {{#deptList}}
            {{>parent}}
          {{/deptList}}
    </div>
    <div class="pure-u-4-5">
        <table class="pure-table pure-table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Year</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td>1</td>
                    <td>Honda</td>
                    <td>Accord</td>
                    <td>2009</td>
                </tr>

                <tr>
                    <td>2</td>
                    <td>Toyota</td>
                    <td>Camry</td>
                    <td>2012</td>
                </tr>

                <tr>
                    <td>3</td>
                    <td>Hyundai</td>
                    <td>Elantra</td>
                    <td>2010</td>
                </tr>
            </tbody>
        </table>
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
  <i class="fa fa-plus-square-o"></i>
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
                    ractive.set('deptList', transData(data, 'id', 'pid', 'children'));
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