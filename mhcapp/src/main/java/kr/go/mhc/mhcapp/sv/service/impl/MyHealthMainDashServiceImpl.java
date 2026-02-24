package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.MyHealthMainDashService;

import org.springframework.stereotype.Service;

import com.extrus.common.json.simple.JSONArray;
import com.extrus.common.json.simple.JSONObject;
import com.extrus.common.json.simple.parser.JSONParser;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : ActDTLSServiceImpl.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-활동량에서 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.01		오명빈			최초생성
 *
 * @author gst
 * @since 2016.07.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.sv.MyHealthMainDashService")
public class MyHealthMainDashServiceImpl extends EgovAbstractServiceImpl implements MyHealthMainDashService{
	
	@Resource(name="mhcapp.sv.MyHealthMainDashDAO")
    private MyHealthMainDashDAO myhealthDAO;

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
		return myhealthDAO.selectActDtlsList(param);
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
		return myhealthDAO.selectActTot(param);
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
		return myhealthDAO.selectActChartList(param);
	}
	
	/**
	 * 활동량 상세 1일 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActDtlsChart(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectActDtlsChart(param);
	}
	
	/**
	 * 활동량 상세 1일 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActDtlsStat1(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectActDtlsStat1(param);
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
		return myhealthDAO.selectActDtlsStat2(param);
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
		return myhealthDAO.selectActDtlsStat3(param);
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
		return myhealthDAO.selectActDtlsChart2(param);
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
		return myhealthDAO.selectActDtlsChart3(param);
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
		return myhealthDAO.selectBloodPressList(param);
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
		return myhealthDAO.selectBloodPressChartList(param);
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
		return myhealthDAO.selectBloodPressDtlsList(param);
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
		return myhealthDAO.selectBloodPressDtlsChartList(param);
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
		return myhealthDAO.selectBloodSugarList(param);
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
		return myhealthDAO.selectBloodSugarChartList(param);
	}
	
	/**
	 * 혈압 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodSugarDtlsList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectBloodSugarDtlsList(param);
	}

	/**
	 * 혈압 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodSugarDtlsChartList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectBloodSugarDtlsChartList(param);
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
		return myhealthDAO.selectBodyCompChartList(param);
	}
	
	/**
	 * 메인 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMainBodyCompChartList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectMainBodyCompChartList(param);
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
		return myhealthDAO.selectBodyCompDtlsChartList(param);
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
		return myhealthDAO.selectBodyCompDtlsList(param);
	}
	
	/**
	 * 체성분 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectBodycompList(Map param) throws Exception{
		// TODO Auto-generated method stub
		return myhealthDAO.selectBodycompList(param);
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
		return myhealthDAO.selectExeRecordList(param);
	}
	
	/**
	 * 식사 기록 등록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectDietRecordList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectDietRecordList(param);
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
		return myhealthDAO.exeRecordInsert(param);
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
		return myhealthDAO.exeRecordUpdate(param);
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
		return myhealthDAO.exeRecordDelete(param);
	}
	
	/**
	 * 메인 대시 혈압 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodPressData(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectBloodPressData(param);
	}
	
	/**
	 * 메인 대시 혈당 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectBloodSugarData(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectBloodSugarData(param);
	}
	
	/**
	 * 운동/식사 기록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExerDietData(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectExerDietData(param);
	}
	
	/**
	 * 알림 도착 유무 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNoticeChk(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectNoticeChk(param);
	}
	
	/**
	 * 식사 구분 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> exeRecordClfCheck(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.exeRecordClfCheck(param);
	}
	
	/**
	 * 식사기록 등록
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int dietRecordInsert(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.dietRecordInsert(param);
	}
	
	/**
	 * 식사기록 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int dietRecordUpdate(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.dietRecordUpdate(param);
	}
	
	/**
	 * 식사상담 추가
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int dietCnslUpdate(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.dietCnslUpdate(param);
	}
	

	/**
	 * 식사 구분 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMealDtls(Map param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectMealDtls(param);
	}
	
	/**
	 * 식사 SN 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int selectMealRegSn(Map param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectMealRegSn(param);
	}
	
	/**
	 * 식사 상세 입력
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int mealDtlsInsert(Map param) throws Exception {
		// TODO Auto-generated method stub
		String[] meal_name=((String)param.get("DIET_NAME")).split("\\^");
		String[] meal_quan=((String)param.get("DIET_QUNTT")).split("\\^");
		String[] meal_ing=((String)param.get("DIET_CONT")).split("\\^",-1);
		for(int i=0;i<meal_name.length;i++){
			param.put("DIET_NAME", meal_name[i]);
			param.put("DIET_QUNTT", meal_quan[i]);
			param.put("DIET_CONT", meal_ing[i]);
			myhealthDAO.mealDtlsInsert(param);
		}
		return 1;
	}
	
	/**
	 * 식사 기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int mealAllDelete(Map param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.mealAllDelete(param);
	}
	
	/**
	 * 식사 마스터 유무 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> dietMasterCheck(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.dietMasterCheck(param);
	}
	
	/**
	 * 미션 측정 횟수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMeasrCntList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectMeasrCntList(param);
	}
	
	/**
	 * 활동량 주간 미션 측정 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMeasrActList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectMeasrActList(param);
	}
	
	/**
	 * 주차별 컨텐츠 상세정보
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectContentsDtls(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectContentsDtls(param);
	}
	
	/**
	 * 주차별 컨텐츠 확인여부
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectContentsCnfm(Map param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectContentsCnfm(param);
	}
	
	/**
	 * 선물받기체크
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectGiftChk(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectGiftChk(param);
	}
	
	/**
	 * 식사 상담기간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectDietPeriod(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectDietPeriod(param);
	}
	
	/**
	 * 집중상담 알림내역 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNoticeListChk(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectNoticeListChk(param);
	}
	
	/**
	 * 집중상담 기간 체크
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectDietPrdChk(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectDietPrdChk(param);
	}

	/**
	 * 모바일 공지사항 체크
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectMobileNotice(Map param) throws Exception {
		return myhealthDAO.selectMobileNotice(param);
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
		return myhealthDAO.selectExcsList(param);
	}
	
	/**
	 * 식사 일기 메인 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception  
	 */
	@Override
	public List<Map<String, String>> selectMealDiaryList(Map param) throws Exception{
		return myhealthDAO.selectMealDiaryList(param);
	}
	
	/**
	 * 식사 일기 주차 및 날짜 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectMealDiaryWeekInfo(Map param) throws Exception{
		return myhealthDAO.selectMealDiaryWeekInfo(param);
	}
	
	/**
	 * 섭취군별 기준 섭취량 대비 비율 정보
	 */
	@Override
	public List<Map<String,String>> selectObjEatDay(Map param) throws Exception{
		return myhealthDAO.selectObjEatDay(param);
	}

	/**
	 * 영양소별 기준 섭취량 대비 비율 정보
	 */
	@Override
	public List<Map<String, String>> selectObjEatNeed(Map param) throws Exception{
		return myhealthDAO.selectObjEatNeed(param);
	}
	
	/**
	 * 식사 일기 상세_유지당류 및 주류 섭취 칼로리 정보
	 */
	@Override
	public List<Map<String, String>> selectMealDiarySgCal(Map param) throws Exception{
		return myhealthDAO.selectMealDiarySgCal(param);
	}
	/**
	 * 식사 일기 상세 정보 조회
	 */
	@Override
	public List<Map<String, String>> selectMealDiaryDtlsList(Map param) throws Exception{
		return myhealthDAO.selectMealDiaryDtlsList(param);
	}
	
	/**
	 * 음식 검색어 조회
	 */
	@Override
	public List<Map<String, String>> selectMealSearch(Map param) throws Exception{
		return myhealthDAO.selectMealSearch(param);
	}
	
	/**
	 * 음식 칼로리 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectFoodSearch(Map param) throws Exception{
		return myhealthDAO.selectFoodSearch(param);
	}
	
	/**
	 * 실천_미션_정보
	 */
	public List<Map<String, String>> selectPractMission(Map param) throws Exception{
		return myhealthDAO.selectPractMission(param);
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

				myhealthDAO.insertMealDiary(paramMap);
	        	// 직접입력
	        	if(paramMap.get("FOOD_CD").toString().indexOf("0")==0) {
	        		paramMap.put("FOOD_NM", jobj.get("FOOD_NM"));
	        		paramMap.put("FOOD_CATE", jobj.get("FOOD_CATE"));
	        		paramMap.put("INTAKE_AM", Double.parseDouble(jobj.get("INTAKE_AM").toString()));
					paramMap.put("INTAKE_AM_CLF", jobj.get("INTAKE_AM_CLF"));
	        		paramMap.put("AMOUNT", Double.parseDouble(jobj.get("AMOUNT").toString()));
	        		paramMap.put("CAL", Double.parseDouble(jobj.get("CAL").toString()));
					paramMap.put("MEAL_TM", param.get("MEAL_TM"));
	        		paramMap.put("CARB", Double.parseDouble(jobj.get("CARB").toString()));
	        		paramMap.put("PROTEIN", Double.parseDouble(jobj.get("PROTEIN").toString()));
	        		paramMap.put("CALCIUM", Double.parseDouble(jobj.get("CALCIUM").toString()));
	        		paramMap.put("SALT", Double.parseDouble(jobj.get("SALT").toString()));
	        		paramMap.put("CHOLESTEROL", Double.parseDouble(jobj.get("CHOLESTEROL").toString()));
	        		paramMap.put("SATURATED", Double.parseDouble(jobj.get("SATURATED").toString()));
	        		paramMap.put("TRANS", Double.parseDouble(jobj.get("TRANS").toString()));
	        		paramMap.put("FAT", Double.parseDouble(jobj.get("FAT").toString()));
	        		paramMap.put("SUGARS", Double.parseDouble(jobj.get("SUGARS").toString()));
					paramMap.put("MNAL_DIV", "M");
	        		if("D".equals(GB)) {
	        			myhealthDAO.deleteMealDiaryManual(paramMap);
						myhealthDAO.insertMealDiary(paramMap);
						myhealthDAO.deleteMealDiary(paramMap);
	        		}else {
	        			myhealthDAO.insertMealDiaryManual(paramMap);
						myhealthDAO.insertMealDiary(paramMap);
	        		}
	        		continue;
	        	}
	        	else if("I".equals(GB)){
	        		List<Map<String,Object>> paramList = myhealthDAO.selectInsertDtls(paramMap);
	        		paramMap.put("paramList", paramList);
	        		myhealthDAO.insertMealDiaryDtls(paramMap);
	        		
	        		myhealthDAO.insertMealDiary(paramMap);
	        	}else if("U".equals(GB)){
	        		myhealthDAO.deleteMealDiaryDtls(paramMap);

	        		List<Map<String,Object>> paramList = myhealthDAO.selectInsertDtls(paramMap);
	        		paramMap.put("paramList", paramList);
	        		myhealthDAO.insertMealDiaryDtls(paramMap);
	        		
	        		myhealthDAO.insertMealDiary(paramMap);
	        	}else if("D".equals(GB)){
	        		myhealthDAO.deleteMealDiaryDtls(paramMap);
	        		myhealthDAO.insertMealDiary(paramMap);
	        		myhealthDAO.deleteMealDiary(paramMap);
	        	}
	        }
			mealDiaryCnt= 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		
        return mealDiaryCnt;
	}
	
	/**
	 * 적정 탄단지 비율 조회
	 */
	public Map<String, Object> selectStndRecomPer(Map param) throws Exception{
		return myhealthDAO.selectStndRecomPer(param);
	}


	/**
	 * 실천 미션 스케쥴 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectPractMissionSch(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectPractMissionSch(param);
	}
	
	/**
	 * 실천 미션 답변 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectPractMissionAnswr(Map<String, String> param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectPractMissionAnswr(param);
	}
	
	/**
	 * 실천 미션 첨부파일 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectPractMissionAttchFile(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectPractMissionAttchFile(param);
	}

	/**
	 * 실천 미션 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int savePractMissionAnswr(Map<String, Object> param) 
			throws Exception{
		// TODO Auto-generated method stub
		return myhealthDAO.savePractMissionAnswr(param);
	}
	
	/**
	 * 대시보드 심박수 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String,Object> selectMainHeartRateChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectMainHeartRateChartList(param);
	}
	
	/**
	 * 심박수 그래프 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> heartRateDtlsChart(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.heartRateDtlsChart(param);
	}
	
	/**
	 * 운동모드 마지막 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExcsModeLastData(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectExcsModeLastData(param);
	}

	/**
	 * 심박 강도별 범위 및 데이터 개수 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectHeartRateStats(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectHeartRateStats(param);
	}
	
	/**
	 * 체성분 상담 값 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectBodyCompCnslList(Map param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectBodyCompCnslList(param);
	}
	
	/**
	 * 운동스케줄 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> exerSchList(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.exerSchList(param);
	}
	
	/**
	 * 운동스케줄 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int exerSchInsert(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.exerSchInsert(param);
	}
	
	/**
	 * 운동스케줄 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int exeSchUpdate(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.exeSchUpdate(param);
	}
	
	/**
	 * 운동스케줄 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int exeSchDelete(Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.exeSchDelete(param);
	}
	
	/**
	 * 복약미션 답변 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int saveDrugMissionAnswr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.saveDrugMissionAnswr(param);
	}
	
	/**
	 * 복약 미션 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectDrugList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectDrugList(param);
	}
	
		
}
