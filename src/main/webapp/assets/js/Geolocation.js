function sendLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            // AJAX를 사용하여 서버에 위치 정보를 전송
            var xhr = new XMLHttpRequest();
            xhr.open("POST", '/EnjoyTrip_Algorithm/attraction-controller?action=nowLocation', true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    // 서버로부터의 응답 처리 (예: 추가적인 동작이 필요한 경우)
                    console.log(xhr.responseText);
                }
            };
            xhr.send("latitude=" + latitude + "&longitude=" + longitude);
        });
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}

function sendLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            // AJAX를 사용하여 서버에 위치 정보를 전송
            var xhr = new XMLHttpRequest();
            xhr.open("POST", '/EnjoyTrip_Algorithm/attraction-controller?action=nowLocation', true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    // 서버로부터의 응답 처리 (예: 추가적인 동작이 필요한 경우)
                    console.log(xhr.responseText);
                }
            };
            xhr.send("latitude=" + latitude + "&longitude=" + longitude);
        });
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}
