<%@page import="com.eomcs.lms.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<a href='/board/add'>새 글</a><br>
<table class='table table-hover'>
<tr>
	<th>번호</th>
	<th>내용</th>
	<th>등록일</th>
	<th>조회수</th>
</tr>
<%
List<Board> boards = (List<Board>) request.getAttribute("boards");
	for (Board board : boards) {%>
	<tr>
	   <td><%=board.getNo()%></td>
	   <td><a href='/board/detail?no=<%=board.getNo()%>'>
	   <%=board.getContents()%></a></td>
	   <td><%=board.getCreatedDate()%></td>
	   <td><%=board.getViewCount()%></td></tr>
<%}%>
<tr><td>43</td><td><a href='/board/detail?no=43'>위대한 어버이 수령동지를 위해</a></td><td>2019-09-18</td><td>7</td></tr>
</table>
</div>
<jsp:include page="/jsp/footer.jsp"/>
</body></html>
