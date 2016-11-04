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
        <div id="main" style="min-width:300px;height:800px"></div>
    </div>
</div>

</script>
<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts-more.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ractive.min.js"></script>
<script type="text/javascript">
    var ractive = new Ractive({
        el: '#container',
        template: '#template',
        data: {}
    });
    ractive.on({
        upload:function () {
            uploadFiles();
        }
    });

    function createChart(data) {
        $('#main').highcharts({
            chart: {
                type: 'columnrange',
                inverted: true
            },
            title: {
                text: '考勤图示'
            },
            xAxis: {
                categories: data.categories
            },
            yAxis: { },
            plotOptions: {
                columnrange: {
                    dataLabels: {
                        enabled: true,
                        formatter: function () {
                            return this.y;
                        }
                    }
                }
            },
            series: [{
                name: '工作中',
                data: data.data
            }]
        });
    }

    function uploadFiles() {
        var files=document.querySelector("#file").files;
        var reader=new FileReader();
        var file=files[0];
        var data=new FormData();
        data.append('file',file);
        data.append('user','hankaibo');

        reader.onload=function(e){
            fetch("/users/me/addendance/upload",{
                method:'post',
//                headers:{
//                    'Content-Type':'multipart/form-data'
//                },
                body:data
            })
            .then(function (res) {
                console.log(res);
                if(res.ok){
                    res.json().then(function (data){
                        createChart(data);
                    });
                } else{
                    console.log('error');
                }
            },function (e) {
                console.error(e);
            });
        }.bind(this);
        reader.readAsBinaryString( file );
    }
</script>
</body>
</html>
