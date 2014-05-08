<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String appContextPath = ((HttpServletRequest) request).getContextPath();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/FlexPaper/js/jquery.min.js"></script>
<script type="text/javascript" src="<%= appContextPath %>/resources/FlexPaper/js/flexpaper.js"></script>
<script type="text/javascript" src="<%= appContextPath %>/resources/FlexPaper/js/flexpaper_handlers.js"></script>
<link type="text/css" rel="stylesheet" href="<%= appContextPath %>/resources/FlexPaper/css/flexpaper.css" />
<title>阅读文件</title>

</head>
<body>
    <div class="container">
        <div style="position: absolute; left: 10px; top: 10px;">
            <div id="documentViewer" class="flexpaper_viewer" style="width: 770px; height: 500px"></div>
        </div>
    </div>
    <script type="text/javascript">
    var startDocument = "Paper";

    $('#documentViewer').FlexPaperViewer(
            { config : {
                SWFFile : encodeURI('${pageContext.request.contextPath}/swf/swf/${file.fileName }'),

                Scale : 0.6,
                ZoomTransition : 'easeOut',
                ZoomTime : 0.5,
                ZoomInterval : 0.2,
                FitPageOnLoad : true,
                FitWidthOnLoad : false,
                FullScreenAsMaxWindow : false,
                ProgressiveLoading : false,
                MinZoomSize : 0.2,
                MaxZoomSize : 5,
                SearchMatchAll : false,
                InitViewMode : 'Portrait',
                RenderingOrder : 'flash',
                StartAtPage : '',

                ViewModeToolsVisible : true,
                ZoomToolsVisible : true,
                NavToolsVisible : true,
                CursorToolsVisible : true,
                SearchToolsVisible : true,
                WMode : 'window',
                localeChain: 'en_US'
            }}
    );
    </script>
</body>
</html>