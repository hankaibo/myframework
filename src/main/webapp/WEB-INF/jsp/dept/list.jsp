<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/resources/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/ztree/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/ztree/jquery.ztree.excheck-3.5.min.js"></script>
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
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</body>
</html>