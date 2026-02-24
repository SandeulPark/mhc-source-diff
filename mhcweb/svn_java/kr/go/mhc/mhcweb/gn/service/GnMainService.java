package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;

public interface GnMainService {
	
	
	/**
	 * 커뮤니티 조회
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public List<Map<String,Object>> getCmntyList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 그룹참가자 cnt
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public int getGrpJoinCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹참가 승인cnt
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public int getGrpJoinApproval(Map<String, Object> param) throws Exception;
	
	/**
	 * 스포츠 활동 인증 cnt
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public int getSportActivityCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 포인트랭킹 순위
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */	
	public List<Map<String,Object>> getPointRankingSn(Map<String, Object> param) throws Exception;
	
	/**
	 * 걸음수 랭킹 순위
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */	
	public List<Map<String,Object>> getStepRankingList(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹별 남,여 CNT
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */	
	public List<Map<String,Object>> getGrpGenderCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 연령별 비율 CNT
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */	
	public List<Map<String,Object>> getAgeCnt(Map<String, Object> param) throws Exception;

	/**
	 * 그룹 현황 정보
	 * @param param
	 * @return
	 */
	public Map<String, Object> getGrpJoinInfo(Map<String, Object> param) throws Exception;

	
	/**
	 * 스포츠 활동 인증 정보 
	 * @param param
	 * @return
	 */
	public Map<String, Object> getSportActivityInfo(Map<String, Object> param) throws Exception;


	/**
	 * 셀프미션 수행 정보 	
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSelfMissionInfo(Map<String, Object> param) throws Exception;

	/**
	 * 식사일기 작성 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMealDiaryInfo(Map<String, Object> param) throws Exception;
	
	
	
	
	
}
