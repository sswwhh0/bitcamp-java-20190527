<%@page import="com.eomcs.lms.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
  <title>게시물 목록</title>
  <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'><link rel='stylesheet' href='/css/common.css'>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div id='content'>
<h1>게시물 목록</h1>
<a href='add'>새 글</a><br>
<table class='table table-hover'>
<tr>
	<th>번호</th>
	<th>내용</th>
	<th>등록일</th>
	<th>조회수</th>
</tr>
	<c:forEach items="${boards}" var="board">
	<tr>
	   <td>${board.no}</td>
	   <td><a href='detail?no=${board.no}'>
	   ${board.contents}</a></td>
	   <td>${board.createdDate}</td>
	   <td>${board.viewCount}</td></tr>
	</c:forEach>
</table>
</div>
<jsp:include page="/jsp/footer.jsp"/>
</body></html>
