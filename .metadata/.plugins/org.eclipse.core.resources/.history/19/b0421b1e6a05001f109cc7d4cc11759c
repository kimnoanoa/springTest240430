<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <jsp:include page="../layout/header.jsp" />

<div  class="container-md">
<h1>Member Modify Page</h1>

<form action="/member/modify" method="post">

<div class="mb-3">
  <label for="i" class="form-label">ID</label>
  <input type="text" class="form-control" id="i" placeholder="ID입력..." name="id" value="${ses.id }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="r" class="form-label">reg_date</label>
  <input type="text" class="form-control" id="r" placeholder="ID입력..." name="" value="${ses.reg_date }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="l" class="form-label">last_login</label>
  <input type="text" class="form-control" id="l" placeholder="ID입력..." name="" value="${ses.last_login }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="p" class="form-label">PW</label>
  <input type="password" class="form-control" id="p" placeholder="Password 입력..." name="pw">
</div>
<div class="mb-3">
  <label for="n" class="form-label">이름</label>
  <input type="text" class="form-control" id="n" placeholder="이름 입력..." name="name" value="${ses.name }">
</div>
<div class="mb-3">
  <label for="e" class="form-label">E-Mail</label>
  <input type="email" class="form-control" id="e" placeholder="E-mail입력..." name="email" value="${ses.email }">
</div>
<div class="mb-3">
  <label for="h" class="form-label">Home</label>
  <input type="text" class="form-control" id="h" placeholder="주소 입력..." name="home" value="${ses.home }">
</div>
<div class="mb-3">
  <label for="a" class="form-label">Age</label>
  <input type="text" class="form-control" id="a" placeholder="나이 입력..." name="age" value="${ses.age }">
</div>

<button type="submit" class="btn btn-primary">수정</button>
<a href="/member/remove"> <button type="button" class="btn btn-danger" onclick="removePopup()">회원탈퇴</button> </a>
</form>

</div>

<jsp:include page="../layout/footer.jsp" />


<script type="text/javascript">

function removePopup() {
	
	if(confirm("삭제를 정말로 하시겠습니까?")){	
		
	}else{
		event.preventDefault();
	}
	
}

</script>










