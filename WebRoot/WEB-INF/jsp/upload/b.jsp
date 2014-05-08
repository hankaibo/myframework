<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width" />
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../js/FlexPaper_2.2.1/css/flexpaper.css" />
<script type="text/javascript" src="../js/FlexPaper_2.2.1/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/FlexPaper_2.2.1/js/flexpaper.js"></script>
<script type="text/javascript" src="../js/FlexPaper_2.2.1/js/flexpaper_handlers.js"></script>

</head>
<body>
	<p>
		aaaa
	</p>
	<div id="documentViewer" class="flexpaper_viewer" style="width:770px;height:500px"></div>
	
<script type="text/javascript">   
alert(1);
$('#documentViewer').FlexPaperViewer(
        { config : {

            SWFFile : '../swf/201405/book/c.swf',

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
