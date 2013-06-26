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
			<jsp:include page="../fragments/menu.jsp"></jsp:include>
			<div class="span9">
				<c:set var="headerName" value="${module['new']? '创建新角色':'更新角色' }"></c:set>
				<div class="page-header">
					<h3>${headerName } </h3>
				</div>
				<form:form method="post" modelAttribute="role" cssClass="form-horizontal">
					<web4j:inputField label="角色名" name="roleName" />
					<web4j:inputField label="角色代码" name="roleCode" />
					<web4j:textarea label="描述" name="roleDesc" />
					<div class="control-group">
						<button type="submit" class="controls btn btn-primary">保存</button>&nbsp;
						<a href='<s:url value="/role/show" />' class="btn">取消</a>
					</div>		
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>