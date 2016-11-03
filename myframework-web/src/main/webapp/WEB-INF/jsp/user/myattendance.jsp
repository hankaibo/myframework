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
            <input type="file" id="input" onchange="handleFiles(this.files)">
            <button id="upload" type="button">upload</button>
        </form>

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

    function handleFiles(files) {
        var reader=new FileReader();
        var numFiles=files.length;
        var file=files[0];
        var data=new FormData();
        data.append('file',file);
        data.append('user','hankaibo');
        console.group("上传文件信息");
        console.log("文件数:%d个。",numFiles);
        console.log("文件名称:%s。",file.name);
        console.log("文件大小:%d kb。",file.size);
        console.log("文件类型:%s。",file.type);
        console.dir(file);
        console.groupEnd();

        reader.onload=function(e){
            fetch("/users/me/addendance/upload",{
                method:'post',
//                headers:{
//                    'Content-Type':'multipart/form-data'
//                },
                body:data
            })
            .then()
            .then(function (data) {
                console.log(data);
            })
            .catch(function (error) {
                console.log(error);
            });
        }.bind(this);
        reader.readAsBinaryString( file );
    }
</script>
</body>
</html>
