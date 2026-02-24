package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.MyHealthMainDashService;

import org.springframework.stereotype.Service;

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
	public List<Map<String, String>> selectMealRegSn(Map param) throws Exception {
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
	public String selectContentsCnfm(Map param) throws Exception {
		// TODO Auto-generated method stub
		return myhealthDAO.selectContentsCnfm(param);
	}

}
