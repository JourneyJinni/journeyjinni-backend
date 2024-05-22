package com.ssafy.mvc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ssafy.mvc.model.*;

import com.ssafy.util.Distance.DistanceSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.mapper.AttractionMapper;
import com.ssafy.util.Distance.DistanceUtil;

import static com.ssafy.util.ImageSimilarity.calculateSimilarity;

@Slf4j
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
	public List<AttractionDto> allAttractions(NowLocation nowLocation) throws SQLException {
		return DistanceSort.isAroundSort(nowLocation.getLatitude(), nowLocation.getLongitude(), attractionMapper.allAttractions());
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


	@Override
	public List<UserTripDto> getUserAttraction(String tripId) throws SQLException {
		return attractionMapper.getUserAttraction(tripId);
	}

	@Override
	public void registerUserAttraction(UserAttractionDto dto) throws SQLException {
		attractionMapper.registerUserAttraction(dto);
		
	}

	@Override
	public void registerUserMapImage(UserMapImageDto dto) throws SQLException {
		attractionMapper.registerUserMapImage(dto);
		
	}

	@Override
	public List<UserMapImageDto> getMyMapImages(String userId) throws SQLException {
		return attractionMapper.getMyMapImages(userId);
	}


	@Override
	public UserAttractionDto getUserAttractionById(String attractionId) throws SQLException {
		return attractionMapper.getUserAttractionById(attractionId);
	}

	@Override
	public UserTripDto getUserTripById(String tripId) throws SQLException {
		
		return attractionMapper.getUserTripById(tripId);
	}

	@Override
	public UserMapImageDto getUserMapImageById(String imageId) throws SQLException {
		return attractionMapper.getUserMapImageById(imageId);
	}

	@Override
	public void deleteUserAttractionById(String attractionId) throws SQLException {
		attractionMapper.deleteUserAttractionById(attractionId);
		
	}

	@Override
	public void deleteTripById(String tripId) throws SQLException {
		attractionMapper.deleteTripById(tripId);
		
	}

	@Override
	public void deleteUserMapImageById(String imageId) throws SQLException {
		attractionMapper.deleteUserMapImageById(imageId);
		
	}

	@Override
	public void updateUserTripById(String tripId, String tripName, String isShared) throws SQLException {
		attractionMapper.updateUserTripById(tripId, tripName,isShared);
		
	}

	@Override
	public void updateUserAttractionById(String attractionId, String attractionName, String attractionDes)
			throws SQLException {
		attractionMapper.updateUserAttractionById(attractionId, attractionName, attractionDes);
		
	}

	@Override
	public void updateUserMapImageById(String imageId, String imageDes) throws SQLException {
		attractionMapper.updateUserMapImageById(imageId, imageDes);
		
	}


	public Long findMostSimilarImage(byte[] queryImageData) throws SQLException {
		List<ImageDto> allImages = attractionMapper.findAll();
		Long mostSimilarImageId = null;
		double maxSimilarity = Double.MIN_VALUE;

		for (ImageDto imageEntity : allImages) {
			double similarity = calculateSimilarity(queryImageData, imageEntity.getImage());
			if (similarity > maxSimilarity) {
				maxSimilarity = similarity;
				mostSimilarImageId = imageEntity.getImage_id();
				log.info(mostSimilarImageId + "가 더 높다! (" + similarity + ")");
			}
		}

		log.info("가장 높은 이미지는 " + mostSimilarImageId);
		return mostSimilarImageId;
	}


	




}
