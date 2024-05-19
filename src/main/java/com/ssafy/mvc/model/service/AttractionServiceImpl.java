package com.ssafy.mvc.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ssafy.mvc.model.CategoryDto;
import com.ssafy.mvc.model.FilterRequestDto;
import com.ssafy.mvc.model.GugunDto;
import com.ssafy.mvc.model.SidoDto;
import com.ssafy.mvc.model.UserTripDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.AttractionDto;
import com.ssafy.util.Distance.DistanceSort;
import com.ssafy.mvc.model.NowLocation;
import com.ssafy.mvc.model.mapper.AttractionMapper;
import com.ssafy.util.Distance.DistanceUtil;


@Service
public class AttractionServiceImpl implements AttractionService {


	private final AttractionMapper attractionMapper;

    private AttractionServiceImpl(AttractionMapper attractionMapper) {
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<GugunDto> getGugun(String sidoCode) throws SQLException {
		return attractionMapper.getGugun(sidoCode);
	}
	@Override
	public List<SidoDto> getCities() throws SQLException {
		return attractionMapper.getCities();
	}

	@Override
	public List<CategoryDto> getCategories() throws SQLException {
		return attractionMapper.getCategories();
	}

	@Override
	public List<AttractionDto> list() throws SQLException {
		return attractionMapper.listAttraction();
	}

	@Override
	public AttractionDto getAttraction(int contentId) throws SQLException {
		return attractionMapper.getAttractionbyContentId(contentId);
	}

	@Override
	public List<AttractionDto> getFilteredList(FilterRequestDto request) throws SQLException {

        //NowLocation nowLocation = NowLocation.getLocation();
		//return DistanceSort.isAroundSort(nowLocation.getLatitiude(), nowLocation.getLongtitude(), attractionMapper.fetchFilteredList(request));
		return attractionMapper.fetchFilteredList(request);
	}
	
	public List<Integer> getRoute(ArrayList<String[]> list){
		List<Integer> route = new ArrayList<Integer>();
		double adjMatrix[][] = new double[list.size()][list.size()];
		for(int i=0;i<list.size();i++) {
			for(int j=i; j<list.size();j++) {
				Double from1 = Double.parseDouble(list.get(i)[1]);
				Double from2 = Double.parseDouble(list.get(i)[2]);
				Double to1 = Double.parseDouble(list.get(j)[1]);
				Double to2 = Double.parseDouble(list.get(j)[2]);
				adjMatrix[i][j] = DistanceUtil.getDistance(from1,from2,to1,to2);
				adjMatrix[j][i] = DistanceUtil.getDistance(from1,from2,to1,to2);
			}
		}
		for(double[] row : adjMatrix) {
			System.out.println(Arrays.toString(row));
		}
		dfs(route,adjMatrix,new boolean[list.size()], 0, 0);
		return route;
		
	}
	public void dfs(List<Integer> route, double adjMatrix[][], boolean visited[], int r, int cnt) {
		if(visited.length ==cnt) {
			return;
		}
		route.add(r);
		visited[r] = true;
		double min = Double.MAX_VALUE;
		int minVertex = -1;
		for(int i=0;i<adjMatrix[r].length;i++) {
			if(i==r||visited[i]) continue;
			if(min >adjMatrix[r][i]) {
				min = adjMatrix[r][i];
				minVertex= i;
			}
			
		}
		if(minVertex==-1) {
			return;
		}
		dfs(route,adjMatrix,visited,minVertex,cnt+1);
		
	}

	@Override
	public List<UserTripDto> getUserTrip(String userId) throws SQLException {
		
		return attractionMapper.getUserTrip(userId);
	}

	@Override
	public void registerUserTrip(String userId, String tripName) throws SQLException {
		attractionMapper.registerUserTrip(userId, tripName);
		return;
		
	}



	




}
