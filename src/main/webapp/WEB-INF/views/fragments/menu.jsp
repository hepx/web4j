<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!-- 左则菜单 -->
<div class="span3 bs-docs-sidebar">
	<ul class="nav nav-list bs-docs-sidenav">
		<c:forEach var="menu" items="${leftMenus }">
			<li><a href='<s:url value="${menu.moduleUrl }" />'><i class="icon-chevron-right"></i>${menu.moduleDesc }</a></li>
		</c:forEach>
    </ul>
</div>


