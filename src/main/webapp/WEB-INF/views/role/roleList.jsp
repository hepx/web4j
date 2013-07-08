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
										<a title="权限管理" href="#" onclick="authMgr(${role.roleId })"><i class="icon-lock"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- Modal -->
				<div id="authModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
				    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				    	<h3 id="myModalLabel">权限管理</h3>
				  	</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
					    <button id="saveRole" class="btn btn-primary">保存</button>
					</div>
				</div>
				<div class='notifications top-right'></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('.icon-remove').parent().click(function(e){
			var a=$(this);
        	$.get(a.attr('href')).done(function(data){
        		a.parent('td').parent('tr').remove();
        		$('.top-right').notify({ message: '删除成功！', type: 'success'}).show();
        	}).fail(function(data){
        		$('.top-right').notify({ message: data.responseText, type: 'danger'}).show();
        	});
			return false;
		});
		$('#saveRole').click(function(){
			$.post('${pageContext.request.contextPath}/auth/saveAuth',$('#authForm').serialize())
			.done(function(data){
				$('#authModal').modal('hide');
				$('.top-right').notify({ message: '保存成功！', type: 'success'}).show();
			}).fail(function(data){
				$('.top-right').notify({ message: data.responseText, type: 'danger'}).show();
			});
		});
		function authMgr(roleId){
			$.getJSON('${pageContext.request.contextPath}/auth/queryAuth',{roleId:roleId})
			.done(function(data) {
				var modules=data.modules;
				var moduleIds=data.moduleIds;
				if(modules){
					var html='';
					html+='<form id="authForm" class="form-inline" >';
					html+='<input type="hidden" name="roleId" value="'+roleId+'">';
					$.each(modules,function(i,module){
						html+='<div class="controls">';
						html+='<label class="checkbox">';
						html+='<input type="checkbox" name="moduleIds" value="'+module.moduleId+'">'+module.moduleName;
						html+='</label>';
						html+='</div>';
						html+='<hr>';
						if(module.subModules){
							html+='<div class="controls" style="padding-left: 30px;">';
							$.each(module.subModules,function(y,subModule){
								html+='<label class="checkbox inline">';
								html+='<input type="checkbox" name="moduleIds" value="'+subModule.moduleId+'">'+subModule.moduleName;
								html+='</label> ';
							});
							html+='</div>';
						}
					});
					html+='</form>';
					$('.modal-body').html(html);
					$('#authModal').modal('show');
					$('#authModal').on('shown',function(){
						$('input[name=moduleIds]').val(moduleIds);
					});
				}
			}).fail(function(jqxhr, textStatus, error) {
				$('.top-right').notify({ message: '错误：'+textStatus+" "+error, type: 'danger'}).show();
			});
		}
	</script>
</body>
</html>