package com.ssafy.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.mvc.model.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao{
	
	private static MemberDao instance = new MemberDaoImpl();
	
	private MemberDaoImpl() {
		
	}
	
	public static MemberDao getInstance() {
		return instance;
	}

	@Override
	public MemberDto memberView(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto memberDto = new MemberDto();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from members \n");
			sql.append("where user_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDto.setUser_id(rs.getString("user_id"));
				memberDto.setUser_name(rs.getString("user_name"));
				memberDto.setEmail_id(rs.getString("email_id"));
				memberDto.setEmail_domain(rs.getString("email_domain"));
				memberDto.setJoin_date(rs.getString("join_date"));
			}
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}
	
	@Override
	public MemberDto memberLogin(String user_id, String user_password) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto memberDto = new MemberDto();
		System.out.println("login start - sql");
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from members \n");
			sql.append("where user_id = ? \n");
			sql.append("and user_password = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDto.setUser_id(rs.getString("user_id"));
				memberDto.setUser_name(rs.getString("user_name"));
				memberDto.setEmail_id(rs.getString("email_id"));
				memberDto.setEmail_domain(rs.getString("email_domain"));
				memberDto.setJoin_date(rs.getString("join_date"));
			}
			System.out.println(memberDto.getUser_name());
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

	@Override
	public int memberIdCheck(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) count from members \n");
			sql.append("where user_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		System.out.println(result);
		return result;
	}

	@Override
	public void memberSignUp(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into members(user_id, user_name, user_password, email_id, email_domain) \n");
			sql.append("values(?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberDto.getUser_id());
			pstmt.setString(2, memberDto.getUser_name());
			pstmt.setString(3, memberDto.getUser_password());
			pstmt.setString(4, memberDto.getEmail_id());
			pstmt.setString(5, memberDto.getEmail_domain());
			
			pstmt.executeUpdate();
			
		} finally {
			DBUtil.close(pstmt, conn);
		}
			
	}

	@Override
	public void memberModify(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members set \n");
			sql.append("user_name = ?, user_password = ?, email_id = ?, email_domain = ? \n");
			sql.append("where user_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberDto.getUser_name());
			pstmt.setString(2, memberDto.getUser_password());
			pstmt.setString(3, memberDto.getEmail_id());
			pstmt.setString(4, memberDto.getEmail_domain());
			pstmt.setString(5, memberDto.getUser_id());
			
			pstmt.executeUpdate();
			
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public void memberDelete(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from members \n");
			sql.append("where user_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			
			pstmt.executeUpdate();
			
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
	}

}
