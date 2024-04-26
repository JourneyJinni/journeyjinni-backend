<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "jakarta.tags.core"%>

<%@ page import = "com.ssafy.mvc.model.MemberDto"%>

 <!-- 상단 navbar start -->
    <nav class="navbar navbar-expand-sm shadow bg-light navbar-light fixed-top">
      <div class="container-fluid">
        <a class="navbar-brand text-primary fw-bold" href="${root}/index.jsp">
          <img src="${root}/assets/img/ssafy_logo.png" 
          alt="Logo" 
          width="60" 
          class="d-inline-block">
          Enjoy Trip
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
        

          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="#">공지사항</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${root}/index.jsp">홈</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${root}/view/tour/attraction.jsp">지도</a>
            </li>
          </ul>

        <c:choose>
        <c:when test="${empty userInfo}">
          <ul class="navbar-nav ms-auto me-2" id="logout-nav">
            <li class="nav-item">
              <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#signupModal">회원가입</a>
            </li>
          </ul>
         </c:when>
         <c:otherwise>
          <ul class="navbar-nav ms-auto me-2" id="login-nav">
          	<li class="nav-item">
              <a class="nav-link" href="#">${userInfo.user_name}님 환영합니다.</a>
              
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" id="logout">로그아웃</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">마이페이지</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">내 정보</a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#" id="myInfo">확인/수정</a></li>
                <li><a class="dropdown-item text-danger" href="#" id="del_user">회원 탈퇴</a></li>
              </ul>
            </li>
          </ul>
          </c:otherwise>
          </c:choose>
        </div>
      </div>
    </nav>
    <script>
    document.querySelector("#logout").addEventListener("click",function(){
    	if (confirm("로그아웃 하시겠습니까?")) {
            window.location.href='${root}/member?service=logout';
        }
  	});
  	document.querySelector("#del_user").addEventListener("click",function(){
    	if (confirm("정말로 회원탈퇴 하시겠습니까?")) {
            window.location.href='${root}/member?service=deluser&user_id=${userInfo.user_id}';
        }
  	});
  	document.querySelector("#myInfo").addEventListener("click",function(){
    	window.location.href='${root}/member?service=infouser&user_id=${userInfo.user_id}';
    });
    </script>
    <!-- 상단 navbar end -->