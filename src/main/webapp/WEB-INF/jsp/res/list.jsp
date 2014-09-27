<%--
  Created by IntelliJ IDEA.
  User: kaibo
  Date: 2014/8/5
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title></title>
    <link href="${ctx}/resources/js/jquerytreetable/css/screen.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/js/jquerytreetable/css/jquery.treetable.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/js/jquerytreetable/css/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/js/artdialog/css/ui-dialog.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/resources/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryui/jquery-ui-1.10.4.custom.js"></script>
    <script src="${ctx}/resources/js/jquerytreetable/jquery.treetable.js"></script>
    <script src="${ctx}/resources/js/artdialog/dist/dialog-min.js"></script>

</head>
<body>
<div id="dialogdiv" style="display: none">
    <form id="addform" class="form-horizontal" role="form" >
        <input id="parentId" type="hidden" name="parentId" value=""/>
        <div class="form-group">
            <label for="inputName" class="col-sm-2 control-label">资源名称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputName" placeholder="资源名称" name="name">
            </div>
        </div>
        <div class="form-group">
            <label for="inputURL" class="col-sm-2 control-label">URL</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="inputURL" placeholder="URL" name="URL">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">状态</label>
            <div class="col-sm-4">
                <label class="radio-inline">
                    <input type="radio" name="isEnable" id="inlineCheckbox1" value="1"> 开启
                </label>
                <label class="radio-inline">
                    <input type="radio" name="isEnable" id="inlineCheckbox2" value="0"> 禁用
                </label>
            </div>
        </div>
    </form>
</div>
<table id="example-advanced">
    <caption>资源管理<a href="#" class="addroot">添加一级菜单</a> </caption>
    <thead>
    <tr>
        <th>资源名称</th>
        <th>URL</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="res" items="${listres}">
        <tr data-tt-id="${res.id}" <c:if test="${res.pid gt 1}">data-tt-parent-id="${res.pid}"</c:if>>
            <td>${res.name }</td>
            <td>${res.URL }</td>
            <td>
                <c:if test="${res.chkDisabled == true}">启用</c:if>
                <c:if test="${res.chkDisabled == false}">禁用</c:if>
            </td>
            <td><a href="${ctx}/res/${res.id}" class="upmove">修改</a> |
                <a href="#" class="del">删除</a> |
                <a href="#" class="add">添加下级</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="application/javascript">
    $(function(){
        $(".addroot").click(function(){
            divForm(null);
        });

        $("#example-advanced").treetable({
            //是否可折叠，默认false,全部展开。
            expandable: true
        });
        // Highlight selected row
        $("#example-advanced tbody").on("mousedown", "tr", function() {
            $(".selected").not(this).removeClass("selected");
            $(this).toggleClass("selected");
        });

        //move
        $(".upmove").click(function(){
            alert("upmove");
            $("#example-advanced").treetable("move","1.2","1.1");
        });
        $(".downmove").click(function(){
            alert("downmove");
        });
        //delete
        $(document).on("click",".del",function(){
            var id=$(this).parent().parent().attr("data-tt-id");
            var url="${ctx }/res/"+id;
            $.ajax({
                type:"delete",
                url:url,
                dataType:"json",
                success:function(data){
                    $("#example-advanced").treetable("removeNode",id);
                    return true;
                }
            });
        });
        //add
        $(".add").click(function(){
            var id=$(this).parent().parent().attr("data-tt-id");
            var node= $("#example-advanced").treetable("node", id);
            $("#parentId").val(id);
            divForm(node);
        });
    });

    function divForm(parentNode){
        var elem = document.getElementById('dialogdiv');
        var elem = $('#dialogdiv').show()[0];
        var d = dialog({
            title: '添加资源菜单',
            width: 450,
            //content: '<div> <form id="addform" class="form-horizontal" role="form" > <div class="form-group"><label for="inputName" class="col-sm-2 control-label">资源名称</label> <div class="col-sm-4"> <input type="text" class="form-control" id="inputName" placeholder="资源名称" name="name"> </div> </div> <div class="form-group"> <label for="inputURL" class="col-sm-2 control-label">URL</label> <div class="col-sm-4"> <input type="text" class="form-control" id="inputURL" placeholder="URL" name="URL"> </div> </div><div class="form-group"> <label class="col-sm-2 control-label">状态</label> <div class="col-sm-4"> <label class="radio-inline"> <input type="radio" name="isEnable" id="inlineCheckbox1" value="1"> 开启 </label> <label class="radio-inline"> <input type="radio" name="isEnable" id="inlineCheckbox2" value="0"> 禁用 </label> </div> </div></form> </div>',
            content:elem,
            button: [
                {
                    value: '保存',
                    callback: function () {
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: '${ctx}/res',
                            data: $('#addform').serialize(),
                            success: function (data) {
                                var row="<tr data-tt-id='+data.id+' data-tt-parent-id='+data.parentId+'>";
                                row+="<td><span>"+data.name+"</span></td>";
                                row+="<td>"+data.url+"</td>";
                                row+="<td>启用</td>";
                                row+="<td>";
                                row+="<a href='#' class='del'>"+'删除'+"</a>";
                                row+="</td>";
                                d.close();
                                $('#example-advanced').treetable('loadBranch',parentNode,row);
                            },
                            error: function(data) {
                                alert("error:"+data.responseText);
                            }

                        });
                        return false;
                    },
                    autofocus: true
                },
                {
                    value: '取消',
                    callback: function () {
                        return true
                    }
                }
            ]
        }).showModal();
    }
</script>
</body>
</html>
