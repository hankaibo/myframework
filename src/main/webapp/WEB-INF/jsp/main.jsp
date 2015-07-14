<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href="${ctx }/resources/js/ztree/css/zTreeStyle.css" type="text/css">
    <script src="${ctx}/resources/js/ztree/jquery.ztree.core-3.5.js"></script>
    <title>主页</title>
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

        $(function() {
            var treeObj = $("#treeDemo");
            $.fn.zTree.init(treeObj, setting, zNodes);
            zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
            curMenu = zTree_Menu.getNodes()[0].children[0];
            zTree_Menu.selectNode(curMenu);

            var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
            a.addClass("cur");

            $("#rightFrame").load(function(){
                var thisheight = $(this).contents().find("body").height()+50;
                $(this).height(thisheight < 500 ? 500 : thisheight);
            });
        });
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
            window.open('${ctx}'+node._url,"rightFrame");
        }
    </script>
</head>
<body>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand thumbnail" href="#"> <img alt="Brand" src="${ctx}/resources/images/0101.jpg"></a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">系统管理</a></li>
                    <li><a href="#">统计管理</a></li>
                    <li><a href="#">个人管理</a></li>
                    <li><a href="#"><abbr title="I'm a dog." class="initialism">About me</abbr></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)" id="dropdownMenu1" aria-expanded="false">${user.username }<span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">个人信息</a></li>
                        </ul>
                    </li>
                    <li><a href="${ctx}/logout">注销</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 col-lg-offset-1" style="background-color:#e7e7e7;">
                <div id="treeDemo"></div>
            </div>
            <div class="col-lg-9">
                <iframe id="rightFrame" name="rightFrame" src="${ctx}/users/toList" frameborder=0 scrolling=no style="margin-left:0px;margin-top:-3px;width:100%;height:500px;"></iframe>
            </div>
        </div>
    </div>
    
    <div class="navbar navbar-default">
        <p class="text-muted text-center">©2015 hankaibo .</p>
    </div>
</body>
</html>