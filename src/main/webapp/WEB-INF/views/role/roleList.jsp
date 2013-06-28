<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
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
				<div class="page-header">
					<h3>角色列表</h3>
				</div>
				<div class="btn-toolbar">
					<a href="<s:url value="/role/new" />" class="btn btn-primary"><i class="icon-plus-sign icon-white"></i> 创建一个新的角色</a>
				</div>
				<div class="pub-box">
					<table class="table table-hover table-condensed">
						<thead>
							<tr>
								<th>#</th>
								<th>角色名</th>
								<th>角色代码</th>
								<th>描述</th>
								<th style="width: 54px;"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" varStatus="status" items="${roles }">
								<tr>
									<td>${status.index+1 }</td>
									<td>${role.roleName }</td>
									<td>${role.roleCode }</td>
									<td>${role.roleDesc }</td>
									<td>
										<a title="修改" href='<s:url value="/role/update/${role.roleId }" />'><i class="icon-pencil"></i></a> 
										<a title="删除" href='<s:url value="/role/delete/${role.roleId }" />'><i class="icon-remove"></i></a> 
										<a title="权限管理" href="#"><i class="icon-lock"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('.icon-remove').parent().click(function(e){
			var a=$(this);
			$.get(a.attr('href'),function(data){
				if(data==='success'){
					a.parent('td').parent('tr').remove();
				}
			});
			return false;
		});
	</script>
</body>
</html>