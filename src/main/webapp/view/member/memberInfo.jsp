<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "jakarta.tags.core"%>

<c:set var="memberInfo" value="${memberInfo }" ></c:set>    

<jsp:include page="/common/head.jsp" />
<body>
<jsp:include page="/common/nav.jsp" />
<jsp:include page="/common/modal.jsp" />


<div style="height:80px"></div>
<div class="container">
	<form action="${applicationScope.root }/member?service=modifyuser" method="POST" id="action_modify">
		<div class="row mb-3 mt-3">
	      <div class="col-md-3">
	        <label for="info-id" class="form-label">아이디 : </label>
	      </div>
	      <div class="col-md-9">
	        <input type="text" class="form-control bg-secondary" id="info-id" name="info-id" value="${memberInfo.user_id }" readonly/>
	      </div>
	    </div>
	    <div class="row mb-3 mt-3">
	      <div class="col-md-3">
	        <label for="info-pw" class="form-label">비밀번호 : </label>
	      </div>
	      <div class="col-md-9">
	        <input type="password" class="form-control bg-secondary" id="info-pw" name="info-pw" readonly/>
	      </div>
	    </div>
	    <div class="row mb-3 mt-3">
	      <div class="col-md-3">
	        <label for="info-name" class="form-label">이름 : </label>
	      </div>
	      <div class="col-md-5">
	        <input type="text" class="form-control bg-secondary" id="info-name" name="info-name" value="${memberInfo.user_name }" readonly/>
	      </div>
	    </div>
	    <div class="row mb-3 mt-3">
	      <div class="col-md-3">
	        <label for="info-email" class="form-label">이메일 : </label>
	      </div>
	      <div class="col-md-3">
	        <input type="text" class="form-control bg-secondary" id="info-email_id" name="info-email_id" value="${memberInfo.email_id }" readonly/>
	      </div>
	      @
	      <div class="col-md-3">
	        <input type="text" class="form-control bg-secondary" id="info-email_domain" name="info-email_domain" value="${memberInfo.email_domain }" readonly/>
	      </div>
	    </div>
	    <div class="row mb-3 mt-3">
	      <div class="col-md-3">
	        <label for="info-name" class="form-label">가입일자 : </label>
	      </div>
	      <div class="col-md-5">
	       ${memberInfo.join_date }
	      </div>
	    </div>
    </form>
    <div>
	   	<button type="button" id="btn-confirm" class="btn btn-danger btn-sm" style="display: none;">
	    수정완료
	    </button>
	    <button type="button" id="btn-update" class="btn btn-primary btn-sm" >
	    수정하기
	    </button>
	    <button type="button" id="btn-close" class="btn btn-outline-danger btn-sm" data-bs-dismiss="modal">
	    Close
	   	</button>
   	</div>
</div>
<jsp:include page="/common/footer.jsp" />
</body>
<script>	
    document.querySelector("#btn-update").addEventListener("click",function(){
    	document.getElementById("info-pw").readOnly = false;
    	document.getElementById("info-pw").setAttribute("class", "form-control");
    	
    	document.getElementById("info-name").readOnly = false;
    	document.getElementById("info-name").setAttribute("class", "form-control");
    	
    	document.getElementById("info-email_id").readOnly = false;
    	document.getElementById("info-email_id").setAttribute("class", "form-control");
    	
    	document.getElementById("info-email_domain").readOnly = false;
    	document.getElementById("info-email_domain").setAttribute("class", "form-control");

    	document.getElementById("btn-update").setAttribute("style", "display: none;");
    	document.getElementById("btn-confirm").setAttribute("style", "display: block;");
  	});
    
    document.querySelector("#btn-confirm").addEventListener("click",function(){
    	if(confirm("정보를 수정하시겠습니까? \n수정된 뒤에는 자동으로 로그아웃됩니다.")) document.getElementById('action_modify').submit();
    });
    
    document.querySelector("#btn-close").addEventListener("click",function(){
    	window.location.href='${root}/member?service=home';
    });
   </script>
</html>