<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="./../header.jsp" %>
    <link rel="stylesheet" href="${ctx}/resources/js/jquery-ui-bootstrap-masterbs3/third-party/jqGrid/jqGrid/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="${ctx}/resources/js/jquery-ui-bootstrap-masterbs3/third-party/jqGrid/jqGrid.overrides.css" />
    <link rel="stylesheet" href="${ctx}/resources/js/fancyBox/source/jquery.fancybox.css?v=2.1.5" media="screen" />
    <!--  jqGrid -->
    <script src="${ctx}/resources/js/jquery-ui-bootstrap-masterbs3/third-party/jqGrid/jqGrid/js/jquery.jqGrid.src.js"></script>
    <script src="${ctx}/resources/js/jquery-ui-bootstrap-masterbs3/third-party/jqGrid/jqGrid/js/i18n/grid.locale-cn.js"></script>
    <title>资源列表</title>
    <script>
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
                    {name: 'isEnable', index: 'isEnable', width: 140, formatter: 'select', editoptions: {value: "0:禁用;1:启用"}},
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
            })
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
    </script>
</head>
<body>
    <div class="container">
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
</body>
</html>
