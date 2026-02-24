package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface ServiceObjMngtService {

	public Map<String, String> getUserInfo(Map<String, Object> param) throws Exception;

	public Map<String, String> getCnslInfo(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> getActiceCnt(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getControlCnt(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getActiveControl(Map<String, Object> param)throws Exception;
		
	public Map<String, String> getRecomCRFPer(Map<String, Object> param) throws Exception;
	
	public Map<String, String> changeNeedam(Map<String, Object> param)throws Exception;

	public Map<String, String> changeObjNeedam(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getObjEatNeed(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getRecommendEatCnt(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getDangerFactor(Map<String, Object> param)throws Exception;

	public void updateCnslInfo(Map<String, Object> param)throws Exception;

	public void updateCnslNurtInfo(Map<String, Object> param)throws Exception;

	public int getCnslSnSeq(Map<String, Object> param)throws Exception;

	public void insertNewCnslInfo(Map<String, Object> param)throws Exception;

	public void insertNewCnslNurtInfo(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getDateList(Map<String, Object> param)throws Exception;

	public Map<String, String> getMyWeek(Map<String, Object> param) throws Exception;
	
	public Map<String, String> checkingIntegration(Map<String, Object> param)throws Exception;

	public void success_pgmt(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getSerivceObjMngtList(Map<String, Object> param)throws Exception;

	public Map<String, String> getCountServiceObjMngt(Map<String, Object> param)throws Exception;
	
	
	/**
	 * 실천미션일정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectPractMissionSch(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> selectPractMissionSchChronic(Map<String, Object> param)throws Exception;
	
	/**
	 * 실천미션스케줄 생성
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public int insertCreatePractMissionSch(Map<String, Object> param) throws Exception;

	public int insertCreatePractMissionSchChronic(Map<String, Object> param) throws Exception;
	
	/**
	 * 실천미션 스케줄 변경 팝업 생성
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectPractMission(Map<String, Object> param)throws Exception;
	public List<Map<String, String>> selectPractMissionChronic(Map<String, Object> param)throws Exception;

	
	/**
	 * 실천미션 스케줄 변경 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public int updatePractMissionSch(Map<String, Object> param) throws Exception;
	public int updatePractMissionSchChronic(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스일정 생성 여부 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, Object> getSvcSchCreateYn(Map<String, Object> param) throws Exception;
	
	/**
	 * 목표상담 완료시 서비스개시일 지정 여부 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, Object> getCnslCompleteSvcBgnAppontYn(Map<String, Object> param) throws Exception;

	
	
	public List<Map<String, Object>> getSelfMngtSurveyList(Map<String, Object> param) throws Exception;

	public String getCnslSn(Map<String, Object> param) throws Exception;

	
	
	
}
