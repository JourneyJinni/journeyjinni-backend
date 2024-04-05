package com.ssafy.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.mvc.model.dao.AttractionDao;
import com.ssafy.mvc.model.dao.AttractionDaoImpl;

public class AttractionServiceImpl implements AttractionService {

	private static AttractionService attractionService = new AttractionServiceImpl();
	private AttractionDao attractionDao;
	
	private AttractionServiceImpl() {
		attractionDao = AttractionDaoImpl.getAttractionDao();
	}
	
	public static AttractionService getAttractionService() {
		return attractionService;
	}

	@Override
	public List<AttractionDto> list() throws SQLException {
		return attractionDao.listAttraction();
	}

	@Override
	public AttractionDto getAttraction(int contentId) throws SQLException {
		return attractionDao.getAttractionbyContentId(contentId);
	}

	@Override
	public List<AttractionDto> getFilteredList(String[] cities, String[] categorys) throws SQLException {
		if (cities == null)
			cities = new String[0];

		if (categorys == null)
			categorys = new String[0];
		return attractionDao.fetchFilteredList(cities, categorys);
	}




}
