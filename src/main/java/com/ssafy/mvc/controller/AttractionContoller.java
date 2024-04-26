package com.ssafy.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.NowLocation;
import com.ssafy.mvc.model.service.AttractionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AttractionContoller {
    private static final long serialVersionUID = 1L;


    private AttractionService attractionService;
    
    public AttractionContoller(AttractionService attractionService) {
    	this.attractionService = attractionService; 
	}
    
	 @GetMapping("/attraction")
	 public String attraction() {
		 System.out.println("[Log] attraction으로 이동");
		 return "tour/attraction"; 
	 }
    
    
    @GetMapping(value = {"/", "/index", "/main"})
    public String index(HttpServletRequest request ) {
    	  try {
			List<AttractionDto> attractionList=attractionService.list();
			request.setAttribute("attractionList",attractionList );
			System.out.println("[Log] : 리스트를 불러옴! " + attractionList.size() + " 개");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return "index";
    }
    
    @PostMapping("/filterList")
    public String filterList(@RequestParam(name = "city") String city, @RequestParam(name = "category") String category, 
    		 HttpServletRequest request) {
    	
    	
    	Map<String, Object> map = new HashMap<>();
    	
    	for (String value : request.getParameterValues("city")) {
    		map.put("cities", value);
    	}
    	
    	for (String value : request.getParameterValues("category")) {
    		map.put("categorys", value);
    	}
    	
		 try {
			List<AttractionDto> filteredList = attractionService.getFilteredList(map);
			System.out.println("[Log] : filterList 실행");
			request.setAttribute("filteredList", filteredList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "tour/attraction";
    }
    
//    @PostMapping("/nowLocation")
//    public String nowLocationi(@RequestParam("latitude") String latitude, @RequestParam("longtitide") String longitude) {
//    	System.out.println("[Log] : 위치 정보가 사용됨!");
//    	
//    	new NowLocation(latitude, longitude);
//    	return "attraction";
//    }
    
    @PostMapping("/nowLocation")
    public String nowLocationi(HttpServletRequest request) {
    	System.out.println("[Log] : 위치 정보가 사용됨!");
    	String latitude = request.getParameter("latitude");
    	String longitude = request.getParameter("longitude");
    	new NowLocation(latitude, longitude);
    	
    	return "attraction";
    }
    
    

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        attractionService = AttractionServiceImpl.getAttractionService();
//    }
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        try {
//            if ("list".equals(action)) {
//                List<AttractionDto> attractionList=attractionService.list();
//                
//                request.setAttribute("attractionList", attractionList);
//                forward(request, response, "/index.jsp");
//
//            } else if ("filterList".equals(action)) {
//                String[] cities = request.getParameterValues("city");
//                String[] categorys = request.getParameterValues("category");
//
//                List<AttractionDto> filteredList = attractionService.getFilteredList(cities, categorys);
//
//                request.setAttribute("filteredList", filteredList);
//                forward(request, response, "/view/tour/attraction.jsp");
//            } else if ("attractionDetail".equals(action)) {
//                String contentId = request.getParameter("selectedAttractionId");
//
//                AttractionDto attractionDetail = attractionService.getAttraction(Integer.parseInt(contentId));
//                request.setAttribute("attractionDetail", attractionDetail);
//                forward(request, response, "/view/tour/attractiondetail.jsp");
//            } else if("attractionRoute".equals(action)) {
//            	ArrayList<String[]> list = new ArrayList<>();
//            	String[] checked = request.getParameterValues("attractionCheckbox");
//            	for(int i=0;i<checked.length;i++) {
//            		String[] input = request.getParameter("attration"+checked[i]).split(",");
//            		if(input !=null) {
//            			list.add(input);
//            		}
//            	}
//            	
//        		List<Integer> route = attractionService.getRoute(list);
//        		System.out.println(route.toString());
////            	System.out.println(Arrays.toString(checked));
////        		for(String[] p : list) {
////        			System.out.println(p[0] + " " + p[1] + " " + p[2]);
////        		}
//            } else if ("nowLocation".equals(action)) {
//            	System.out.println("[Log] : 위치 정보가 사용됨!");
//                String latitude = request.getParameter("latitude");
//                String longitude = request.getParameter("longitude");
//                
//                new NowLocation(latitude, longitude);
//                forward(request, response, "/view/tour/attraction.jsp");
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        doGet(request, response);
//    }
//
//    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//        dispatcher.forward(request, response);
//    }
//
//    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//        response.sendRedirect(request.getContextPath() + path);
//    }

}