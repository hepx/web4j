<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../fragments/headerTag.jsp"></jsp:include>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
	<jsp:include page="../fragments/bodyHeader.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<jsp:include page="../fragments/menu.jsp"></jsp:include>
			<div class="span9">
				<div class="page-header">
					<h3>模块列表</h3>
				</div>
				<div class="btn-toolbar">
	      			<a href="<s:url value="/module/new" />" class="btn btn-primary">
	      			<i class="icon-plus-sign icon-white"></i> 创建新模块</a>
				</div>
	      		<div class="pub-box" id="moduleTable">
	      		</div>
	      		<div class="pagination">
				    <a href="#" class="first" data-action="first">&laquo;</a>
				    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
				    <input type="text" readonly="readonly" data-max-page="20" />
				    <a href="#" class="next" data-action="next">&rsaquo;</a>
				    <a href="#" class="last" data-action="last">&raquo;</a>
				</div>
				<div class='notifications top-right'></div>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$("a[href='"+(location.pathname+location.search)+"']").parent().addClass('active');
			loadModuleData(start, limit);
			function loadModuleData(start,limit){
				$.ajax({
					url: '${pageContext.request.contextPath}/module/list',
					type: 'POST',
					data: {start:start,limit:limit},
					dataType: 'json'
				}).done(function(data,status,jqXHR){
					var html='';
					html+='<table class="table table-hover table-condensed">';
		            html+='<thead>';
		            html+='<tr>';
		            html+='<th>#</th>';
		            html+='<th>模块名</th>';
		            html+='<th>描述</th>';
		            html+='<th>模块代码</th>';
		            html+='<th>父级模块代码</th>';
		            html+='<th>图标</th>';
		            html+='<th>路径</th>';
		            html+='<th>排列</th>';
		            html+='<th style="width:36px;"></th>';
		            html+='</tr>';
		            html+='</thead>';
		            html+='<tbody>';
		            var maxPage=data.maxPage;
		            var rows=data.rows;
		            if(rows){
		            	$.each(rows,function(i,module){
		              		html+='<tr>';
		              		html+='<td>'+(i+1)+'</td>';
		              		html+='<td>'+module.moduleName+'</td>';
		              		html+='<td>'+module.moduleDesc+'</td>';
		              		html+='<td>'+module.moduleCode+'</td>';
		              		html+='<td>'+module.parentCode+'</td>';
		              		html+='<td><i class="'+module.moduleIcon+'"></i></td>';
		              		html+='<td>'+module.moduleUrl+'</td>';
		              		html+='<td>'+module.sort+'</td>';
		              		html+='<td>';
		              		html+='<a title="修改" href="<s:url value="/module/update/'+module.moduleId+'" />"><i class="icon-pencil"></i></a> ';
		              		html+='<a title="删除" href="<s:url value="/module/delete/'+module.moduleId+'" />"><i class="icon-remove"></i></a>';
		              		html+='</td>';
		              		html+='</tr>';
		            	});
		            }
					html+='</tbody>';
					html+='</table>';
					$('#moduleTable').html(html);
					createModulePagerBar(maxPage);
		            //remove 
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
				}).fail(function(jqXHR,status,error){
					$('.top-right').notify({ message: '错误：'+status+" "+error, type: 'danger'}).show();
				});
			}
			//创建翻页组件
			function createModulePagerBar(maxPage){
		    	$('.pagination').jqPagination({
		    		link_string: '/?page={page_number}',
		    		page_string: "第{current_page}页  共{max_page}页",
		    		max_page: maxPage,
		    		paged: function(page) {
		    			loadModuleData((page-1)*limit, limit);
		    		}
		    	});
			}
		});
	</script>
</body>
</html>