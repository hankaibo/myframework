<%--
  Created by IntelliJ IDEA.
  User: JUSFOUN
  Date: 2016-11-3
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link href="//cdn.bootcss.com/pure/0.6.0/pure.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/resources/css/pure.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Muli" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/visavail.css" rel="stylesheet" type="text/css">
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <title>attendance</title>
</head>
<body>
<div id='container'></div>

<script id="template" type="text/ractive">
<div class="pure-g">
    <div class="pure-u-1">
        <form id="uploadForm" enctype="multipart/form-data">
            <input type="file" id="file">
            <button type="button" class="button button-success" on-click="upload">upload</button>
        </form>
        <p id="example">
    </div>
</div>
</script>
<script src="http://cdn.bootcss.com/moment.js/2.17.0/moment-with-locales.min.js"></script>
<script src="http://cdn.bootcss.com/d3/3.5.17/d3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ractive.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/visavail.js"></script>
<script type="text/javascript">
    moment.locale("zh-cn");
    var dataset = [];
    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: []
    });
    ractive.on({
        upload: function () {
            uploadFiles();
        }
    });

    function createChart(data) {
        // draw Visavail.js chart
        var chart = visavailChart().width(800);
        for(var i=0;i<data.data.length;i++){
            var foo={};
            foo.measure=data.categories[i];
            foo.data=[[data.data[i][0],1,data.data[i][1]]];
            dataset.push(foo);
        }
        for(var j=0;j<dataset.length;j++){
            console.log(dataset[j].data);
        }
        console.log(dataset);
        d3.select("#example").datum(dataset).call(chart);
    }

    function uploadFiles() {
        var files = document.querySelector("#file").files;
        var reader = new FileReader();
        var file = files[0];
        var data = new FormData();
        data.append('file', file);
        data.append('user', 'hankaibo');

        reader.onload = function (e) {
            fetch("/users/me/addendance/upload", {
                method: 'post',
//                headers:{
//                    'Content-Type':'multipart/form-data'
//                },
                body: data
            }).then(function (res) {
                console.log(res);
                if (res.ok) {
                    res.json().then(function (data) {
                        createChart(data);
                    });
                } else {
                    console.log('error');
                }
            }, function (e) {
                console.error(e);
            });
        }.bind(this);
        reader.readAsBinaryString(file);
    }
</script>
</body>
</html>
