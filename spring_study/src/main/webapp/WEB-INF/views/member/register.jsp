<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <jsp:include page="../layout/header.jsp" />

<div  class="container-md">
<h1>Member Register Page</h1>

<form action="/member/register" method="post">

<div class="mb-3">
  <label for="i" class="form-label">ID</label>
  <input type="text" class="form-control" id="i" placeholder="ID입력..." name="id">
</div>
<div class="mb-3">
  <label for="p" class="form-label">PW</label>
  <input type="password" class="form-control" id="p" placeholder="Password 입력..." name="pw">
</div>
<div class="mb-3">
  <label for="n" class="form-label">이름</label>
  <input type="text" class="form-control" id="n" placeholder="이름 입력..." name="name">
</div>
<div class="mb-3">
  <label for="e" class="form-label">E-Mail</label>
  <input type="email" class="form-control" id="e" placeholder="E-mail입력..." name="email">
</div>
<div class="mb-3">
  <label for="h" class="form-label">Home</label>
  <input type="text" class="form-control" id="h" placeholder="주소 입력..." name="home">
</div>
<div class="mb-3">
  <label for="a" class="form-label">Age</label>
  <input type="text" class="form-control" id="a" placeholder="나이 입력..." name="age">
</div>

<button type="submit" class="btn btn-primary">회원가입</button>

</form>

</div>

<jsp:include page="../layout/footer.jsp" />