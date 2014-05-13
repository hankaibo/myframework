<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css" />
<script type="text/javascript" src="${ctx}/resources/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<title>新建文章</title>
<script type="text/javascript">
    UE.getEditor('inputContent',{
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
        toolbars:[['fullscreen', 'source', '|', 'undo', 'redo', '|',
                   'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
                   'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                   'insertimage', 'insertvideo', 'music', '|', 'pagebreak', 'horizontal', '|',
                   'print', 'preview']],
        //focus时自动清空初始化时的内容
        autoClearinitialContent:true,
        //关闭字数统计
        wordCount:false,
        //关闭elementPath
        elementPathEnabled:false,
        //默认的编辑区域高度
        initialFrameHeight:300
        //更多其他参数，请参考ueditor.config.js中的配置项
    });
</script>
</head>
<body>
<div class="container" style="margin: 0;width:100%">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/article/articles">文章列表</a></li>
        <li class="active"><a href="#">新建</a></li>
    </ul>
    <form class="form-horizontal" role="form" action="${ctx}/article" method="post">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">文章标题</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputTitle" placeholder="文章标题" name="title">
            </div>
            <label for="inputEmail3" class="col-sm-2 control-label">文章作者</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputAuthor" placeholder="文章作者" name="author">
            </div>
        </div>
<!--         <div class="form-group"> -->
<!--             <label for="inputEmail3" class="col-sm-2 control-label">文章类型</label> -->
<!--             <div class="col-sm-4"> -->
<!--             </div> -->
<!--         </div> -->
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">内容</label>
            <div class="col-sm-10">
                <script id="inputContent" placeholder="内容" name="content" type="text/plain"></script>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" class="btn btn-primary active">保存</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>