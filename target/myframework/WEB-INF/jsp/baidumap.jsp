<%--
  Created by IntelliJ IDEA.
  User: kaibo
  Date: 2014/8/3
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>百度地图测试之自定义地图类型</title>
    <script src="http://api.map.baidu.com/api?v=1.5&ak=54ee741e32573451bfe237956bf9bfd3" type="text/javascript"></script>
    <script type="text/javascript">
        function initialize() {
            var tilelayer = new BMap.TileLayer();                                                      // 创建地图层实例
            tilelayer.getTilesUrl = function (tileCoord, zoom) {                                       // 设置图块路径
                var x = tileCoord.x;
                var y = tileCoord.y;
                return '${ctx}/resources/tiles/' + zoom + '/tile' + x + '_' + y + '.png';
            };

            var MyMap = new BMap.MapType('MyMap', tilelayer, {minZoom: 8, maxZoom: 10});
            var map = new BMap.Map('container', {mapType: MyMap});                                      // 创建地图实例
            var point = new BMap.Point(0, 0);                                                           // 创建点坐标
            map.centerAndZoom(point, 10);

            map.addControl(new BMap.NavigationControl());                                               //地图平移缩放控件
            map.addControl(new BMap.ScaleControl());                                                    //比例尺控件
            map.addControl(new BMap.OverviewMapControl());                                              //缩略地图控件

            var marker = new BMap.Marker(new BMap.Point(0, 0));                                         // 创建标注
            map.addOverlay(marker);                                                                     // 将标注添加到地图中


            var infoWindow = new BMap.InfoWindow("普通标注");                                            //创建信息窗口
            marker.addEventListener("click", function () {
                this.openInfoWindow(infoWindow);
            });
        }
        window.onload = initialize;
    </script>
</head>
<body>
<div id="container" style="width:800px;height:400px;"></div>
</body>
</html>
