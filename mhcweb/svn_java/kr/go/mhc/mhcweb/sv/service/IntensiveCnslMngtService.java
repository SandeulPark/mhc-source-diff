package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : IntensiveCnslMngtService.java
 * @Description : 관리자 WEB에서 사용하는 집중 상담업무를 관리하는interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.01	장슬기			최초생성
 *
 * @author gst
 * @since 2016.09.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface IntensiveCnslMngtService {

	public List<Map<String, Object>> getIntensiveCnslMngtList(Map<String, Object> param) throws Exception;

	public Map<String, String> getCountIntensiveCnsl(Map<String, Object> param)throws Exception;

	public Map<String, String> getBasicUserInfo(Map<String, Object> param)throws Exception;
	
//	추가
	public Map<String, String> getManagerType(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> getGoalMngt(Map<String, Object> param)throws Exception;

	public Map<String, String> getCnslHistory(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> getMealRegDe(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> getCnslDe(Map<String, Object> param)throws Exception;

	public Map<String,Object> getMealDietInfo(Map<String, Object> param)throws Exception;
	
	public Map<String, String> getIntakeSttus(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> getEvalActive(Map<String, Object> param) throws Exception;
	
	public int updateIntensiveCnslEval(Map<String, Object> param) throws Exception;
	
	public int updateSubmit(Map<String, Object> param) throws Exception;
	
	public int deleteIntensiveCnslEval(Map<String, Object> param) throws Exception;

	public Map<String, String> selectHealthCnslDtls(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> selectHealthCnslAttchFiles(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> selectHealthCnslBottomList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> getCnslTemplateNm(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getPractMissionRslt(Map<String, Object> param)throws Exception;	

	// 2017.03.03 이태석 추가(파일첨부)
	public List<Map<String, Object>> getCnslAttchList(Map<String, Object> param)throws Exception;
	
	//식사일기 탭 추가
	public List<Map<String, Object>> getMealDiaryList(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> getMealDiaryInputInfo(Map<String, Object> param)throws Exception;	
	
	public Map<String,Object> getMealAssayRslt(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> getCRFPerRslt(Map<String, Object> param) throws Exception;	
	
	public List<Map<String, Object>> getMealDivCal(Map<String, Object> param) throws Exception;		

	public List<Map<String, Object>> getMealNutriRslt(Map<String, Object> param) throws Exception;	

	public Map<String,Object> getMealEtcRslt(Map<String, Object> param) throws Exception;	
	
   //집중상담 화면 변경
	public List<Map<String, Object>> getPrdTotalinfo(Map<String, Object> param)throws Exception;		

	public List<Map<String, Object>> getSvWeekTotalnfo(Map<String, Object> param) throws Exception;	
	
	public List<Map<String, Object>> getCalTotalInfo(Map<String, Object> param) throws Exception;		

	public List<Map<String, Object>> getCRFTotalnfo(Map<String, Object> param) throws Exception;	

	public  List<Map<String, Object>> getMealAvgInfo(Map<String, Object> param) throws Exception;		
	

	
	public List<Map<String, Object>> getMealAssayTotalInfo(Map<String, Object> param) throws Exception;		

	public List<Map<String, Object>> getMealEtcTotalInfo(Map<String, Object> param) throws Exception;	

	public  List<Map<String, Object>> getMealNutriTotalInfo(Map<String, Object> param) throws Exception;		
	
	
	public List<Map<String, Object>> getCnslContHist(Map<String,Object> param) throws Exception;
	
	public int updateNutriAllSubmit(Map<String, Object> param) throws Exception;
	
	//20191209 양현우 추가
	public List<Map<String, Object>> selectIntensiveCnslMngtListPop(Map<String, Object> param) throws Exception;

	public int updateIntensiveCnslMngtListPop(Map<String, Object> param) throws Exception;
	
	public List<Map<String,Object>> getAlgoPop(Map<String,Object> param) throws Exception;
	
	public List<Map<String,Object>> getAutoAlgoPop(Map<String,Object> param) throws Exception;
	
	public List<Map<String,Object>> getAlgoPopSetting(Map<String,Object> param) throws Exception;
}
