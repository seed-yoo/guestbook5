<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<form action="/guestbook5/gbc/write2" method="get">
		<table border="1" width="540px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="72" rows="5" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="4">
					<button type="submit">등록</button>
				</td>
			</tr>
		</table>
	</form>
	<br>

	<c:forEach items="${ requestScope.pList }" var="personVo" varStatus="status">
		<table border="1" width="540px">
			<tr>
				<td>${ personVo.no }</td>
				<td>${ personVo.name }</td>
				<td>${ personVo.reg_date }</td>
				<td><a href="/guestbook5/gbc/deleteform?no=${ personVo.no }">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4">${ personVo.content }</td>
			</tr>
		</table>
		<br>
	</c:forEach>



	<br>
</body>
</html>