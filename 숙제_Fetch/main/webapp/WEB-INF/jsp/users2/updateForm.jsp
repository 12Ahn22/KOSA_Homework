<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <title>수정화면</title>
      <style>
        label {
          display: inline-block;
          width: 120px;
        }

        input {
          margin-bottom: 10px;
        }
      </style>
    </head>

    <body>
      <h1>
        회원정보 수정양식
      </h1>
      <form action="user.do" method="post" id="uForm">
        <input type="hidden" name="action" value="update">
        <label>아이디 : </label> <input type="text" id="userid" name="userid" value="${user.userid}" readonly="readonly">
        <br />
        <label>비밀번호 : </label> <input type="password" id="userpassword" name="userpassword" required="required"><br />
        <label>비밀번호확인 : </label> <input type="password" id="userpassword2" name="userpassword2"
          required="required"><br />
        <label>이름 : </label> <input type="text" id="username" name="username" value="${user.username}"><br />
        <label>나이: </label> <input type="number" id="userage" name="userage" value="${user.userage}"><br />
        <label>이메일: </label> <input type="email" id="useremail" name="useremail" value="${user.useremail}"><br />
        <div>
          <input type="submit" value="수정">
          <a href="user.do?action=view&userid=${user.userid}">취소</a>
        </div>
      </form>

      <script>
        const uForm = document.getElementById("uForm");

        const userpassword = document.getElementById("userpassword");
        const userpassword2 = document.getElementById("userpassword2");
        const username = document.getElementById("username");
        const userage = document.getElementById("userage");
        const useremail = document.getElementById("useremail");
        const userid = document.getElementById("userid");

        uForm.addEventListener("submit", (e) => {
          e.preventDefault();

          const param = {
            userpassword: userpassword.value,
            username: username.value,
            userage: userage.value,
            useremail: useremail.value,
            userid: userid.value,
            action: "update"
          }

          // 유효성 체크
          if(!userid.value){
            alert("유효한 접근이 아닙니다.");
            location = "user.do?action=list";
            return;
          }

          if(!username.value){
            alert("이름을 입력해주세요");
            username.focus();
            return;
          }

          if(!userage.value){
            alert("나이를 입력해주세요");
            userage.focus();
            return;
          }

          if(!useremail.value){
            alert("이메일을 입력해주세요");
            useremail.focus();
            return;
          }

          if (userpassword.value !== userpassword2.value) {
            alert("비밀 번호가 잘못 되었습니다.");
            userpassword2.value = "";
            userpassword2.focus();
            return;
          }

          if (userpassword.value.length < 12) {
            alert("비밀 번호 길이는 12자 이상이어야합니다.");
            return;
          }

          fetch("user.do", {
            method: "POST",
            body: JSON.stringify(param),
            headers: { "Content-type": "application/json; charset=utf-8" }
          }).then(res => res.json()).then(json => {
            //서버로 부터 받은 결과를 사용해서 처리 루틴 구현  
            console.log("json ", json);

            if (json.status === 0) {
              // 성공
              alert("회원 수정 성공: json.userid" + json.userid);
              // 페이지 이동
              location = "user.do?action=view&userid=" + json.userid; // 왜 백틱안될까.
            } else {
              alert(json.statusMessage);
            }
          });

        });
      </script>
    </body>

    </html>