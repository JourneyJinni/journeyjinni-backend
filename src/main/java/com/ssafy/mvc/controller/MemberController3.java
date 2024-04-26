package com.ssafy.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.mvc.model.service.MemberService;
import com.ssafy.mvc.model.service.MemberServiceImpl;


@Controller
public class MemberController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;
	
	public MemberController3(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String service = request.getParameter("service");
		String path = "";
		
		switch(service) {
			case "home" :
				path = "/index.jsp";
				redirect(request, response, path);
				break;
				
			case "login" :
				path = login(request, response);
				forward(request, response, path);
				break;
				
			case "logout" :
				path = logout(request, response);
				forward(request, response, path);
				break;
				
			case "signup":
				path = signUp(request, response);
				forward(request, response, path);
				break;
				
			case "deluser":
				path = delUser(request, response);
				forward(request, response, path);
				break;
				
			case "infouser":
				path = infoUser(request, response);
				forward(request, response, path);
				break;
				
			case "modifyuser":
				path = modifyUser(request, response);
				forward(request, response, path);
				break;
				
			case "idcheck":
				idCheck(request, response);
				break;			
				
			default :
				path = "/error.jsp";
				redirect(request, response, path);
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
	
	protected String login(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_pw");
		System.out.println(user_id + " " + user_password);
		
		try {
			//MemberDto memberDto = memberService.memberLogin(user_id, user_password);
			//if(memberDto.getUser_name() == null) return "/index.jsp";
			HttpSession session = request.getSession(true);
			
			//request.setAttribute("memberDto", memberDto);
			//session.setAttribute("userInfo", memberDto);
			
			return "/index.jsp";
		} catch(Exception e) {
			e.printStackTrace();
			return "/common/error.jsp";
		}
	}
	
	protected String logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("로그아웃");
			HttpSession session = request.getSession(false);
			
			if(session != null && session.getAttribute("userInfo") != null) {
				session.removeAttribute("userInfo");
				session.invalidate();
				System.out.println("로그아웃");
			}
			
			return "/index.jsp";
		} catch(Exception e) {
			e.printStackTrace();
			return "/common/error.jsp";
		}
	}
	
	protected String signUp(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto(); 
		memberDto.setUser_id(request.getParameter("sign_user-id"));
		memberDto.setUser_name(request.getParameter("sign_user-name"));
		memberDto.setUser_password(request.getParameter("sign_user-pw"));
		memberDto.setEmail_id(request.getParameter("sign_user-email"));
		memberDto.setEmail_domain(request.getParameter("sign_user-domain"));
		
		StringBuilder sb = new StringBuilder();
		sb.append(memberDto.getUser_id() + " ");
		sb.append(memberDto.getUser_name() + " ");
		sb.append(memberDto.getUser_password() + " ");
		sb.append(memberDto.getEmail_id() + " ");
		sb.append(memberDto.getEmail_domain() + " ");
		System.out.println(sb);
		try {
			memberService.memberSignUp(memberDto);
			
			return "/index.jsp";
		} catch(Exception e) {
			e.printStackTrace();
			return "/common/error.jsp";
		}
	}
	
	protected String delUser(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		
		try {
			memberService.memberDelete(user_id);
			
			return logout(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			return "/common/error.jsp";
		}
	}
	
	protected String infoUser(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		
		try {
			MemberDto memberDto = memberService.memberView(user_id);
			request.setAttribute("memberInfo", memberDto);
			
			return "/view/member/memberInfo.jsp";
		} catch(Exception e) {
			e.printStackTrace();
			return "/common/error.jsp";
		}
	}
	
	protected String modifyUser(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto(); 
		memberDto.setUser_id(request.getParameter("info-id"));
		memberDto.setUser_name(request.getParameter("info-name"));
		memberDto.setUser_password(request.getParameter("info-pw"));
		memberDto.setEmail_id(request.getParameter("info-email_id"));
		memberDto.setEmail_domain(request.getParameter("info-email_domain"));
		
		System.out.println(request.getParameter("info-id"));
		System.out.println(request.getParameter("info-name"));
		System.out.println(request.getParameter("info-pw"));
		System.out.println(request.getParameter("info-email_id"));
		System.out.println(request.getParameter("info-email_domain"));
		
		try {
			memberService.memberModify(memberDto);
			
			return logout(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			return "/common/error.jsp";
		}
	}
	
	protected void idCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String data = request.getReader().lines().collect(Collectors.joining());
		JSONObject jo = new JSONObject(data);
		
		String checkId = jo.getString("checkUserId");
		System.out.println(checkId);
		
		JSONObject result = new JSONObject();
		try {
			if(memberService.memberIdCheck(checkId) == 0) {
				result.put("success", true);
				System.out.println("JSON true");
			} else {
				result.put("success", false);
				System.out.println("JSON false");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		return;
	}
}


