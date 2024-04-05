package com.ssafy.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.service.AttractionService;
import com.ssafy.mvc.model.service.AttractionServiceImpl;
import com.ssafy.util.Distance.NowLocation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/attraction-controller")
public class AttractionContoller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static AttractionService attractionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        attractionService = AttractionServiceImpl.getAttractionService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                List<AttractionDto> attractionList=attractionService.list();
                
                request.setAttribute("attractionList", attractionList);
                forward(request, response, "/index.jsp");

            } else if ("filterList".equals(action)) {
                String[] cities = request.getParameterValues("city");
                String[] categorys = request.getParameterValues("category");

                List<AttractionDto> filteredList = attractionService.getFilteredList(cities, categorys);

                request.setAttribute("filteredList", filteredList);
                forward(request, response, "/view/tour/attraction.jsp");
            } else if ("attractionDetail".equals(action)) {
                String contentId = request.getParameter("selectedAttractionId");

                AttractionDto attractionDetail = attractionService.getAttraction(Integer.parseInt(contentId));
                request.setAttribute("attractionDetail", attractionDetail);
                forward(request, response, "/view/tour/attractiondetail.jsp");
            } else if("attractionRoute".equals(action)) {
            	ArrayList<String[]> list = new ArrayList<>();
            	for(int i=0;i<10;i++) {
            		String[] input = request.getParameter("attration"+i).split(" ");
            		if(input !=null) {
            			list.add(input);
            		}
            	}
            } else if ("nowLocation".equals(action)) {
            	System.out.println("[Log] : 위치 정보가 사용됨!");
                String latitude = request.getParameter("latitude");
                String longitude = request.getParameter("longitude");
                
                new NowLocation(latitude, longitude);
                forward(request, response, "/view/tour/attraction.jsp");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }

}