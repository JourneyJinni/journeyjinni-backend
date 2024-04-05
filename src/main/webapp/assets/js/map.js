window.onload = function () {
    console.log("맵 페이지 오픈");
    let poll = localStorage.getItem("poll");
    let user = localStorage.getItem("user");
    var pollDiv = document.getElementById("vote");

    let user_img = document.getElementById("user_thumb");
    let login_nav = document.getElementById("login-nav");
    let logout_nav = document.getElementById("logout-nav");
    if (user) {
        login_nav.setAttribute("class", "navbar-nav ms-auto me-2");
        logout_nav.setAttribute("class", "navbar-nav ms-auto me-2 d-none");
    } else {
        login_nav.setAttribute("class", "navbar-nav ms-auto me-2 d-none");
        logout_nav.setAttribute("class", "navbar-nav ms-auto me-2");
    }
}

function addAnswer() {
    var listDiv = document.querySelector("#poll-answer-list");

    var divEl = document.createElement("div");
    divEl.setAttribute("class", "row mb-1 poll-answer-list-item");
    
    var divE2 = document.createElement("div");
    divE2.setAttribute("class", "col-md-10");

    var divE3 = document.createElement("div");
    divE3.setAttribute("class", "col-md-2");

    var inputEl = document.createElement("input");
    inputEl.setAttribute("type", "text");
    inputEl.setAttribute("class", "form-control");
    inputEl.setAttribute("name", "answer");

    var buttonEl = document.createElement("button");
    buttonEl.setAttribute("type", "button");
    buttonEl.setAttribute("class", "btn btn-outline-danger btn-sm");

    buttonEl.addEventListener("click", function (e) {
        var parent = this.parentNode.parentNode;
        listDiv.removeChild(parent);
    });
    buttonEl.appendChild(document.createTextNode("삭제"));

    divE2.appendChild(inputEl);
    divE3.appendChild(buttonEl);
    divEl.appendChild(divE2);
    divEl.appendChild(divE3);
    listDiv.appendChild(divEl);
}


function makePoll() {
    let s_date = document.getElementById("start_date").value;
    let e_date = document.getElementById("end_date").value;

    if (s_date.length == 0 || e_date.length == 0 || s_date > e_date) {
        alert("날짜를 다시 입력하세요");
        return;
    }

    if (!document.getElementById("question").value) {
        alert("질문 내용을 입력하세요");
        return;
    }

    var answers = document.getElementsByName("answer");
    for (var i = 0; i < answers.length; i++) {
        if (!answers[i].value) {
        alert("답변 항목 입력하세요");
        return;
        }
    }

    let answersArray = [];
    for (let i = 0; i < answers.length; i++){
        answersArray.push(answers[i].value);
    }

    let poll = {
        start_date: s_date,
        end_date: e_date,
        question: document.querySelector("#question").value,
        answers: answersArray,
    };

    let localString = JSON.stringify(poll);
    localStorage.setItem("poll", localString);

    alert("투표를 생성합니다.");

    window.document.location.reload();
}

function getCurrentDate() {
    let date = new Date();
    return date.toISOString().substring(0, 10);
}

function dateFormat(date) {
    var yymmdd = date.split("-");
    return yymmdd[0].substr(2, 2) + "." + yymmdd[1] + "." + yymmdd[2];
}

function poll() {
    console.log("vote");
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

    if (user_id == 'test' && user_pw == 'test') {
        let user = {
            id_user: user_id,
            pw_user: user_pw
        };

        let localString = JSON.stringify(user);
        localStorage.setItem("user", localString);
        
        window.document.location.reload();
    } else {
        alert("id, 비밀번호가 틀렸습니다.");
        return;
    }
}

function logout() {
    if (confirm("로그아웃 하시겠습니까?")) {
        localStorage.removeItem("user");
        window.document.location.reload();
    }
}