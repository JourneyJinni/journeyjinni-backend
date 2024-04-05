<%@ page import="java.util.List" %>
<%@ page import="com.ssafy.mvc.model.AttractionDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<jsp:include page="/common/head.jsp" />

<body>
 <jsp:include page="/common/nav.jsp" />
 <jsp:include page="/common/modal.jsp" />
  <!-- 중앙 content start -->

  <br><br>
  <br>
  <br>
  <!-- 중앙 content start -->
  <div class="container mt-3">
    <div class="row justify-content-center">
      <!-- 가운데 div -->
      <div class="col-md-6">
        <div class="rounded p-4 bg-light shadow">
          <h4 class="mb-4">Find your Tour</h4>
          <form class="rd-mailform form-fix" action = "${root}/attraction-controller?action=filterList" method="post">
            <div class="row row-20 row-fix">
              <div class="col-sm-12">
                <label class="form-label-outside font-weight-bold">지역</label>
                <div class="form-wrap form-wrap-inline">
                  <div class="container">
                    <div class="row">
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="1"
                            id="city1" name="city">
                          <label class="form-check-label font-weight-bold" for="city1">서울</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="6"
                            id="city2" name="city">
                          <label class="form-check-label font-weight-bold" for="city2">부산</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="4"
                            id="city3" name="city">
                          <label class="form-check-label font-weight-bold" for="city3">대구</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="2"
                            id="city4" name="city">
                          <label class="form-check-label font-weight-bold" for="city4">인천</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="5"
                            id="city5" name="city">
                          <label class="form-check-label font-weight-bold" for="city5">광주</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="3"
                            id="city6" name="city">
                          <label class="form-check-label font-weight-bold" for="city6">대전</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="7"
                            id="city7" name="city">
                          <label class="form-check-label font-weight-bold" for="city7">울산</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="8"
                            id="city8" name="city">
                          <label class="form-check-label font-weight-bold" for="city8">세종</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="31"
                            id="city31" name="city">
                          <label class="form-check-label font-weight-bold" for="city31">경기</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="32"
                            id="city32" name="city">
                          <label class="form-check-label font-weight-bold" for="city32">강원</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="33"
                            id="city33" name="city">
                          <label class="form-check-label font-weight-bold" for="city33">충북</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="34"
                            id="city34" name="city">
                          <label class="form-check-label font-weight-bold" for="city34">충남</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="35"
                            id="city35" name="city">
                          <label class="form-check-label font-weight-bold" for="city35">경북</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="36"
                            id="city36" name="city">
                          <label class="form-check-label font-weight-bold" for="city36">경남</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="37"
                            id="city37" name="city">
                          <label class="form-check-label font-weight-bold" for="city37">전북</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="38"
                            id="city38" name="city">
                          <label class="form-check-label font-weight-bold" for="city38">전남</label>
                        </div>
                      </div>
                      <div class="col-sm-2">
                        <div class="form-check">
                          <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="39"
                            id="city39" name="city">
                          <label class="form-check-label font-weight-bold" for="city39">제주</label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <label class="form-label-outside font-weight-bold">서비스</label>
              <div class="row">
                <div class="col-sm-4">
                  <div class="form-check">
                    <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="32"
                      id="category32" name="category">
                    <label class="form-check-label font-weight-bold" for="category32">숙박</label>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div class="form-check">
                    <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="12"
                      id="category12" name="category">
                    <label class="form-check-label font-weight-bold" for="category12">관광지</label>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div class="form-check">
                    <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="39"
                      id="category39" name="category">
                    <label class="form-check-label font-weight-bold" for="category39">음식점</label>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div class="form-check">
                    <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="14"
                      id="category14" name="category">
                    <label class="form-check-label font-weight-bold" for="category14">문화시설</label>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div class="form-check">
                    <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="15"
                      id="category15" name="category">
                    <label class="form-check-label font-weight-bold" for="category15">공연</label>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div class="form-check">
                    <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="25"
                      id="category25" name="category">
                    <label class="form-check-label font-weight-bold" for="category25">여행 코스</label>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div class="form-check">
                    <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="28"
                      id="category28" name="category">
                    <label class="form-check-label font-weight-bold" for="category28">레포츠</label>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div class="form-check">
                    <input class="form-check-input" style="width: 25px; height: 25px;" type="checkbox" value="38"
                      id="category38" name="category">
                    <label class="form-check-label font-weight-bold" for="category38">숙박</label>
                  </div>
                </div>

                <br><br>

                <div class="form-wrap form-button">
                  <button type="submit" class="btn btn-primary">검색</button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

 <br><br><br>

 <!-- 결과 표시 -->
 <div class="container mt-3">
   <div class="row">
     <!-- 왼쪽: 검색 결과 -->
     <div class="col-md-6">
       <div class="rounded p-4 bg-light shadow">
         <h4 class="mb-4">Search Results</h4>
         <ul>
           <%
             List<AttractionDto> filteredList = (List<AttractionDto>) request.getAttribute("filteredList");
             if (filteredList != null && !filteredList.isEmpty()) {
               int count = 0;
               for (AttractionDto attractionDto : filteredList) {
                 if (count < 10) { // 10개까지만 표시
                   String modalId = "modal" + count;
           %>
           <li class="row">
             <div class="col-9">
               <h4><%= attractionDto.getTitle() %></h4>
               <p><%= attractionDto.getAddr1() %></p>
             </div>
             <div class="col-3">

               <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#<%= modalId %>">
                   더보기
                 </button> <button class="btn btn-primary" onclick="settingMap(<%= attractionDto.getLatitude()%>, <%= attractionDto.getLongitude() %>)">위치</button>
             </div>
           </li>
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
                     <img src="<%= attractionDto.getFirstImage() %>" class="img-fluid" alt="..." 
                     onerror="this.src='${root }/assets/img/noimg.jpg'">
                   </div>
                   <h5 class="mt-1"><%= attractionDto.getTitle() %></h5>
                   <p><%= attractionDto.getAddr1() + attractionDto.getAddr2() %></p>
                   <div><%=attractionDto.getOverview()%></div>


                 </div>
                 <div class="modal-footer">
                   <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">닫기</button>
                 </div>
               </div>
             </div>
             <script>
               // 모달이 열릴 때마다 호출되는 함수
               function onModalOpen_<%= modalId %>() {
                 // 모달이 열릴 때마다 map.relayout() 호출
                 map_<%= modalId %>.relayout();
               }

               // 모달이 열릴 때 이벤트 핸들러 등록
               $('#<%= modalId %>').on('shown.bs.modal', onModalOpen_<%= modalId %>);
             </script>
           </div>
           <%
                 count++;
               } else {
                 break;
               }
             }
           } else {
           %>
           <li>결과가 없습니다.</li>
           <% } %>
         </ul>
       </div>
     </div>
     <!-- 오른쪽: 카카오맵 -->
     <div id="map" style="width:500px;height:400px;"></div>

     <!-- JavaScript 파일 불러오기 -->
     <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fba22d17b9dfcc039051c2097282d942"></script>

     <!-- map.js 파일 불러오기 -->
     <script src="<%= request.getContextPath() %>/assets/js/kakaomap.js"></script>
     <!-- HTML에서 함수 호출하여 원하는 위도와 경도 값을 전달 -->

   </div>
 </div>

  <!-- 중앙 content end -->

  <br><br><br>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<jsp:include page="/common/footer.jsp" />
</body>
<!-- Latest compiled JavaScript -->

<script>

    $(document).ready(function() {
        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').trigger('focus')
        });
    });
  </script>


