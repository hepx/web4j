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
				<c:set var="headerName" value="${user['new']? '创建新用户':'更新用户' }"></c:set>
				<div class="page-header">
					<h3>${headerName } </h3>
				</div>
				<form:form method="post" modelAttribute="user" cssClass="form-horizontal">
					<web4j:inputField label="用户名" name="userName" />
					<c:if test="${user['new'] }">
						<web4j:passwordField label="密码" name="password" />
					</c:if>
					<web4j:inputField label="联系方式" name="phone" />
					<web4j:inputField label="邮箱地址" name="email" />
				    <div class="control-group">
				        <label class="control-label">状态</label>
				        <div class="controls">
				            <label class="radio inline">
				            	<form:radiobutton path="status" value="true"/>
				            	启用
				            </label>
				           	<label class="radio inline">
				            	<form:radiobutton path="status" value="false" />
				            	禁用
				            </label>
				        </div>
				    </div>
					<div class="control-group">
						<button type="submit" class="controls btn btn-primary">保存</button>&nbsp;
						<a href='<s:url value="/user/show" />' class="btn">取消</a>
					</div>	
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//var isnew=${user['new']};
		$(function(){
			if(${user['new']}){
				$('input:radio[name=status]')[0].checked = true;
			}
		});
	</script>
</body>
</html>