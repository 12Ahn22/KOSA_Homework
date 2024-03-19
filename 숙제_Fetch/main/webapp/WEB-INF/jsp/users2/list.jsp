<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Information</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .button-container {
            margin-top: 20px;
        }
        .button-container button {
            padding: 10px 20px;
            font-size: 16px; 
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
	<h1>회원목록</h1>
	  
    <form id="searchForm" action="list.jsp" method="get" >
    	<label>이름 : </label>
    	<input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    	<input type="submit" value="검색">
    </form>
    
    <form id="listForm" action="user.do" method="post">
    	<input type="hidden" id="action" name="action" value="view">
    	<input type="hidden" id="userid" name="userid" >
    </form>
<!--     
    <form id="listForm2" action="users" method="get">
    	<input type="hidden" id="action" name="action" value="view">
    	<input type="hidden" id="userid" name="userid" >
    </form>
    <form id="listForm3" action="users?action=view" method="get">
    	<input type="hidden" id="userid" name="userid" >
    </form>
    <form id="listForm3" action="users?action=view" method="post">
    	<input type="hidden" id="userid" name="userid" >
    </form>
 -->    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>이름</th>
            <th>나이</th>
            <th>이메일</th>
        </tr>
        <c:forEach var="user" items="${list}">
        <tr>
            <td onclick="jsView('${user.userid}')"  style="cursor:pointer;">${user.userid}</td>
            <td><a href="user.do?action=view&userid=${user.userid}">${user.username}</a></td>
            <td>${user.userage}</td>
            <td>${user.useremail}</td>
        </tr>
        </c:forEach>
    </table>
<script>
function jsView(uid) {
	//인자의 값을 설정한다 
	userid.value = uid;
	
	//양식을 통해서 서버의 URL로 값을 전달한다
	listForm.submit();
	
}
</script>      
    <div class="button-container">
        <a href="user.do?action=insertForm">등록</a>
    </div>
</body>
</html>

