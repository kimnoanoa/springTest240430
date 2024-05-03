<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header.jsp</title>
<link href="/re/css/bootstrap.min.css" rel="stylesheet" />
<script src="/re/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">NOA</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        <%-- <c:if test="${ses ne null }"> --%>
        <li class="nav-item">
          <a class="nav-link" href="/board/register">게시판 글쓰기</a>
        </li>
        <%-- </c:if> --%>
        <li class="nav-item">
          <a class="nav-link" href="/board/list">게시판 보기</a>
        </li>
        <%-- <c:if test="${ses eq null }">
        <li class="nav-item">
          <a class="nav-link" href="/member/register">회원가입</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/login">로그인</a>
        </li>
        </c:if>
        <c:if test="${ses ne null }">
        <li class="nav-item">
          <a class="nav-link" href="/member/logout">로그아웃</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/modify">${ses.id }(${ses.email }) Welcome!!</a>
        </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link" href="http://www.google.com" target="_blank" >Link</a>
        </li> --%>
      </ul>
    </div>
  </div>
</nav>

