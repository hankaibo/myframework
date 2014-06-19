<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sessionId" value="${pageContext.session.id}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/resources/js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<%@ include file="./../header.jsp" %>
<script type="text/javascript" src="${ctx}/resources/js/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/uploadify/myuploadify.js"></script>
<title>我的信息</title>
<style type="text/css">

#queue {
    background-color: #FFF;
    border-radius: 3px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25);
    height: 300px;
    margin-bottom: 10px;
    overflow: auto;
    padding: 5px 10px;
    width: 300px;
}
</style>
<script type="text/javascript">
var contentPath="${ctx}";
var sessionId="${sessionId}";
    $(function(){
        myUploadify(false,'1024kb','照片',"*.png;*.jpeg;*.jpg;*.gif","picturePath",false,10);
    });
</script>
</head>
<body>
<div class="container" style="margin: 0;width:100%">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">我的信息</a></li>
        <li><a href="${ctx}/user/me/${userself.id}">修改</a></li>
    </ul>
    <form class="form-horizontal" role="form" action="${ctx }/user" method="post">
        <input type="hidden" name="_method" value="put"/>
        <input type="hidden" value="${userself.id }" name="id"/>
        <input type="hidden" value="${userself.credits }" name="credits"/>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">昵称</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputUsername" placeholder="昵称" name="username" value="${userself.username }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">真实姓名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputRealName" placeholder="真实姓名" name="realName" value="${userself.realName }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">密码</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputPassword" placeholder="密码" name="password" value="${userself.password }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">确认密码</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputEqualPassword" placeholder="确认密码" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">邮箱</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputEmail" placeholder="邮箱" name="email" value="${userself.email }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">手机</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputMobile" placeholder="手机" name="mobile" value="${userself.mobile }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">电话</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputPhone" placeholder="电话" name="phone" value="${userself.phone }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">性别</label>
                    <div class="col-sm-8">
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="inlineCheckbox1" value="1" <c:if test="${userself.sex eq 1 }" >checked</c:if>> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="inlineCheckbox2" value="0" <c:if test="${userself.sex eq 0 }" >checked</c:if>> 女
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="inlineCheckbox3" value="2" <c:if test="${userself.sex eq 2 }" >checked</c:if>> 保密
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-4 control-label">生日</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputBirthday" placeholder="生日" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="">
                    <div id="queue">
                        <div id="finishFile"></div>
                    </div>
                    <input id="file_upload" name="file_upload" type="file" multiple="true"> 
                </div>
                <div>
                    <a class="btn btn-default" href="${ctx}/upload">测试</a>
                    <a class="btn btn-default" href="javascript:$('#file_upload').uploadify('upload')">开始上传</a>
                    <a class="btn btn-default" href="javascript:$('#file_upload').uploadify('cancel', '*')">取消所有</a>
                    <a class="btn btn-default" href="javascript:$('#file_upload').uploadify('stop')">停止上传</a>
                    <!-- <h4>大小:</h4>
                    <div id='progress'></div> -->
                </div>
                
            </div>
        </div>
    </form>
</div>
</body>
</html>