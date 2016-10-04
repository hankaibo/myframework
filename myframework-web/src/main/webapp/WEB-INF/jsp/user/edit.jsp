<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../home.jsp" %>
    <script src="${ctx}/resources/js/jquery-validation-1.13.1/dist/jquery.validate.min.js"></script>
    <script src="${ctx}/resources/js/jquery-validation-1.13.1/dist/localization/messages_zh.min.js"></script>
    <title>用户修改</title>
    <script type="text/javascript">
        $(function() {
            $( "#inputBirthday" ).datepicker({
                maxDate: new Date()
            });
            $(".form-horizontal").validate({
                onsubmit:true,
                onfocusout:false,
                onkeyup:false,
                rules: {
                },
                submitHandler:function(){
                    var param = $(".form-horizontal").serialize();
                    $.ajax({
                        url:'${ctx}/users/${user.id}',
                        success:function(data){
                            alert("用户修改成功！")
                        },
                        type:'put',
                        dataType:'json',
                        data:param
                    });
                }
            });
        });
    </script>
</head>
<body>
<div class="container" style="margin: 0;width:100%">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/users/toList">用户列表</a></li>
        <li class="active"><a href="#">修改</a></li>
    </ul>
    <form class="form-horizontal" role="form" >
        <input type="hidden" value="${user.id }" name="id"/>
        <input type="hidden" value="${user.credits }" name="credits"/>
        <div class="form-group">
            <label class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputUsername" placeholder="用户名" name="username" value="${user.username }"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="inputPassword" placeholder="密码" name="password" value="${user.password }">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">性别</label>
            <div class="col-sm-4">
                <label class="radio-inline">
                    <input type="radio" name="sex" id="inlineCheckbox1" value="1" <c:if test="${user.sex eq 1 }" >checked</c:if>> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="inlineCheckbox2" value="0" <c:if test="${user.sex eq 0 }" >checked</c:if>> 女
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="inlineCheckbox3" value="2" <c:if test="${user.sex eq 2 }" >checked</c:if>> 保密
                </label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">生日</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputBirthday" placeholder="生日" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" class="btn btn-primary active">修改</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>