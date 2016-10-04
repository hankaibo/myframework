<%--
  Created by IntelliJ IDEA.
  User: kaibo
  Date: 2015/4/9
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="../home.jsp" %>
<script type="text/javascript" src="${ctx}/resources/js/echarts-2.2.1/build/dist/echarts.js"></script>
<script type="text/javascript">
//路径配置
require.config({
    paths:{echarts:'${ctx}/resources/js/echarts-2.2.1/build/dist'}
});
//使用
require(
    [
    'echarts',
    'echarts/chart/bar'// 使用柱状图就加载bar模块，按需加载
    ],
    function(ec){
        var myChartSex=ec.init(document.getElementById('main'));
        var option={
            tooltip:{show:true},
            legend:{data:['性别人数']},
            xAxis:[{
                type:'category',
                data:["男","女","保密"]
            }],
            yAxis:[{
                type:'value'
            }],
            series:[{
                "name":"人数",
                "type":"bar",
                "data":[${sexCount.get("男")},${sexCount.get("女")},${sexCount.get("保密")}]
            }]
        };
        //
        myChartSex.setOption(option);
    }
);

</script>
</head>
<body>
<div class="container" style="width:95%">
    <div class="row">
        <div id="main" style="height: 400px;" ></div>
    </div>
</div>
</body>
</html>
