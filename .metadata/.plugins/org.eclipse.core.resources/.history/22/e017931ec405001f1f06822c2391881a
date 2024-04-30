<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<jsp:include page="layout/header.jsp"></jsp:include>

<br>

<div class="container-md">
<h1>
	My first Spring Project  
</h1>


<c:if test="${ses.id != null }">
<p>${ses.id }님이 로그인하셨습니다.</p>
<span class="badge rounded-pill text-bg-info">${ses.last_login }</span>
</c:if>

</div>

<br>

<jsp:include page="layout/footer.jsp"></jsp:include>



<script type="text/javascript">
	const msg_login = `<c:out value="${msg_login}"/>`;
	const msg_logout = `<c:out value="${msg_logout}"/>`;
	const msg_remove = `<c:out value="${msg_remove}"/>`;
	
	if(msg_login == 'fail'){
		alert('로그인 실패!!');
	}
	
	if(msg_logout == 'ok'){
		alert('로그아웃 되었습니다.');
	}
	
	if(msg_remove == '1'){
		alert('회원 탈퇴가 정상적으로 완료되었습니다.');
	}

	
</script>



