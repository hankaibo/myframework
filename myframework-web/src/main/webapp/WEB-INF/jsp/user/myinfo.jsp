<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sessionId" value="${pageContext.session.id}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="./../header.jsp" %>
<script type="text/javascript" src="${ctx}/resources/js/imageLens/jquery.imageLens.js"></script>
<title>我的信息</title>
<script type="text/javascript" language="javascript">
    $(function () {
        $("#imagePan").imageLens({ lensSize: 200 });
        $("#img_03").imageLens({ imageSrc: "sample01.jpg" });
        $("#img_04").imageLens({ borderSize: 8, borderColor: "#06f" });
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
                    <label for="inputUsername" class="col-sm-4 control-label">昵称</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputUsername" placeholder="昵称" name="username" value="${userself.username }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputRealName" class="col-sm-4 control-label">真实姓名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputRealName" placeholder="真实姓名" name="realName" value="${userself.realName }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-4 control-label">密码</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="inputPassword" placeholder="密码" name="password" value="${userself.password }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-4 control-label">邮箱</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputEmail" placeholder="邮箱" name="email" value="${userself.email }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputMobile" class="col-sm-4 control-label">手机</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputMobile" placeholder="手机" name="mobile" value="${userself.mobile }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPhone" class="col-sm-4 control-label">电话</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputPhone" placeholder="电话" name="phone" value="${userself.phone }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">性别</label>
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
                    <label for="inputBirthday" class="col-sm-4 control-label">生日</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputBirthday" placeholder="生日" name="birthday" value="<fmt:formatDate value="${userself.birthday}" pattern="yyyy-MM-dd" />"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="">
                    <div id="queue">
                        <img src="${ctx}${userself.pictureUrl}" id="imagePan" width="380" height="380"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>