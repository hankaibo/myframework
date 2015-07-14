<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="./../header.jsp" %>
<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${ctx}/resources/js/jqGrid-4.8.2/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/resources/js/jqGrid-4.8.2/css/ui.jqgrid-bootstarp.css" />
<script type="text/javascript" src="${ctx }/resources/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/ztree/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<!--  jqGrid -->
<script src="${ctx}/resources/js/jqGrid-4.8.2/js/jquery.jqGrid.src.js"></script>
<script src="${ctx}/resources/js/jqGrid-4.8.2/js/i18n/grid.locale-en.js"></script>
<title>用户列表</title>
<script type="text/javascript">
    $(function(){
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            edit: {
                enable: true,//设置 zTree 是否处于编辑状态
                editNameSelectAll: false,//节点编辑名称 input 初次显示时,设置 txt 内容是否为全选状态。
                showRemoveBtn: showRemoveBtn,//设置是否显示删除按钮。
                showRenameBtn: showRenameBtn//设置是否显示编辑名称按钮。
            },
            view: {
                addHoverDom: addHoverDom,//用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
                removeHoverDom: removeHoverDom,//用于当鼠标移出节点时，隐藏用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
                selectedMulti: false//设置是否允许同时选中多个节点。
            },
            callback: {
                beforeDrag: beforeDrag,//用于捕获节点被拖拽之前的事件回调函数，并且根据返回值确定是否允许开启拖拽操作
                beforeEditName: beforeEditName,//用于捕获节点编辑按钮的 click 事件，并且根据返回值确定是否允许进入名称编辑状态
                beforeRemove: beforeRemove,//用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
                beforeRename: beforeRename,//用于捕获节点编辑名称结束（Input 失去焦点 或 按下 Enter 键）之后，更新节点名称数据之前的事件回调函数，并且根据返回值确定是否允许更改名称的操作
                onRemove: onRemove,//用于捕获删除节点之后的事件回调函数。
                onRename: onRename//用于捕获节点编辑名称结束之后的事件回调函数。
            }
        };

        var zNodes =[
             <c:forEach var="dept" items="${listdepts}">
                 {id:"${dept.id}",name:"${dept.name}",pId:"${dept.pid}" },
             </c:forEach>
        ];

        var className = "dark";
        /*用于捕获节点被拖拽之前的事件回调函数，并且根据返回值确定是否允许开启拖拽操作*/
        function beforeDrag(treeId, treeNodes) {
            return false;
        }
        
        /*用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作。*/
        function beforeRemove(treeId, treeNode) {
            className = (className === "dark" ? "":"dark");
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.selectNode(treeNode);
            if(confirm("确认删除 节点 -- " + treeNode.name + " 吗？")){
                var url="${ctx }/dept/"+treeNode.id;
                $.ajax({
                    type:"delete",
                    url:url,
                    dataType:"json",
                    success:function(data){
                        return true;
                    }
                });
            }
        }
        /*用于捕获删除节点之后的事件回调函数。*/
        function onRemove(e, treeId, treeNode) {
            alert("删除节点成功！");
        }
        
        
        /*用于捕获节点编辑按钮的 click 事件，并且根据返回值确定是否允许进入名称编辑状态*/
        function beforeEditName(treeId, treeNode) {
            className = (className === "dark" ? "":"dark");
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.selectNode(treeNode);
            return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
        }
        /*用于捕获节点编辑名称结束（Input 失去焦点 或 按下 Enter 键）之后，更新节点名称数据之前的事件回调函数，并且根据返回值确定是否允许更改名称的操作*/
        function beforeRename(treeId, treeNode, newName, isCancel) {
            className = (className === "dark" ? "":"dark");
            if (newName.length == 0) {
                alert("节点名称不能为空.");
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                setTimeout(function(){zTree.editName(treeNode)}, 10);
                return false;
            }else{
                var url="${ctx }/dept";
                var paramData={"name":newName,"id":treeNode.id};
                $.ajax({
                    url:url,
                    type:"PUT",
                    contentType:"application/json",
                    data:JSON.stringify(paramData),
                    dataType:"json",
                    success:function(data){
                        return true;
                    }
                });
            }
        }
        /*用于捕获节点编辑名称结束之后的事件回调函数。*/
        function onRename(e, treeId, treeNode, isCancel) {
            alert("编辑节点名称成功！");
        }
        
        
        /*设置是否显示删除按钮。*/
        function showRemoveBtn(treeId, treeNode) {
//             return !treeNode.isFirstNode;
            return !treeNode.isParent;//设置所有的父节点不显示删除按钮
        }
        /*设置是否显示编辑名称按钮。*/
        function showRenameBtn(treeId, treeNode) {
//             return !treeNode.isLastNode;
            return true;
        }
        
        /*用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮*/
        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0){
                return;
            }
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='添加下级' onfocus='this.blur();'></span>";
            var upStr = "<span id='upBtn_" + treeNode.tId
                + "' title='上移' onfocus='this.blur();'>上移</span>";
            var downStr = "<span id='downBtn_" + treeNode.tId
                + "' title='下移' onfocus='this.blur();'>下移</span>";
            sObj.after(addStr);
            //禁止父节点和首尾节点的上下移。
            if(!treeNode.isParent && !treeNode.isFirstNode){
                sObj.after(upStr);
            }
            if(!treeNode.isParent && !treeNode.isLastNode){
                sObj.after(downStr);
            }
            //添加下级
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn){
                btn.bind("click", function(){
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    $.ajax({
                        type:"post",
                        url:"${ctx }/dept",
                        data:"id="+treeNode.id,
                        dataType:"json",
                        success:function(data){
                            zTree.addNodes(treeNode, {id:data.id, pId:treeNode.id, name:data.name});
                            return true;
                        }
                    });
                });
            }
            //上移（兄弟节点之间）
            var upBtn = $("#upBtn_"+treeNode.tId);
            if (upBtn){
                upBtn.bind("click", function(){
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    var sNodes = zTree.getSelectedNodes();
                    var upNode = sNodes[0].getPreNode();
                    $.ajax({url:"${ctx }/dept/"+treeNode.id+"/"+upNode.id+"/up",
                        type:"GET",
                        dataType:"json",
                        success:function(data){
                            zTree.moveNode(upNode,treeNode,"prev");
                            return true;
                        },
                        error:function(XMLHttpRequest, textStatus, errorThrown) {
                            this; // 调用本次AJAX请求时传递的options参数
                        }
                    });
                });
            }
            //下移（兄弟节点之间）
            var downBtn = $("#downBtn_"+treeNode.tId);
            if (downBtn){
                downBtn.bind("click", function(){
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    var sNodes = zTree.getSelectedNodes();
                    var downNode = sNodes[0].getNextNode();
                    $.ajax({url:"${ctx }/dept/"+treeNode.id+"/"+downNode.id+"/down",
                        type:"GET",
                        dataType:"json",
                        success:function(data){
                            zTree.moveNode(downNode,treeNode,"next");
                            return true;
                        },
                        error:function(XMLHttpRequest, textStatus, errorThrown) {
                            this; // 调用本次AJAX请求时传递的options参数
                        }
                    });
                });
            }
        };
        $(function(){
            $("#gridList").jqGrid({
                url:'${ctx}/users',  //请求数据的url地址
                datatype: "json",  //请求的数据类型
                mtype:"get",
                colNames:['ID','名称','性别'], //数据列名称（数组）
                colModel:[ //数据列各参数信息设置
                    {name:'id',index:'id', editable:false, width:80,align:'center', title:false},
                    {name:'username',index:'username', width:100, title:false},
                    {name:'sex',index:'sex', width:40, formatter:'select', editoptions:{value:"0:女;1:男;2:保密"}}
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
        /*用于当鼠标移出节点时，隐藏用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮*/
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_"+treeNode.tId).unbind().remove();
            $("#upBtn_"+treeNode.tId).unbind().remove();
            $("#downBtn_"+treeNode.tId).unbind().remove();
        };
        
        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
    });
</script>
<style type="text/css">
.ztree li span.button.add {
    margin-left: 2px;
    margin-right: -1px;
    background-position: -144px 0;
    vertical-align: top;
    *vertical-align: middle
}
</style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <ul id="treeDemo" class="ztree"></ul>
        </div>
        <div class="col-md-8">
            <form class="form-inline">
                <label>名称：</label><input type="text" class="input" id="username" required/>
                <input type="button" class="btn btn-success" id="find_btn" value="查 询" />
                <input type="button" class="btn btn-primary pull-right fancybox" id="add_btn" href="#addDiv" value="新 增" />
            </form>
            <table id="gridList"></table>
            <div id="pager"></div>
        </div>
    </div>
</div>
</body>
</html>