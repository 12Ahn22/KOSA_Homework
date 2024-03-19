<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User insert form</title>
    <style>
        label {
            display: inline-block;
            width: 200px;
        }
        input {
            margin-bottom: 10px; 
        }
    </style>
</head>
<body>
    <h1>
        삭제 결과 화면
    </h1>
	<c:if test="${updated != 0}">
		삭제되었습니다 
	</c:if>   
	<a href="user.do?action=list">목록</a>

	<form action="user.do" method="post">
		<input type="hidden" name="action" value="list">
		<input type="submit" value="목록">
	</form>     
	
</body>
</html>

