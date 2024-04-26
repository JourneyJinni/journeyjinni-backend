<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div
  class="modal fade"
  id="loginModal"
  data-bs-backdrop="static"
  data-bs-keyboard="false"
  tabindex="-1"
  aria-labelledby="staticBackdropLabel"
  aria-hidden="true"
>
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <div class="modal-header">
        <h4 class="modal-title">
          <i class="bi bi-chat-left-dots-fill text-info"> 로그인</i>
        </h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <div class="modal-body">
        <form action="login" method="POST" id="action_login">
          <div class="row mb-3 mt-3">
            <div class="col-md-3">
              <label for="user_id" class="form-label">아이디 : </label>
            </div>
            <div class="col-md-9">
              <input type="text" class="form-control" id="user_id" name="user_id" required="required"/>
            </div>
          </div>
          <div class="row mb-3 mt-3">
            <div class="col-md-3">
              <label for="user_pw" class="form-label">비밀번호 : </label>
            </div>
            <div class="col-md-9">
              <input type="password" class="form-control" id="user_pw" name="user_pw" required="required"/>
            </div>
          </div>
        </form>
      </div>

      <div class="modal-footer">
        <button type="submit" form="action_login" id="btn-login" class="btn btn-primary btn-sm">
          로그인
        </button>
        <button type="button" class="btn btn-outline-danger btn-sm" data-bs-dismiss="modal">
          Close
        </button>
      </div>
    </div>
  </div>
</div>
<!-- 로그인 modal end -->
<!-- 회원가입 modal start -->
<div
  class="modal fade"
  id="signupModal"
  data-bs-backdrop="static"
  data-bs-keyboard="false"
  tabindex="-1"
  aria-labelledby="staticBackdropLabel"
  aria-hidden="true"
>
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <div class="modal-header">
        <h4 class="modal-title">
          <i class="bi bi-chat-left-dots-fill text-info"> 회원가입</i>
        </h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <div class="modal-body">
        <form action="/join" method="POST" id="action_signup">
          <div class="row mb-3 mt-3">
            <div class="col-md-3">
              <label for="user-id" class="form-label">아이디 : </label>
            </div>
            <div class="col-md-5">
              <input type="text" class="form-control" id="sign_user-id" name="user_id"/>
            </div>
            <div class="col-md-4">
              <button type="button" class="btn btn-outline-danger btn-sm" id="id-check">
                중복확인
              </button>
            </div>
          </div>
          <div class="row mb-3 mt-3">
            <div class="col-md-3">
              <label for="user-pw" class="form-label">비밀번호 : </label>
            </div>
            <div class="col-md-5">
              <input type="password" class="form-control" id="sign_user-pw" name="user_password" />
            </div>
            <div class="col-md-4">
              <label for="user-pw" class="form-label" style="display:none;"> 임시 </label>
            </div>
          </div>
          <div class="row mb-3 mt-3">
            <div class="col-md-3">
              <label for="user-pw" class="form-label">비밀번호 확인 : </label>
            </div>
            <div class="col-md-5">
              <input type="password" class="form-control" id="sign_user-repw" />
            </div>
            <div class="col-md-4">
              <label for="user-pw" class="form-label" id="sign_pw-text"></label>
            </div>
          </div>
          <div class="row mb-3 mt-3">
            <div class="col-md-3">
              <label for="user-name" class="form-label">이름 : </label>
            </div>
            <div class="col-md-5">
              <input type="text" class="form-control" id="sign_user-name" name="user_name"/>
            </div>
          </div>
          <div class="row mb-3 mt-3">
            <div class="col-md-3">
              <label for="user-email" class="form-label">이메일 : </label>
            </div>
            <div class="col-md-3">
              <input type="text" class="form-control" id="sign_user-email" name="email_id"/>
            </div>
            @
            <div class="col-md-3">
              <input type="text" class="form-control" id="sign_user-domain" name="email_domain"/>
            </div>
          </div>
        </form>
      </div>

      <div class="modal-footer">
        <button type="submit" form="action_signup" id="btn-signup" class="btn btn-primary btn-sm" ><!--  onclick="javascript:signup()" -->
          회원가입
        </button>
        <button type="button" id="btn-signup_close" class="btn btn-outline-danger btn-sm" data-bs-dismiss="modal">
          Close
        </button>
      </div>
    </div>
  </div>
</div>
<!-- 회원가입 modal end -->
<script>
 	let signId = document.querySelector("#sign_user-id");
	
	document.querySelector("#id-check").addEventListener("click",function(){
		if (document.querySelector("#sign_user-id").value.length == 0) {
	        alert("아이디를 입력하세요.");
	        console.log("script");
	        return;
	    }
		 fetch("/idcheck/" + signId.value)
	   		.then(response => response.text())
	   		.then(data => {
	   			console.log(data);
		 		if(data == 0) {
		 			if (window.confirm("사용 가능한 id입니다. 사용하시겠습니까?")) {
						signId.readOnly = true;
						signId.setAttribute("class", "form-control bg-secondary");
				        return;
				   	}
		 		} else {
		 			alert("이미 사용중인 ID 입니다.");
			    	return;
		 		}
  		   });

	});
	 
	document.querySelector("#btn-signup_close").addEventListener("click",function(){
		document.querySelector("#action_signup").reset();
		signId.readOnly = false;
		signId.setAttribute("class", "form-control");
	});
</script>