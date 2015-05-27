<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <%@ include file="./../header.jsp" %>
    <link rel="stylesheet" href="${ctx}/resources/js/jquery-ui-bootstrap-masterbs3/third-party/jqGrid/jqGrid/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="${ctx}/resources/js/jquery-ui-bootstrap-masterbs3/third-party/jqGrid/jqGrid.overrides.css" />
    <!--  jqGrid -->
    <script src="${ctx}/resources/js/jquery-ui-bootstrap-masterbs3/third-party/jqGrid/jqGrid/js/jquery.jqGrid.min.js"></script>
    <script src="${ctx}/resources/js/jquery-ui-bootstrap-masterbs3/third-party/jqGrid/jqGrid/js/i18n/grid.locale-cn.js"></script>
<title>日志列表</title>
<script type="text/javascript">
    $(function(){
        $("#gridList").jqGrid({
            url:'${ctx}/logs',  //请求数据的url地址
            datatype: "json",  //请求的数据类型
            mtype:"get",
            colNames:['ID','用户名','记录','时间','IP','操作'], //数据列名称（数组）
            colModel:[ //数据列各参数信息设置
                {name:'id',index:'id', editable:false, width:80,align:'center', title:false},
                {name:'username',index:'username', width:100, title:false},
                {name:'description'},
                {name:'createTime'},
                {name:'ip'},
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
                root:"resultList",              // 数据行（默认为：rows）
                page: "currentPage",            // 当前页
                total: "totalPage",             // 总页数
                records: "total",               // 总记录数
                repeatitems : false             // 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
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
    });
    function displayButtons(cellvalue,options,rowObject){
        var del="<a class='btn' href='#' onclick='delFormData(\""+options.rowId+"\")'>删除</a>";
        return del;
    }
    function delFormData(id){
        if(confirm("您确定要删除该数据吗?")){
            $.ajax({
                url:'${ctx}/logs/'+id,
                type:'delete',
                dataType:'json',
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
                    url:'${ctx}/logs/batch/'+columns,
                    type:'delete',
                    dataType:'json',
                    processData:false,
                    success:function(data){
                        $("#gridList").jqGrid('setGridParam').trigger("reloadGrid");
                    }
                });
            }
        }
    }
</script>
</head>
<body>
<div id="container-fluid" class="container-fluid" style="padding-left: 0">
    <form class="form-inline">
        <label>名称：</label><input type="text" class="input" id="username" required/>
        <input type="button" class="btn btn-success" id="find_btn" value="查 询" />
    </form>
    <table id="gridList"></table>
    <div id="pager"></div>
</div>
</body>
</html>