<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>WEB4J</title>
    <link href='<s:url value="/resources/bootstrap/css/bootstrap.css"/>' rel="stylesheet"/>
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
    </style>
</head>
<body>
	<div class="container">
		<form class="form-signin" action='<s:url value="/login" />' method="post">
			<h2 class="form-signin-heading">用户登录</h2>
			<c:if test="${not empty error}">
				<span style="color:red">${error}</span>
			</c:if>
			<input type="text" name="userName" class="input-block-level" placeholder="用户名"> 
			<input type="password" name="password" class="input-block-level" placeholder="密码"> 
			<label class="checkbox">
				<input type="checkbox" value="remember-me">
				记住用户
			</label>
			<button class="btn btn-large btn-primary" type="submit">登录</button>
		</form>
	</div>
	<!-- /container -->
</body>
</html>
