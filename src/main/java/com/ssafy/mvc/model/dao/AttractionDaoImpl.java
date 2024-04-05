package com.ssafy.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao{
	
	private static AttractionDao attractionDao;
	private StringBuilder sql;
	private AttractionDaoImpl() {};
	
	public static AttractionDao getAttractionDao() {
		if(attractionDao == null)
			attractionDao = new AttractionDaoImpl();
		
		return attractionDao;
	}

	@Override
	public List<AttractionDto> listAttraction() throws SQLException {
		List<AttractionDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		     conn = DBUtil.getConnection();
		        sql = new StringBuilder();
		        sql.append("select * "
		        		+ "from attraction_info ai join attraction_description ad "
		        		+ "where ai.content_id = ad.content_id "
		        		+ "ORDER BY ai.content_id desc");
		        pstmt = conn.prepareStatement(sql.toString());

		        rs = pstmt.executeQuery();
		        while (rs.next()) {
		            AttractionDto attractionDto = new AttractionDto(
		                rs.getInt("content_id"),
		                rs.getInt("content_type_id"),
		                rs.getString("title"),
		                rs.getString("addr1"),
		                rs.getString("addr2"),
		                rs.getString("zipcode"),
		                rs.getString("tel"),
		                rs.getString("first_image"),
		                rs.getString("first_image2"),
		                rs.getInt("readcount"),
		                rs.getInt("sido_code"),
		                rs.getInt("gugun_code"),
		                rs.getBigDecimal("latitude"),
		                rs.getBigDecimal("longitude"),
		                rs.getString("mlevel"),
		                rs.getString("overview")
		            );
		            list.add(attractionDto);
		        }
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * contentId로 관광지 정보 가져오기
	 * @param contentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public AttractionDto getAttractionbyContentId(int contentId) throws SQLException {
	    AttractionDto attractionDto = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBUtil.getConnection();
	        sql = new StringBuilder();

	        sql.append("SELECT *");
	        sql.append("FROM attraction_info ai join attraction_description ad ");
	        sql.append("WHERE ai.content_id = ad.content_id and ai.content_id = ?");

	        
	        pstmt = conn.prepareStatement(sql.toString());
	        pstmt.setInt(1, contentId);
	        
	        rs = pstmt.executeQuery();
	        if (rs.next()) {

	            attractionDto = new AttractionDto(
	                rs.getInt("content_id"),
	                rs.getInt("content_type_id"),
	                rs.getString("title"),
	                rs.getString("addr1"),
	                rs.getString("addr2"),
	                rs.getString("zipcode"),
	                rs.getString("tel"),
	                rs.getString("first_image"),
	                rs.getString("first_image2"),
	                rs.getInt("readcount"),
	                rs.getInt("sido_code"),
	                rs.getInt("gugun_code"),
	                rs.getBigDecimal("latitude"),
	                rs.getBigDecimal("longitude"),
	                rs.getString("mlevel"),
	                rs.getString("overview")
	         
	            );

	        }
	    } finally {
	        DBUtil.close(rs, pstmt, conn);
	    }
	    return attractionDto;
	}

	/**
	 * 조건 달고 관광지 리스트 가져오기
	 * @param cities
	 * @param categotys
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<AttractionDto> fetchFilteredList(String[] cities, String[] categotys) throws SQLException {
		List<AttractionDto> filteredList = new ArrayList<>();


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			conn = DBUtil.getConnection();
			sql = new StringBuilder();

			sql.append("SELECT *");
	        sql.append("FROM attraction_info ai join attraction_description ad using (content_id)");
	        sql.append("WHERE ");


			// 시도 및 카테고리에 따른 쿼리문 설정
			if (cities.length != 0) {
				sql.append("ai.sido_code IN (");
				for (int i = 0; i < cities.length; i++) {
					sql.append("?");
					// 다음이 존재한다면 "," 찍기
					if (i < cities.length - 1)
						sql.append(",");
				}
				sql.append(") ");
			}

			if (categotys.length != 0) {
				if (cities.length != 0)
					sql.append("AND ");

				sql.append("ai.content_type_id IN (");
				for (int i = 0; i < categotys.length; i++) {
					sql.append("?");
					if (i < categotys.length - 1)
						sql.append(",");
				}
				sql.append(")");
			}

			pstmt = conn.prepareStatement(sql.toString());

			// PreparedStatement에 파라미터 설정
			int parameterIndex = 1;
			for (String city : cities) {
				pstmt.setString(parameterIndex++, city);
			}
			for (String category : categotys) {
				pstmt.setString(parameterIndex++, category);
			}

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				AttractionDto attractionDto = new AttractionDto();

				attractionDto.setContentId( resultSet.getInt("content_id"));
				attractionDto.setContentTypeId(resultSet.getInt("content_type_id"));
				attractionDto.setTitle(resultSet.getString("title"));
				attractionDto.setAddr1( resultSet.getString("addr1"));
				attractionDto.setAddr2( resultSet.getString("addr2"));
				attractionDto.setFirstImage(resultSet.getString("first_image"));
				attractionDto.setOverview(resultSet.getString("overview"));
				attractionDto.setLatitude(resultSet.getBigDecimal("latitude"));
				attractionDto.setLongitude(resultSet.getBigDecimal("longitude"));


				filteredList.add(attractionDto);

			}
		} catch (SQLException e) {
			System.out.println("[error] fetchFilterdList에서 SQL 에러");
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, pstmt, conn);
		}

		return filteredList;
	}

}
