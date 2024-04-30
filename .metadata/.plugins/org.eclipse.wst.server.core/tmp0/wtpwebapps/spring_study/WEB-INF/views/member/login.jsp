<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h1>Member Login Page</h1>

	<form action="/member/login" method="post">

		<div class="mb-3">
			<label for="i" class="form-label">ID</label> <input type="text"
				class="form-control" id="i" placeholder="ID입력..." name="id">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">PW</label> <input type="password"
				class="form-control" id="p" placeholder="Password 입력..." name="pw">
		</div>
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
</div>

<jsp:include page="../layout/footer.jsp" />
