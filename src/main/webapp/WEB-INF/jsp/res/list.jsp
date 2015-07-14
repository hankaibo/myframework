<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="./../header.jsp" %>
    <link rel="stylesheet" href="${ctx}/resources/js/jqGrid-4.8.2/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="${ctx}/resources/js/jqGrid-4.8.2/css/ui.jqgrid-bootstarp.css" />
    <link rel="stylesheet" href="${ctx}/resources/js/fancyBox/source/jquery.fancybox.css?v=2.1.5" media="screen" />
    <link rel="stylesheet" href="${ctx }/resources/js/ztree/css/zTreeStyle.css" type="text/css">
    <!--  jqGrid -->
    <script src="${ctx}/resources/js/jqGrid-4.8.2/js/jquery.jqGrid.src.js"></script>
    <script src="${ctx}/resources/js/jqGrid-4.8.2/js/i18n/grid.locale-en.js"></script>
    <!-- fancyBox -->
    <script src="${ctx}/resources/js/fancyBox/source/jquery.fancybox.pack.js?v=2.1.5"></script>
    <%--ztree--%>
    <script src="${ctx}/resources/js/ztree/jquery.ztree.core-3.5.js"></script>
    <title>资源列表</title>
    <script>
        var setting = {
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "all"
            },
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        };
//        登录时，资源信息被放置在session中，故可以直接获取到
        var zNodes =[
            <c:forEach var="res" items="${menuTree}">
                {id:"${res.id}",name:"${res.name}",pId:"${res.pid}" },
            </c:forEach>
        ];
        $(function() {
            $("#gridList").jqGrid({
                treeGrid:true,
                treeGridModel:'nested',
                ExpandColumn:'name',
                url: '${ctx}/resources/',  //请求数据的url地址
                datatype: "json",  //请求的数据类型
                mtype: "get",
                colNames: ['ID', '资源名称', 'URL', '状态', '操作'], //数据列名称（数组）
                colModel: [ //数据列各参数信息设置
                    {name: 'id', hidden:true},
                    {name: 'name', index: 'name',editable:true},
                    {name: 'url', index: 'url', width: 140,editable:true},
                    {name: 'isEnable', index: 'isEnable', width: 140, formatter: 'select', editoptions: {value: "false:禁用;true:启用"}},
                    {name: 'action', align: 'center', sortable: false, formatter: displayButtons}
                ],
                shrinkToFit:true,
                autowidth: true, //自动匹配宽度
                caption: "资源列表",
                gridview: true, //加速显示
                height: '100%',
                sortable: false,  //可以排序
                jsonReader: {
                    root: "listBaseRes",              // 数据行（默认为：rows）
                    repeatitems: false             // 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
                },
                cellEdit: true,
                cellsubmit:'remote',
                cellurl:'${ctx}/resources/',
                treeReader : {
                    level_field: "level",
                    left_field:"lft",
                    right_field: "rgt",
                    leaf_field: "isLeaf",
                    expanded_field: "expanded"
                },
                loadComplete: function (data) { //完成服务器请求后，回调函数
                    if (data.records == 0) { //如果没有记录返回，追加提示信息，删除按钮不可用
                        $("p").appendTo($("#gridList")).addClass("nodata").html('找不到相关数据！');
                        $("#del_btn").attr("disabled", true);
                    } else { //否则，删除提示，删除按钮可用
                        $("p.nodata").remove();
                        $("#del_btn").removeAttr("disabled");
                    }
                },
//                切换到编辑模式前可以在这里修改单元格内容作为输入控件的值
//                formatCell:function (rowid, cellname, value, iRow, iCol){
//                },
                //在单元格切换到编辑模式前触发事件
                beforeEditCell: function (rowid, cellname, value, iRow, iCol) {
                    var rec = $("#gridList").jqGrid('getRowData', rowid);
                    if (cellname == 'url' && rec[cellname]=='' ) {
                        setTimeout(function () {
                            jQuery("#gridList").jqGrid('restoreCell', iRow, iCol);
                            //===>或者设置为只读
                            //$('#' + rowid + '_amount').attr('readonly', true);
                        }, 1);
                    }
                    if (cellname == 'name') {
                        setTimeout(function () {
                            jQuery("#gridList").jqGrid('restoreCell', iRow, iCol);
                            //===>或者设置为只读
                            //$('#' + rowid + '_amount').attr('readonly', true);
                        }, 1);
                    }
                },
                //在单元格内容保存前触发，你可以在这个事件里面存储发往服务器的内容
//                beforeSaveCell:function(rowid, cellname, value, iRow, iCol){
//                },
                //在数据发到服务器前触发，此方法可以返回json对象附加额外的数据
                beforeSubmitCell : function(rowid,celname,value,iRow,iCol) {
                    //列提交前的拦截方法
                    var $gridList = $("#gridList") ;
                    var $editUrl = '${ctx}/resources/'+rowid ;
                    //给jqgrid 从新设置cellurl 值
                    $gridList.setGridParam({cellurl:$editUrl});
                    return {'url':value};
                },
                gridComplete: function () {
                }
            });

            $(".fancybox").fancybox();

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            $.fn.zTree.getZTreeObj("treeDemo").expandAll(true);
            $("#addParentId").click(function(){
                var parentIdObj = $("#addParentId");
                var parentIdOffset = $("#addParentId").offset();
                $("#menuContent").css({left:parentIdOffset.left + "px", top:parentIdOffset.top + parentIdObj.outerHeight() + "px"}).slideDown("fast");

                $("body").bind("mousedown", onBodyDown);
            });
        })
        function displayButtons(cellvalue,options,rowObject){
            var del="<a class='btn' onclick=\"delFormData('"+options.rowId+"')\" >删除</a>";
            return del;
        }
        function delFormData(id){
            if(confirm("您确定要删除该数据吗?")){
                $.ajax({
                    url:'${ctx}/resources/'+id,
                    type:'delete',
                    dataType:'json',
                    contentType:'application/json',
                    success:function(){
                        $("#demoGrid").jqGrid('setGridParam').trigger("reloadGrid");
                    }
                });
            }
        }



        function beforeClick(treeId, treeNode) {
//            var check = (treeNode && !treeNode.isParent);
//            if (!check) alert("只能选择叶子节点...");
//            return check;
        }

        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
                    n = "";
            nodes.sort(function compare(a,b){return a.id-b.id;});
            for (var i=0, l=nodes.length; i<l; i++) {
                v += nodes[i].name + ",";
            }
            for (var i=0, l=nodes.length; i<l; i++) {
                n += nodes[i].id + ",";
            }
            if (v.length > 0 ) v = v.substring(0, v.length-1);
            if (n.length > 0 ) n = n.substring(0, n.length-1);
            var addParentIdObj = $("#addParentId");
            addParentIdObj.attr("value", n);
            addParentIdObj.html(v);
        }

        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                hideMenu();
            }
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
    </script>
</head>
<body>
    <div class="container-fluid">
        <div>
            <div>
                <form>
                    <label>名称：</label><input type="text" class="input" id="username" required/>
                    <input type="button" class="btn btn-success" id="find_btn" value="查 询" />
                    <input type="button" class="btn btn-primary pull-right fancybox" id="add_btn" href="#addDiv" value="新 增" />
                </form>
            </div>
        </div>
        <table id="gridList"></table>
        <div id="pager"></div>
    </div>
    <%--新增--%>
    <div id="addDiv" class="container" style="display: none; margin: 0; width:500px;">
        <h3 class="text-center">资源新建</h3>
        <form class="form-horizontal" role="form" action="${ctx}/resources/" method="post">
            <div class="form-group">
                <label for="addName" class="col-sm-3 control-label">资源名称</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="addName" placeholder="资源名称" name="name">
                </div>
            </div>
            <div class="form-group">
                <label for="addUrl" class="col-sm-3 control-label">URL</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="addUrl" placeholder="密码" name="url">
                </div>
            </div>
            <div class="form-group">
                <label for="addParentId" class="col-sm-3 control-label">上级目录</label>
                <div class="col-sm-6">
                    <input id="addParentId" type="text" class="form-control" readonly name="parentId" value="1" />根节点
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">状态</label>
                <div class="col-sm-6">
                    <label class="radio-inline">
                        <input type="radio" name="isEnable" id="isEnable1" value="1"> 启用
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="isEnable" id="isEnable2" value="0"> 禁用
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <button type="submit" class="btn btn-primary active">保存</button>
                    <button type="reset" class="btn btn-primary active">取消</button>
                </div>
            </div>
        </form>
    </div>
    <div id="menuContent" class="" style="display:none; position: absolute;z-index:8099">
        <ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>
</body>
</html>
