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
				<c:set var="headerName" value="${module['new']? '创建新模块':'更新模块' }"></c:set>
				<div class="page-header">
					<h3>${headerName } </h3>
				</div>
				<form:form method="post" modelAttribute="module" cssClass="form-horizontal">
					<web4j:inputField label="模块名" name="moduleName" />
					<web4j:inputField label="描述" name="moduleDesc" />
					<web4j:inputField label="模块代码" name="moduleCode" />
					<web4j:inputField label="父级模块代码" name="parentCode" />
					<web4j:inputField label="路径" name="moduleUrl" />
					<web4j:inputField label="图标" name="moduleIcon" />
					<web4j:inputField label="排列" name="sort"></web4j:inputField>
					<div class="control-group">
						<button type="submit" class="controls btn btn-primary">保存</button>&nbsp;
						<a href='<s:url value="/module/show" />' class="btn">取消</a>
					</div>			
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>