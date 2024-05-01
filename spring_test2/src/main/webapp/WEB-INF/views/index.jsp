<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="layout/header.jsp"></jsp:include>

<br>

<div class="container-md">
<h1>
	My first Spring Project  
</h1>

<%-- 
<c:if test="${ses.id != null }">
<p>${ses.id }님이 로그인하셨습니다.</p>
<span class="badge rounded-pill text-bg-info">${ses.last_login }</span>
</c:if> --%>

</div>

<br>

<jsp:include page="layout/footer.jsp"></jsp:include>






