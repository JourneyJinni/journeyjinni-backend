let user_list = [];
if (localStorage.getItem("user_list")) {
    user_list = localStorage.getItem("user_list");
}

window.onload = function () {
    
}


function getCurrentDate() {
    let date = new Date();
    return date.toISOString().substring(0, 10);
}

function dateFormat(date) {
    var yymmdd = date.split("-");
    return yymmdd[0].substr(2, 2) + "." + yymmdd[1] + "." + yymmdd[2];
}

function login() {
    let user_id = document.getElementById("user-id").value;
    let user_pw = document.getElementById("user-pw").value;

    if (!user_id) {
        alert("id를 확인하세요");
        return;
    }

    if (!user_pw) {
        alert("비밀번호를 확인하세요");
        return;
    }

    let used_list = [];
    if (user_list.length > 0) {
        for (let i = 0; i < JSON.parse(user_list).length; i++) {
            used_list.push(JSON.parse(user_list)[i]);
        }
    } else {
        alert("아이디와 비밀번호를 확인하세요");
        return;
    }

    for (let i = 0; i < JSON.parse(user_list).length; i++) {
        if (user_id == used_list[i].info_id && user_pw == used_list[i].info_pw) {
            let user = {
                id_user: used_list[i].info_id,
                pw_user: used_list[i].info_pw,
                name_user: used_list[i].info_name,
                email_user: used_list[i].info_email,
                phone_user: used_list[i].info_phone
            };

            let localString = JSON.stringify(user);
            localStorage.setItem("user", localString);
            
            window.document.location.reload();
            return;
        }
    }
    alert("아이디와 비밀번호를 확인하세요");
    return;
}

let sub_id = document.getElementById("sign_user-id");
let sub_pw = document.getElementById("sign_user-pw");
let sub_repw = document.getElementById("sign_user-repw");
let pw_text = document.getElementById("sign_pw-text");

sub_repw.addEventListener("keyup", function () {
    let pwText = ["사용가능한 비밀번호입니다", "비밀번호가 일치하지 않습니다"];
    let pwColor = ["green", "red"];

    if (sub_repw.value.trim().length > 0) {
        if (sub_pw.value == sub_repw.value) {
            pw_text.innerText = pwText[0];
            pw_text.style.color = pwColor[0];
        } else {
            pw_text.innerText = pwText[1];
            pw_text.style.color = pwColor[1];
        }
    } else {
        pw_text.innerText = "";
    }
})

function signup() {
    if (sub_id.value.length == 0) {
        alert("아이디를 입력하세요.");
        sub_id.focus();
        return;
    }
    if (sub_pw.value.length == 0) {
        alert("비밀번호를 입력하세요.");
        sub_pw.focus();
        return;
    }
    if (sub_repw.value.length == 0 || sub_repw.value != sub_pw.value) {
        alert("비밀번호가 일치하지 않습니다.");
        sub_repw.focus();
        return;
    }
    if (sub_id.readOnly == false) {
       alert("아이디의 중복을 확인하세요.")
       return;
    }
	if(confirm("회원가입 하시겠습니까?")) document.getElementById('action_signup').submit();
}

function checkid() {
    console.log("중복확인");
    if (sub_id.value.length == 0) {
        alert("아이디를 입력하세요.");
        return;
    }

    if (user_list.length > 0) {
        let used_list = [];
        for (let i = 0; i < JSON.parse(user_list).length; i++) {
            used_list.push(JSON.parse(user_list)[i]);
        }
        for (let i = 0; i < used_list.length; i++) {
            if (sub_id.value == used_list[i].info_id) {
                alert("중복된 id입니다.");
                return;
            }
        }
    }
    if (window.confirm("사용 가능한 id입니다. 사용하시겠습니까?")) {
        sub_id.readOnly = 'true';
        sub_id.setAttribute("class", "form-control bg-secondary");
        return;
    }
}
