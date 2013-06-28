<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<jsp:include page="../fragments/headerTag.jsp"></jsp:include>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<jsp:include page="../fragments/menu.jsp"></jsp:include>
	      	<div class="span9">
	      		<div class="page-header">
	      			<h3>用户列表</h3>
	      		</div>
	      		<div class="btn-toolbar">
	      			<a href='<s:url value="/user/new" />' class="btn btn-primary">
	      			<i class="icon-user icon-white"></i> 创建新用户</a>
				</div>
	      		<div class="pub-box" id="userTable">
	      		</div>
	      		<div class="pagination">
				    <a href="#" class="first" data-action="first">&laquo;</a>
				    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
				    <input type="text" readonly="readonly" data-max-page="20" />
				    <a href="#" class="next" data-action="next">&rsaquo;</a>
				    <a href="#" class="last" data-action="last">&raquo;</a>
				</div>
	      	</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			loadUserInfoData(start,limit);
			//ajax请求数据
			function loadUserInfoData(start,limit){
				$.ajax({
					url: '${pageContext.request.contextPath}/user/list',
					type: 'POST',
					data:{start:start,limit:limit},
					dataType:'json'
				}).done(function(data,textStatus,jqXHR){
					var html="";
					html+='<table class="table table-hover table-condensed">';
		            html+='<thead>';
		            html+='<tr>';
		            html+='<th>#</th>';
		            html+='<th>用户名</th>';
		            html+='<th>角色</th>';
		            html+='<th>邮箱地址</th>';
		            html+='<th>联系方式</th>';
		            html+='<th>状态</th>';
		            html+='<th style="width:36px;"></th>';
		            html+='</tr>';
		            html+='</thead>';
		            html+='<tbody>';
					var maxPage=data.maxPage;
					var rows=data.rows;
					if(rows){
						$.each(rows,function(i,userInfo){
		              		html+='<tr>';
		              		html+='<td>'+(i+1)+'</td>';
		              		html+='<td>'+userInfo.userName+'</td>';
		              		html+='<td>'+userInfo.roleName+'</td>';
		              		html+='<td>'+userInfo.email+'</td>';
		              		html+='<td>'+userInfo.phone+'</td>';
		              		html+='<td>'+(userInfo.status==true?'<span class="badge badge-success">启用</span>':
		              			'<span class="badge badge-important">禁用</span')+'</td>';
		              		html+='<td>';
		              		html+='<a title="修改" href="<s:url value="/user/update/'+userInfo.userId+'" />"><i class="icon-pencil"></i></a> ';
		              		html+='<a title="删除" href="<s:url value="/user/delete/'+userInfo.userId+'" />"><i class="icon-remove"></i></a>';
		              		html+='</td>';
		              		html+='</tr>';
						});
					}
		            html+='</tbody>';
		            html+='</table>';
		            $('#userTable').html(html);
		            createUserPagerBar(maxPage);
		            //remove 
		    		$('.icon-remove').parent().click(function(e){
		    			var a=$(this);
		    			$.get(a.attr('href'),function(data){
		    				if(data==='success'){
		    					a.parent('td').parent('tr').remove();
		    				}
		    			});
		    			return false;
		    		});
				}).fail(function(jqXHR,textStatus,error){
					alert("出错了"+textStatus+","+error);
				});
			}
			//创建翻页组件
			function createUserPagerBar(maxPage){
		    	$('.pagination').jqPagination({
		    		link_string: '/?page={page_number}',
		    		page_string: "第{current_page}页  共{max_page}页",
		    		max_page: maxPage,
		    		paged: function(page) {
		    			loadUserInfoData((page-1)*limit, limit);
		    		}
		    	});
			}
		});
	</script>
</body>
</html>