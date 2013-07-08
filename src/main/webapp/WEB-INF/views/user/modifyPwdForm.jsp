<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="web4j" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../fragments/headerTag.jsp"></jsp:include>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="span9 offset3">
				<div class="page-header">
					<h3>修改密码 </h3>
				</div>
				<form:form method="post" action="/web4j/user/modifyPwd" modelAttribute="loginUser" cssClass="form-horizontal">
					<web4j:passwordField label="旧密码" name="password" />
					<web4j:passwordField label="新密码" name="newPwd" />
					<div class="control-group">
						<button type="submit" class="controls btn btn-primary">保存</button>&nbsp;
						<a href='<s:url value="/home" />' class="btn">取消</a>
					</div>			
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>