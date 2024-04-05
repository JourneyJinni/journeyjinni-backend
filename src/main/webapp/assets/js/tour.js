const baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
const serviceKey = "npBxlz1Yaj3%2B94u3RHvcZcemCFO2D%2Bo2m%2Bg2BlxQ9mnQKLJReV%2BJbMHiitye0oPlAW8H%2FhzROGfYD8lryf969Q%3D%3D";

function saveFormData() {

    // 결과를 표시할 요소 가져오기
    const resultContainer = document.getElementById('result-container');

    // 이전에 표시된 내용 지우기
    resultContainer.innerHTML = '';

    // 선택된 지역들을 저장할 배열
    var selectedRegions = [];
    // 선택된 지역 체크박스들을 가져오기
    var regionCheckboxes = document.querySelectorAll('input[name="city"]:checked');
    // 각 체크된 지역을 배열에 추가
    regionCheckboxes.forEach(function(checkbox) {
      selectedRegions.push(checkbox.value);
    });

    var selectedCategories = [];
    var categoryCheckboxes = document.querySelectorAll('input[name="category"]:checked');
    categoryCheckboxes.forEach(function(checkbox) {
      selectedCategories.push(checkbox.value);
    });

    var queryParams = `?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=appTest&_type=JSON`;

    if (selectedCategories.length > 0) {
        queryParams += "&contentTypeId=" + selectedCategories.join(',');
    }
    if (selectedRegions.length > 0) {
        queryParams += "&areaCode=" + selectedRegions.join(',');
    }

    // 완성된 URL
    const url = baseUrl + queryParams;

    const results = []; // 결과를 저장할 배열

    fetch(url)
    .then(response => response.json())
        .then(data => {

        // 데이터 처리
        data.response.body.items.item.forEach(item => {
            const { title, addr1, firstimage, contentId, mapx, mapy } = item;

            // 결과를 HTML에 추가
            const resultDiv = document.createElement('div');
            resultDiv.classList.add('result-item');

            resultDiv.innerHTML = `
            <p><b>${title}</b></p>
            <p>주소: ${addr1}</p>
            <button class="show-detail btn btn-primary" onclick="showDetail(this)" 
                    data-title="${title}" 
                    data-address="${addr1}" 
                    data-mapx="${mapx}" 
                    data-mapy="${mapy}" 
                    data-image="${firstimage}">
                상세 정보 보기
            </button>
            <br><br>
        `;

            resultContainer.appendChild(resultDiv);

            const resultObj = {
                title: title,
                address: addr1,
                contentId: contentId,
                mapX: mapx,
                mapY: mapy,
                url : url
            };

            // 결과 배열에 추가
            results.push(resultObj);
        });
    });
    


    return false;
}


function showDetail(button) {

    const detailContainer = document.getElementById('detail-container');

    detailContainer.innerHTML = '';

    console.log("Detail");
    var title = button.dataset.title;
    var address = button.dataset.address;
    var mapx = button.dataset.mapx;
    var mapy = button.dataset.mapy;
    var image = button.dataset.image;
    var contentId = button.dataset.contentId;
    var url = button.dataset.url;

    //removeMap();
    settingMap(parseFloat(mapy), parseFloat(mapx));

    const resultDiv = document.createElement('div');
    resultDiv.classList.add('result-item');

    const imageElement = document.createElement('img');
    imageElement.src = image;
    imageElement.style.width = '400px'; // 크기를 고정하여 추가
    resultDiv.appendChild(imageElement);

    // 상세 정보를 resultDiv에 추가
    resultDiv.innerHTML += `
        <br> 
        <h2><b>${title}</b></h2>
        <p>주소: ${address}</p>
    `;

    // resultDiv를 result-container에 추가
    detailContainer.appendChild(resultDiv);
}


// 모든 상세 정보 버튼에 이벤트 리스너 추가
document.querySelectorAll('.show-detail').forEach(button => {
    button.addEventListener('click', showDetail);
});