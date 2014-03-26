<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
</head>
<body>
    <form action="<c:url value="/user/edit.html"/>" method="post">
        <input type="hidden" value="${user.id }" name="id"/>
        <input type="hidden" value="${user.credits }" name="credits"/>
        <table align="center">
            <tbody>
                <tr>
                    <td colspan="6">用户修改</td>
                </tr>
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="username" value="${user.username }"/></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td><input type="text" name="sex" value="${user.sex }"/></td>
                </tr>
                <tr>
                    <td>生日:</td>
                    <td><input type="text" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />"/></td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: right;"><input type="submit" value="修改"/></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>