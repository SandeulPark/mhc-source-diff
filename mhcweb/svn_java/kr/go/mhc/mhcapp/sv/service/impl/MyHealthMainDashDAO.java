package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MyHealthMainDashDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-메인 대시 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * 		2016.07.01		오명빈				최초생성
 *
 * @author gst
 * @since 2016.07.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.sv.MyHealthMainDashDAO")
public class MyHealthMainDashDAO extends DMultiEgovAbstractMapper{

	/**
	 * 활동량 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsList", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 총걸음수, 총거리, 칼로리, 운동시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActTot(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActTot", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActChartList", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 상세 1일 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsChart", param);	
		return rsList;  
	}
	/**
	 * 활동량 상세 1일 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsStat1", param);	
		return rsList;  
	}
	/**
	 * 활동량 상세 1주일 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsStat2", param);	
		return rsList;  
	}
	/**
	 * 활동량 상세 1,3개월 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsStat3", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 상세 1주일 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsChart2", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 상세 1,3개월 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsChart3", param);	
		return rsList;  
	}
	
	/**
	 * 혈압 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressList", param);	
		return rsList;  
	}
	
	/**
	 * 혈압 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressChartList", param);	
		return rsList;  
	}
	
	/**
	 * 혈압 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressDtlsList", param);	
		return rsList;  
	}
	
	/**
	 * 혈압 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressDtlsChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressDtlsChartList", param);	
		return rsList;  
	}
	/**
	 * 혈당 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarDtlsList", param);	
		return rsList;  
	}
	
	/**
	 * 혈당 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarDtlsChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarDtlsChartList", param);	
		return rsList;  
	}
	
	/**
	 * 혈당 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarChartList", param);	
		return rsList;  
	}
	/**
	 * 혈당 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarList", param);	
		return rsList;  
	}
	
	/**
	 * 나의 건강 상세 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBodyCompChartList", param);	
		return rsList;  
	}
	
	/**
	 * 메인 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMainBodyCompChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMainBodyCompChartList", param);	
		return rsList;  
	}
	
	/**
	 * 체성분 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompDtlsChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBodyCompDtlsChartList", param);	
		return rsList;  
	}
	
	/**
	 * 체성분 상세 각 데이터 차이값 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBodyCompDtlsList", param);	
		return rsList;  
	}
	
	/**
	 * 운동기록 등록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExeRecordList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectExeRecordList", param);	
		return rsList;  
	}
	
	
	/**
	 * 운동기록 등록 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exeRecordInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.myhealth.exeRecordInsert", param);	
		return rsList;  
	}
	
	/**
	 * 운동기록 수정 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exeRecordUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.myhealth.exeRecordUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 운동기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exeRecordDelete(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = delete("mhcapp.sv.myhealth.exeRecordDelete", param);	
		return rsList;  
	}
	
	/**
	 * 식사기록 등록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDietRecordList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectDietRecordList", param);	
		return rsList;  
	}
	
	//-------------------------------------------------------------------------
	
	
	/**
	 * 메인 대시 혈압 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressData", param);	
		return rsList;  
	}
	
	/**
	 * 메인 대시 혈당 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarData", param);	
		return rsList;  
	}
	
	/**
	 * 운동/식사 기록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExerDietData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectExerDietData", param);	
		return rsList;  
	}
	
	/**
	 * 알림 도착 유무 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNoticeChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectNoticeChk", param);	
		return rsList;  
	}
	
	/**
	 * 식사 구분 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> exeRecordClfCheck(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.exeRecordClfCheck", param);	
		return rsList;  
	}
	
	/**
	 * 식사기록 등록 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int dietRecordInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.myhealth.dietRecordInsert", param);	
		return rsList;  
	}
	
	/**
	 * 식사 기록 수정 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int dietRecordUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.myhealth.dietRecordUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 식사 상담 추가 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int dietCnslUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.myhealth.dietCnslUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 식사 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMealDtls(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMealDtls", param);	
		return rsList;  
	}
	
	/**
	 * 식사 SN 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMealRegSn(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMealRegSn", param);	
		return rsList;  
	}
	
	/**
	 * 식사 상세 추가 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int mealDtlsInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.myhealth.mealDtlsInsert", param);	
		return rsList;  
	}
	
	/**
	 * 식사 기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int mealAllDelete(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = delete("mhcapp.sv.myhealth.mealAllDelete", param);	
		return rsList;  
	}
	
	/**
	 * 식사 마스터 유무 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> dietMasterCheck(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectDietMasterCheck", param);	
		return rsList;  
	}
	
	/**
	 * 미션 측정 횟수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMeasrCntList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMeasrCntList", param);	
		return rsList;  
	}
	
	/**
	 * 주차별 컨텐츠 상세정보
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectContentsDtls(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectContentsDtls", param);	
		return rsList;  
	}
	
	/**
	 * 주차별 컨텐츠 확인여부
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public String selectContentsCnfm(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		String rsList = selectOne("mhcapp.sv.myhealth.selectContentsCnfm", param);	
		return rsList;  
	}
}
