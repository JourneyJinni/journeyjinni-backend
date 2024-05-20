package com.ssafy.mvc.service;

import com.ssafy.mvc.model.Board.BoardDto;
import com.ssafy.mvc.model.Board.BoardListDto;

import java.util.Map;

public interface BoardService {

	void writeArticle(BoardDto boardDto) throws Exception;
	BoardListDto listArticle(Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(BoardDto boardDto) throws Exception;
//	
	void deleteArticle(int articleNo) throws Exception;


}
