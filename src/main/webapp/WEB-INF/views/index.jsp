<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ssafy.mvc.model.*" %>
<jsp:include page="/common/head.jsp" />

<body>
<script>
	window.onload=function(){
		window.location.href="${root}/attraction-controller?&action=list";
	}

</script>
   <jsp:include page="/common/nav.jsp" />
   <jsp:include page="/common/modal.jsp" />
    <!-- 중앙 content start -->
    <!-- header content start -->
    <header>
      <div style="height:80px"></div>
      <div class="container-fluid">
        <div class="row" id="header_div">
          <div class="col-md-9 row justify-content-center position-relative">
            <div class="row justify-content-center text-center text-white">
              <div style="height:30px;"></div>
              <h2>Enjoy Trip에 오신것을 환영합니다.</h2>
              <h4>나의 여행계획을 사랑하는 사람들과 공유하세요.</h4>
            </div>
          </div>
          <div class="col-md-3 row justify-content-center position-relative align-items-center">
            <div>
              <button type="button" id="map" class="btn btn-primary btn-sm" style="height: 120px; width:200px">
                지도로 가기
              </button>
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- header content end -->
    <!-- 중앙 center content start -->
    <!-- 중앙1 content start -->
    <div class="container-fluid p-3">
      <div class="container-fluid" id="main1_div">
        <div class="row row align-items-center position-relative text-center">
           	<h2 class="text-secondary fw-bolder mt-2">지역 관광지</h2>
            <h4>우리나라 곳곳의 명소입니다.</h4>
          <div class="col-lg-9 col-12">
            <h2></h2>
          </div>
        </div>
      </div>
      <!-- 중앙1 content end -->
      
     <%
      	List<AttractionDto> list = (List<AttractionDto>) request.getAttribute("attractionList");
      	
      %>
      
      <!-- 중앙2 content start -->
      <div class="container-fluid">
        <div class="col-md-12 mt-3">
          <div class="row fw-bold text-center">
            <h2></h2>
          </div>
          <div class="row mt-3">
          
<%
    Random random = new Random();
    int listSize = list.size();
    Set<Integer> chosenIndices = new HashSet<>();
    
    while (chosenIndices.size() < 4) {
        int randomIndex = random.nextInt(listSize);
        if (!chosenIndices.contains(randomIndex)) {
            chosenIndices.add(randomIndex);
            AttractionDto attraction = list.get(randomIndex);
            
            // 고유한 모달 ID 생성
            String modalId = "modal" + randomIndex;
%>
          
<div class="card col-lg-3 col-md-6">
    <div class="card-img-wrapper mt-2">
        <img src="<%= attraction.getFirstImage() %>" class="card-img-top img-fluid" alt="..." 
             onerror="this.src='${root }/assets/img/noimg.jpg'">
    </div>
    <div class="card-body">
        <h5 class="card-title"><%= attraction.getTitle() %></h5>
        <p class="card-text"><%= attraction.getAddr1() + attraction.getAddr2() %></p>
        
        <!-- 고유한 모달 ID를 data-target에 설정 -->
        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#<%= modalId %>">
            더보기
        </button>
    </div>
</div>

<!-- 각 카드에 대한 모달 -->
<div class="modal fade" id="<%= modalId %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">상세 정보</h5>&nbsp
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- 모달 내용 -->
                <div class="modal-img-wrapper">
                    <img src="<%= attraction.getFirstImage() %>" class="img-fluid" alt="..." onerror="this.src='${root }/assets/img/noimg.jpg'">
                </div>
                <h5 class="mt-1"><%= attraction.getTitle() %></h5>
                <p><%= attraction.getAddr1() + attraction.getAddr2() %></p>
                <div><%=attraction.getOverview()%></div>
                
                <!-- 상세 설명 등 추가 내용 -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

<%
        }
    }
%>
    
          </div>
        </div>
        <!-- 중앙2 content end -->
        <!-- 중앙 center content end -->
      </div>
    </div>
    <!-- 중앙 content end -->
	
	<jsp:include page="/common/footer.jsp" />
	

	
    
  </body>
  <!-- Latest compiled JavaScript -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="assets/js/main.js"></script>
  <script>
  	document.querySelector("#map").addEventListener("click",function(){
  		window.location.href="${root}/view/tour/attraction.jsp";
  	});
    $(document).ready(function() {
        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').trigger('focus')
        });
    });
  </script>

</html>
