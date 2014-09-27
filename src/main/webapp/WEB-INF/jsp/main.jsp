<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${ctx }/resources/js/ztree/css/zTreeStyle.css" type="text/css">
<%@ include file="header.jsp" %>
<script type="text/javascript" src="${ctx }/resources/js/ztree/jquery.ztree.core-3.5.js"></script>
<title>论坛</title>
<script>
    var curMenu=null,zTree_Menu=null;
    var setting={
        view:{
            showLine:false,
            selectedMulti:false,
            dblClickExpand:false
        },
        data:{simpleData:{enable:true}},
        callback:{
            onNodeCreated:this.onNodeCreated,
            beforeClick:this.beforeClick,
            onClick:this.onClick
        }
    };
    var zNodes =[
        <c:forEach var="res" items="${menuTree}">
        {id:"${res.id}",name:"${res.name}",pId:"${res.pid}",_url:"${res.URL}" },
        </c:forEach>
    ];
    function beforeClick(treeId, node) {
        if (node.isParent) {
            if (node.level === 0) {
                var pNode = curMenu;
                while (pNode && pNode.level !==0) {
                    pNode = pNode.getParentNode();
                }
                if (pNode !== node) {
                    var a = $("#" + pNode.tId + "_a");
                    a.removeClass("cur");
                    zTree_Menu.expandNode(pNode, false);
                }
                a = $("#" + node.tId + "_a");
                a.addClass("cur");

                var isOpen = false;
                for (var i=0,l=node.children.length; i<l; i++) {
                    if(node.children[i].open) {
                        isOpen = true;
                        break;
                    }
                }
                if (isOpen) {
                    zTree_Menu.expandNode(node, true);
                    curMenu = node;
                } else {
                    zTree_Menu.expandNode(node.children[0].isParent?node.children[0]:node, true);
                    curMenu = node.children[0];
                }
            } else {
                zTree_Menu.expandNode(node);
            }
        }
        return !node.isParent;
    }
    function onClick(e, treeId, node) {
        openRight('${ctx}'+node._url)
    }

    $(function() {
        var treeObj = $("#treeDemo");
        $.fn.zTree.init(treeObj, setting, zNodes);
        zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
        curMenu = zTree_Menu.getNodes()[0].children[0];
        zTree_Menu.selectNode(curMenu);

        var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
        a.addClass("cur");

        $("#accordion").accordion({
            collapsible : true
        });
    });
    /*在右边的iframe打开新网页*/
    function openRight(url){
        window.open(url,"rightFrame"); 
    }
</script>
    <style type="text/css">
        .ztree li a.level0 {width:200px;height: 20px; text-align: center; display:block; background-color: #0B61A4; border:1px silver solid;}
        .ztree li a.level0.cur {background-color: #66A3D2; }
        .ztree li a.level0 span {display: block; color: white; padding-top:3px; font-size:12px; font-weight: bold;word-spacing: 2px;}
        .ztree li a.level0 span.button {	float:right; margin-left: 10px; visibility: visible;display:none;}
        .ztree li span.button.switch.level0 {display:none;}
    </style>

</head>
<body>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">My Framework</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">系统管理</a></li>
                    <li><a href="#"><abbr title="I'm a dog." class="initialism">About me</abbr></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="javascript:void(0)">${user.username }</a></li>
                    <li><a href="${ctx}/logout">注销</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container" style="width:95%">
        <div class="row">
            <div class="col-lg-2" style="padding-right: 5px;">
                <div id="treeDemo"></div>
            </div>
            <div class="col-lg-10" style="padding-left: 5px;">
                <iframe id="rightFrame" name="rightFrame" src="" width="100%" height="500px;" frameborder="0"></iframe> 
            </div>
        </div>
    </div>
    
    <div class="navbar navbar-default navbar-fixed-bottom">
        <p class="text-muted">这是bottom部分.</p>
    </div>
</body>
</html>