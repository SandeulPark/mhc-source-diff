package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.extrus.common.json.simple.JSONArray;
import com.extrus.common.json.simple.JSONObject;
import com.extrus.common.json.simple.parser.JSONParser;

import kr.go.mhc.mhcapp.gn.service.GnrlMyHealthMainDashService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : GnrlMyHealthMainDashServiceImpl.java
 * @Description : 보편건강 App에서 사용하는 나의건강-활동량에서 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.08.13		이태석			최초생성
 * 		
 * @author thejoin
 * @since 2019.08.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.gn.GnrlMyHealthMainDashService")
public class GnrlMyHealthMainDashServiceImpl extends EgovAbstractServiceImpl implements GnrlMyHealthMainDashService{

	@Resource(name="mhcapp.gn.GnrlMyhealthMainDashDAO")
    private GnrlMyhealthMainDashDAO gnrlMyhealthDAO;
	
	/**
	 * 시간별 활동량 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectDayActCnt(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectDayActCnt(param);
	}
	
	/**
	 * 입력 정보 조회 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> selectInsertInfo(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectInsertInfo(param);
	}
	
	/**
	 * 활동량 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectActDtlsList(param);
	}
	/**
	 * 활동량 총걸음수, 총거리, 칼로리, 운동시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActTot(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectActTot(param);
	}
	
	/**
	 * 활동량 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActChartList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectActChartList(param);
	}
	
	/**
	 * 활동량 상세 1주일 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActDtlsStat2(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectActDtlsStat2(param);
	}
	/**
	 * 활동량 상세 1,3개월 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActDtlsStat3(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectActDtlsStat3(param);
	}
	
	/**
	 * 활동량 상세 1주일 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActDtlsChart2(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectActDtlsChart2(param);
	}
	
	/**
	 * 활동량 상세 1,3개월 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActDtlsChart3(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectActDtlsChart3(param);
	}
	
	/**
	 * 나의건강 상세 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBodyCompChartList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBodyCompChartList(param);
	}
	
	/**
	 * 체성분 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBodyCompDtlsChartList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBodyCompDtlsChartList(param);
	}
	
	/**
	 * 체성분 상세 각 데이터 차이값 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBodyCompDtlsList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBodyCompDtlsList(param);
	}
	
	/**
	 * 체성분 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectBodycompList(Map param) throws Exception{
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBodycompList(param);
	}
	
	/**
	 * 혈당 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodSugarList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBloodSugarList(param);
	}

	/**
	 * 혈당 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodSugarChartList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBloodSugarChartList(param);
	}
	
	/**
	 * 혈당 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodSugarDtlsList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBloodSugarDtlsList(param);
	}

	/**
	 * 혈당 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodSugarDtlsChartList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBloodSugarDtlsChartList(param);
	}
	
	/**
	 * 혈압 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodPressList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBloodPressList(param);
	}

	/**
	 * 혈압 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodPressChartList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBloodPressChartList(param);
	}

	/**
	 * 혈압 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodPressDtlsList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBloodPressDtlsList(param);
	}

	/**
	 * 혈압 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodPressDtlsChartList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectBloodPressDtlsChartList(param);
	}
	
	/**
	 * 운동기록 등록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExeRecordList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectExeRecordList(param);
	}
	
	/**
	 * 운동기록 등록
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int exeRecordInsert(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.exeRecordInsert(param);
	}
	
	/**
	 * 운동기록 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int exeRecordUpdate(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.exeRecordUpdate(param);
	}
	
	/**
	 * 운동기록 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int exeRecordDelete(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.exeRecordDelete(param);
	}
	
	/**
	 * 운동 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExcsList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlMyhealthDAO.selectExcsList(param);
	}
	
	/**
	 * 영양소별 기준 섭취량 대비 비율 정보
	 */
	@Override
	public List<Map<String, String>> selectObjEatNeed(Map param) throws Exception{
		return gnrlMyhealthDAO.selectObjEatNeed(param);
	}
	
	/**
	 * 식사 일기 메인 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception  
	 */
	@Override
	public List<Map<String, String>> selectMealDiaryList(Map param) throws Exception{
		return gnrlMyhealthDAO.selectMealDiaryList(param);
	}
	
	/**
	 * 식사 일기 상세_유지당류 및 주류 섭취 칼로리 정보
	 */
	@Override
	public List<Map<String, String>> selectMealDiarySgCal(Map param) throws Exception{
		return gnrlMyhealthDAO.selectMealDiarySgCal(param);
	}
	
	/**
	 * 식사 일기 주차 및 날짜 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectMealDiaryWeekInfo(Map param) throws Exception{
		return gnrlMyhealthDAO.selectMealDiaryWeekInfo(param);
	}
	
	/**
	 * 식사 일기 상세 정보 조회
	 */
	@Override
	public List<Map<String, String>> selectMealDiaryDtlsList(Map param) throws Exception{
		return gnrlMyhealthDAO.selectMealDiaryDtlsList(param);
	}
	
	/**
	 * 음식 검색어 조회
	 */
	@Override
	public List<Map<String, String>> selectMealSearch(Map param) throws Exception{
		return gnrlMyhealthDAO.selectMealSearch(param);
	}
	
	/**
	 * 음식 칼로리 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectFoodSearch(Map param) throws Exception{
		return gnrlMyhealthDAO.selectFoodSearch(param);
	}
	
	/**
	 * 식사_일기 저장
	 */
	public int mealDiaryInsert(Map param) throws Exception{
		int mealDiaryCnt = 0;
		String popSel = (String) param.get("popSel");
		String popSelStr = popSel.replaceAll("&quot;", "\"");
		JSONParser pser = new JSONParser();
		Object obj = pser.parse(popSelStr);
		JSONArray jArr = (JSONArray) obj;
		
		try{
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			
	        for(int i=0;i<jArr.size();i++){
	        	paramMap.clear();
	        	
	        	JSONObject jobj = (JSONObject) jArr.get(i);
	        	String GB = (String) jobj.get("GB");
	        	
	        	paramMap.put("USER_ID", param.get("SESS_USER_ID"));
	        	paramMap.put("MEAL_DE", param.get("MEAL_DE"));
	        	paramMap.put("MEAL_TM", param.get("MEAL_TM"));
	        	paramMap.put("MEAL_CLF", param.get("MEAL_CLF"));
	        	paramMap.put("FOOD_CD", jobj.get("FOOD_CD"));
	        	paramMap.put("INTAKE_AM", Double.parseDouble(jobj.get("INTAKE_AM").toString()));
	        	paramMap.put("attchFileSn", "");
	        	paramMap.put("foodRecognYn", 'N');
	        	
	        	gnrlMyhealthDAO.insertMealDiary(paramMap);
	        	if("I".equals(GB)){
	        		List<Map<String,Object>> paramList = gnrlMyhealthDAO.selectInsertDtls(paramMap);
	        		paramMap.put("paramList", paramList);
	        		gnrlMyhealthDAO.insertMealDiaryDtls(paramMap);
	        		
	        		gnrlMyhealthDAO.insertMealDiary(paramMap);
	        	}else if("U".equals(GB)){
	        		gnrlMyhealthDAO.deleteMealDiaryDtls(paramMap);

	        		List<Map<String,Object>> paramList = gnrlMyhealthDAO.selectInsertDtls(paramMap);
	        		paramMap.put("paramList", paramList);
	        		gnrlMyhealthDAO.insertMealDiaryDtls(paramMap);
	        		
	        		gnrlMyhealthDAO.insertMealDiary(paramMap);
	        	}else if("D".equals(GB)){
	        		gnrlMyhealthDAO.deleteMealDiaryDtls(paramMap);
	        		gnrlMyhealthDAO.insertMealDiary(paramMap);
	        		gnrlMyhealthDAO.deleteMealDiary(paramMap);
	        	}
	        }
			mealDiaryCnt= 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		
        return mealDiaryCnt;
	}
	
	public int foodRecognInsert(Map param) throws Exception{		
		int mealDiaryCnt = 0;
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();				        
	        	paramMap.put("MEAL_DE", param.get("MEAL_DE"));
	        	paramMap.put("MEAL_CLF", param.get("MEAL_CLF"));
	        	paramMap.put("MEAL_TM", param.get("MEAL_TM"));
	        	paramMap.put("USER_ID", param.get("USER_ID"));
	        	paramMap.put("FOOD_CD", param.get("FOOD_CD"));
	        	paramMap.put("LST_DML_ID", param.get("LST_DML_ID"));
	        	paramMap.put("FOOD_CATE", param.get("FOOD_CATE"));
	        	paramMap.put("INTAKE_AM", param.get("INTAKE_AM"));
	        	paramMap.put("AMOUNT", param.get("AMOUNT"));
	        	paramMap.put("CAL", param.get("CAL"));
	        	paramMap.put("CARB", param.get("CARB"));
	        	paramMap.put("PROTEIN", param.get("PROTEIN"));
	        	paramMap.put("FAT", param.get("FAT"));
	        	paramMap.put("SUGARS", param.get("SUGARS"));
	        	paramMap.put("SALT", param.get("SALT"));
	        	paramMap.put("CHOLESTEROL", param.get("CHOLESTEROL"));
	        	paramMap.put("SATURATED", param.get("SATURATED"));
	        	paramMap.put("TRANS", param.get("TRANS"));
	        	paramMap.put("attchFileSn", param.get("ATTCH_FILE_SN"));	        	
	        	paramMap.put("foodRecognYn", param.get("FOOD_RECOGN_YN"));	        	
	      		List<Map<String,Object>> paramList = gnrlMyhealthDAO.selectInsertDtls(paramMap);
	      		paramMap.put("paramList", paramList);	      		
	      		String Index = param.get("INDEX").toString();	      		
	      		gnrlMyhealthDAO.insertMealDiaryDtls(paramMap);	      			      		
	      		if (Index==null || Index.equals("0"))  {
	      			gnrlMyhealthDAO.insertMealDiary(paramMap);	      			
	      		}
	        mealDiaryCnt= 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		
        return mealDiaryCnt;
	}

	@Override
	public List<Map<String, String>> selectLunchbox(Map param) throws Exception{
		return gnrlMyhealthDAO.selectLunchbox(param);
	}
	

}
