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
			<P>系统后端采用Spring MVC、SPRING JDBC+ECH缓存</P>
			<P>前端采用Jquery、Bootstrap</P>
		</div>
	</div>
</body>
</html>
