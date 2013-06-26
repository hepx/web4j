<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>系统异常</title>
<link href="<s:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<s:url value="/resources/js/jquery.min.js" />"></script>
</head>
<body>
  <div class="hero-unit">
    <h2><font color="red">出错了！！！</font></h2>
    <br />
    <p>异常信息：${exception.message}</p>
    <a id="info" href="#">打开详细信息</a>
    <div id="errorInfo" style="font-size: 12px;background-color: white; display: none;">
		<c:forEach items="${exception.stackTrace}" var="stackTrace"> 
			${stackTrace} 
		</c:forEach>
  	</div>
    <hr>
    <a href="<s:url value="/" />" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> 回到主页</a>
  </div>
  <script type="text/javascript">
  	$('#info').click(function(){
  		$('#errorInfo').slideToggle();
  	});
  </script>
</body>
</html>