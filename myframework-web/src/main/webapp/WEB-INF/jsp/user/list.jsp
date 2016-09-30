<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <%@ include file="./../header.jsp" %>
    <link rel="stylesheet" href="${ctx}/resources/js/jqGrid-4.8.2/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="${ctx}/resources/js/jqGrid-4.8.2/css/ui.jqgrid-bootstarp.css" />
    <link rel="stylesheet" href="${ctx}/resources/js/fancyBox/source/jquery.fancybox.css?v=2.1.5" media="screen" />
    <link rel="stylesheet" href="${ctx}/resources/js/uploadify/uploadify.css" />
    <%--jquery.uploadify.js--%>
    <script type="text/javascript" src="${ctx}/resources/js/uploadify/jquery.uploadify.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/uploadify/myuploadify.js"></script>
    <!--  jqGrid -->
    <script src="${ctx}/resources/js/jqGrid-4.8.2/js/jquery.jqGrid.src.js"></script>
    <script src="${ctx}/resources/js/jqGrid-4.8.2/js/i18n/grid.locale-en.js"></script>
    <!-- fancyBox -->
    <script src="${ctx}/resources/js/fancyBox/source/jquery.fancybox.pack.js?v=2.1.5"></script>
    <title>用户列表</title>
    <script type="text/javascript">
        var contentPath="${ctx}";
        var sessionId="${sessionId}";
        $(function(){
            $("#gridList").jqGrid({
                url:'${ctx}/users',  //请求数据的url地址
                datatype: "json",  //请求的数据类型
                mtype:"get",
                colNames:['ID','名称','性别','生日','最后访问时间','积分','操作'], //数据列名称（数组）
                colModel:[ //数据列各参数信息设置
                    {name:'id',index:'id', editable:false, width:80,align:'center', title:false},
                    {name:'username',index:'username', width:100, title:false},
                    {name:'sex',index:'sex', width:40, formatter:'select', editoptions:{value:"0:女;1:男;2:保密"}},
//                    {name:'birthday',index:'birthday', width:120,formatter:"date",formatoptions: {srcformat:'Y-m-d',newformat:'Y-m-d'}},
                    {name:'strBirthday',index:'strBirthday', width:120},
                    {name:'strLastVisit',index:'strLastVisit', width:160},
                    {name:'credit',index:'credit', width:100,align:'center'},//,formatter:fancyBoxFormatter},
                    {name:'action',align:'center',sortable:false,formatter:displayButtons}
                ],
                rowNum:10, //每页显示记录数
                rowList:[10,20,30], //分页选项，可以下拉选择每页显示记录数
                pager: '#pager',  //表格数据关联的分页条，html元素
                autowidth: true, //自动匹配宽度
                caption: "用户列表",
                gridview:true, //加速显示
                viewrecords: true,  //显示总记录数
                multiselect: true,  //可多选，出现多选框
                multiselectWidth: 25, //设置多选列宽度
                height: '100%',
                sortable:true,  //可以排序
                sortname: 'id',  //排序字段名,可以是列名称或者是一个数字，这个参数会被提交到后台.
                sortorder: "desc", //排序方式：倒序，本例中设置默认按id倒序排序
                jsonReader:{
                    root:"list",              // 数据行（默认为：rows）
                    page: "pageNum",            // 当前页
                    total: "pages",             // 总页数
                    records: "total",           // 总记录数
                    repeatitems : false         // 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
                },
                loadComplete:function(data){ //完成服务器请求后，回调函数
                    if(data.records==0){ //如果没有记录返回，追加提示信息，删除按钮不可用
                        $("p").appendTo($("#gridList")).addClass("nodata").html('找不到相关数据！');
                        $("#del_btn").attr("disabled",true);
                    }else{ //否则，删除提示，删除按钮可用
                        $("p.nodata").remove();
                        $("#del_btn").removeAttr("disabled");
                    }
                }
            });
            $("#gridList").jqGrid('navGrid','#pager',{add:false,del:false,edit:false,search:false});
            $("#gridList").jqGrid('navButtonAdd','#pager',{caption:"",title:"删除所选记录",buttonicon:'ui-icon-trash',onClickButton:function(){batchDelFormData();}});
            //查询
            $("#find_btn").click(function(){
                var username = $.trim($("#username").val());
                if(username){
                    $("#gridList").jqGrid('setGridParam',{
                        dataType:'json',
                        postData:{'username':username}
                    }).trigger("reloadGrid"); //重新载入
                }
            });
            //遮盖层
            $(".fancybox").fancybox();
            //导入用户
            myUploadify("/upload/import",false,'1024kb','表格',"*.xls;*.xlsx","foo",false,10);
        });
        function fancyBoxFormatter ( cellvalue, options, rowObject ){
            var result,link,fancyBoxHTML,fancyBoxContent;
            link = "<a class=\"fancybox\" href=\"#data" + options.rowId + "\">" + cellvalue + "</a>";
            fancyBoxHTML = "<div style=\"display:none\"><div id=\"data" + options.rowId + "\">" + fancyBoxContent + "</div></div>";

            return link + fancyBoxHTML;
        }
        function displayButtons(cellvalue,options,rowObject){
            var edit="<a class='btn btn-default' role='button' href='${ctx}/users/"+options.rowId+"'>修改</a>";
            var del="<a class='btn btn-default' role='button' href='#' onclick='delFormData(\""+options.rowId+"\")'>删除</a>";
            return edit+del;
        }
        function delFormData(id){
            if(confirm("您确定要删除该数据吗?")){
                $.ajax({
                    url:'${ctx}/users/'+id,
                    type:'delete',
                    dataType:'json',
                    contentType:'application/json',
                    success:function(){
                        $("#gridList").jqGrid('setGridParam').trigger("reloadGrid");
                    }
                });
            }
        }
        function batchDelFormData(){
            var ids=$("#gridList").jqGrid('getGridParam','selarrrow');
            if(ids==""){
                alert("请选择要删除的记录!");
            }else{
                if(confirm("您确定要删除这些数据吗?")){
                    var columns=[];
                    for(var i=0;i<ids.length;i++){
                        columns.push($("#gridList").jqGrid('getCell',ids[i],'id'));
                    }
                    $.ajax({
                        url:'${ctx}/users/batch/'+columns,
                        type:'delete',
                        dataType:'json',
                        contentType:'application/json',
                        processData:false,
                        success:function(data){
                            $("#gridList").jqGrid('setGridParam').trigger("reloadGrid");
                        }
                    });
                }
            }
        }
    </script>
    <style>
    #queue {
    background-color: #FFF;
    border-radius: 3px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25);
    height: 330px;
    margin-bottom: 10px;
    overflow: auto;
    padding: 5px 10px;
    width: 350px;
    }
    </style>
</head>
<body>
    <div id="container-fluid" class="container-fluid" style="padding-left: 0">
        <form class="form-inline">
            <div class="form-group">
                <label>名称：</label><input type="text" class="input" placeholder="名称" id="username" required />
                <input type="button" class="btn btn-success" id="find_btn" value="查 询" />
            </div>
            <div class="form-group pull-right">
                <a class="btn btn-default" role="button" href="javascript:$('#file_upload').uploadify('upload')">导入</a>
                <input type="button" class="btn btn-default pull-right fancybox" id="add_btn" href="#addDiv" value="新增" />
            </div>
            <div class="form-group pull-right">
                <div>
                    <div id="finishFile"></div>
                </div>
                <input class="btn" id="file_upload" name="file_upload" type="file" multiple="true">
            </div>
        </form>
        <table id="gridList"></table>
        <div id="pager"></div>
    </div>
    <%--新增--%>
    <div id="addDiv" class="container" style="display: none; margin: 0; width:500px;">
        <h3 class="text-center">用户新建</h3>
        <form class="form-horizontal" role="form" action="${ctx}/users" method="post">
            <div class="form-group">
                <label for="addUsername" class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="addUsername" placeholder="用户名" name="username">
                </div>
            </div>
            <div class="form-group">
                <label for="addPassword" class="col-sm-3 control-label">密码</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="addPassword" placeholder="密码" name="password">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">性别</label>
                <div class="col-sm-6">
                    <label class="radio-inline">
                        <input type="radio" name="addSex" id="addSex1" value="1"> 男
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="addSex" id="addSex2" value="0"> 女
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="addSex" id="addSex3" value="2" checked> 保密
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label for="addBirthday" class="col-sm-3 control-label">生日</label>
                <div class="col-sm-6">
                    <input type="datetime-local" class="form-control" id="addBirthday" placeholder="生日" name="birthday">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <button type="submit" class="btn btn-primary active">保存</button>
                    <button type="reset" class="btn btn-primary active">取消</button>
                </div>
            </div>
        </form>
        <a href="${ctx}/users/toAdd" >跳转到完整添加</a>
    </div>
</body>
</html>