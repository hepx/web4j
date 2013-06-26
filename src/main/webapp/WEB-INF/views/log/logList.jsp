<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	      			<h3>日志列表</h3>
	      		</div>
				<form id="logForm" class="form-search">
					<div class="input-append">
						<input id="msg" type="text" class="span2 search-query" placeholder="查询内容">
				    	<button type="submit" class="btn"><i class="icon-search"></i></button>
				  	</div>
				</form>
				<div class="pub-box" id="logTable">
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
		var msg=null;
		$(function(){
			loadLogData(start,limit,msg);
			$('#logForm').submit(function(){
				loadLogData(start,limit,$('#msg').val());
				return false;
			});
			function createLogPagerBar(maxPage){
		    	$('.pagination').jqPagination({
		    		link_string: '/?page={page_number}',
		    		page_string: "第{current_page}页  共{max_page}页",
		    		max_page: maxPage,
		    		paged: function(page) {
		    			loadLogData((page-1)*limit, limit,$('#msg').val());
		    		}
		    	});
			}
	        function loadLogData(start,limit,msg){
	        	$.ajax({
	        		url: '${pageContext.request.contextPath}/log/list',
	                type: 'POST',
	                data: {start: start,limit: limit,msg:msg},
	                dataType: 'json'
	        	}).done(function(data,textStatus,jqXHR){
	        		var html='';
		            html+='<table class="table table-hover table-condensed">';
					html+='<thead>';
					html+='<tr>';
					html+='<th>#</th>';
					html+='<th>操作人</th>';
					html+='<th>时间</th>';
					html+='<th>日志描述</th>';
					html+='<th style="width:36px;"></th>';
					html+='</tr>';
					html+='</thead>';
					html+='<tbody>';
					var maxPage=data.maxPage;
					var rows=data.rows;
					if(rows){
						$.each(rows,function(i,log){
							html+='<tr>';
							html+='<td>'+(i+1)+'</td>';
							html+='<td>'+log.operator+'</td>';
							html+='<td>'+log.logTime+'</td>';
			                html+='<td>'+log.msg+'</td>';
			                html+='<td>';
			                html+='<a href="<s:url value="/log/delete/'+log.logId+'" />"><i class="icon-remove"></i></a>';
			                html+='</td>';
			                html+='</tr>';
						});
					}
		            html+='</tbody>';
		            html+='</table>';
		            $('#logTable').html(html);
		            createLogPagerBar(maxPage);
		    		$('.icon-remove').parent().click(function(e){
		    			var a=$(this);
		    			$.get(a.attr('href'),function(data){
		    				if(data==='success'){
		    					a.parent('td').parent('tr').remove();
		    				}
		    			});
		    			return false;
		    		});
	        	}).fail(function(jqXHR, textStatus, errorThrown){
	        		alert("出错了"+textStatus+","+errorThrown);
	        	});
	        }
		});
	</script>
</body>
</html>