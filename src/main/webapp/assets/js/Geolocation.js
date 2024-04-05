function sendLocation() {
    if (navigator.geolocation) {
        var options = {
            enableHighAccuracy: true,
            timeout: 5000, // 최대 시간 (밀리초)
            maximumAge: 0  // 캐시된 위치 정보 최대 유지 시간 (밀리초)
        };
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
            console.log(position)
            xhr.send("latitude=" + latitude + "&longitude=" + longitude);
            console.log("latitude=" + latitude + "&longitude=" + longitude);
        }, function(error) {
            // 에러 처리
            console.error("Error getting geolocation:", error);
        }, options);
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}
