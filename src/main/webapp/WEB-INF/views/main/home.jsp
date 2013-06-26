<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/headerTag.jsp"></jsp:include>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp"></jsp:include>
	<div class="container">
		<div class="hero-unit">
			<h2>欢迎使用WEB4J</h2>
			<P>系统采用SpringMVC、SPRING-Jdbc、Jquery、Bootstrap开发</P>
			<br>
			<p>系统特点：</p>
			<ul>
				<li>实现了简单的用户、模块、权限管理</li>
				<li>采用RESTful</li>
				<li>增加cache</li>
				<li>采用SPRING验证</li>
				<li>全局JSON转换</li>
			</ul>
		</div>
	</div>
</body>
</html>
