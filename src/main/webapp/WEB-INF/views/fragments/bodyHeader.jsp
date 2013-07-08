<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Header 主体 -->
  
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="brand" href="#">WEB4J</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="divider-vertical"></li>
                  	<li><a href='<s:url value="/home" />'><i class="icon-home icon-white"></i> 主页</a></li>
					<li class="divider-vertical"></li>
					<c:forEach var="module" items="${navMenus}">
						<li><a
							href='<s:url value="/navModule?moduleCode=${module.moduleCode }" />'>
								<c:if test="${not empty module.moduleIcon }">
									<i class="${module.moduleIcon } icon-white"></i>
								</c:if> ${module.moduleDesc }
						</a></li>
						<li class="divider-vertical"></li>
					</c:forEach>
				</ul>
				<div class="pull-right">
					<ul class="nav pull-right">
					    <li class="dropdown">
					    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Welcome, ${loginUser.userName } <b class="caret"></b></a>
	                        <ul class="dropdown-menu">
	                            <li><a href='<s:url value="/user/modifyPwd" />'><i class="icon-edit"></i> 修改密码</a></li>
	                            <li class="divider"></li>
	                            <li><a href='<s:url value="/quit"/>'><i class="icon-off"></i> 退出</a></li>
	                        </ul>
                    	</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
	
