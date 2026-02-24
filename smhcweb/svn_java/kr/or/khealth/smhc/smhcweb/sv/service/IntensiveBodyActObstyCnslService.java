package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : IntensiveBodyActObstyCnslService.java
 * @Description : 관리자 WEB에서 집중상담_신체활동 비만 상담을 관리하는interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.23		이태석			최초생성
 *
 * @author gst
 * @since 2016.09.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface IntensiveBodyActObstyCnslService {
	
	public Map<String, String> getBasicUserInfo(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getIntensiveBodyGoalMngt(Map<String, Object> param)throws Exception;
	
	public Map<String, String> getCnslHistory(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getCnslDe(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> getCnslDePrdTotalInfo(Map<String, Object> param)throws Exception;
	
	public Map<String, String> getTotEval(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getSvWeekSttus(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getActRecord(Map<String, Object> param)throws Exception;
	
	public int getTotEvalupdate(Map<String, Object> param)throws Exception;
	
	public int updateBodyCnslSubmit(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> getBodyActDEList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> getBodyActDYList(Map<String, Object> param) throws Exception;	
	
	public int getTotEvalDelete(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> getCnslTemplateNm(Map<String, Object> param)throws Exception;
	
	public Map<String, Object> getCnslTemplateConts(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> getCnslTemplateList(Map<String, Object> param)throws Exception;
	
	public int getCnslTemplateUpdate(Map<String, Object> param) throws Exception;

	public int getCnslTemplateDel(Map<String, Object> param) throws Exception;
	
	// 2017.02.27 이태석 추가(파일첨부)
	public List<Map<String, Object>> getCnslAttchList(Map<String, Object> param)throws Exception;
	
	// 2017.03.30 이태석 추가(건강정보 조회)
	public List<Map<String, Object>> getHelthExam(Map<String, Object> param)throws Exception;
	
	// 2017.03.31 이태석 추가(목표 걸음수 달성율 조회)
	public Map<String, Object> getObjWalkAchvPer(Map<String, Object> param) throws Exception;
	
	// 2017.04.03 이태석 추가(주 평균 운동시간,횟수 조회)
	public Map<String, Object> getWeekExcsAvgTmCnt(Map<String, Object> param) throws Exception;
	
	// 2017.04.12 이태석 추가(심박수 시간대별 조회)
	public List<Map<String, Object>> getDayHeartRateDtaList(Map<String, Object> param)throws Exception;
	
	// 2017.05.18 추가(심박수 구간별 조회)
	public List<Map<String, Object>> getDayHeartRateSecList(Map<String, Object> param)throws Exception;	
	
	public List<Map<String, Object>> getDayHeartRateSecList2(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getActRecordChart(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getObjHrSucRate(Map<String, Object> param) throws Exception; 
	
	// 2017.06.26 추가(공통 동영상 목록 조회)	
	public List<Map<String, Object>> getBodyActVdTemplateList(Map<String, Object> param)throws Exception;
	
	public int updateBodyActAllSubmit(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> bodyCompInfoList(Map<String, Object> param) throws Exception;
}
