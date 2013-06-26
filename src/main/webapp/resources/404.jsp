<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Error 404</title>
<link href="<s:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<style>
  .center {text-align: center; margin-left: auto; margin-right: auto; margin-bottom: auto; margin-top: auto;}
</style>
</head>
<body>
<body>
  <div class="hero-unit center">
    <h2><font color="red">Error 404</font></h2>
    <br />
    <p>您所访问的网页无法找到,请联系您的网络管理员或再试一次。</p>
    <p><b>或者你可以按下这个小小的按钮:</b></p>
    <a href="<s:url value="/" />" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> 回到主页</a>
  </div>
  <br />
</body>
</html>