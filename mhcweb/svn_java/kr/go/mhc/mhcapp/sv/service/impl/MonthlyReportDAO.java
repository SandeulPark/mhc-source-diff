package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


@Repository("mhcapp.sv.MonthlyReportDAO")
public class MonthlyReportDAO extends DMultiEgovAbstractMapper{

	/**
	 * 월간리포트 카테고리 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCategoryList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectCategoryList", param);	
		return rsList;  
	}
	/**
	 * 리포트 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyReportList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyReportList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 종합 체중변화 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectWeightChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectWeightChartList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 종합 목표/현재 체중 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectWeightList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectWeightList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 종합 월별 체성분 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBodyCompChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyBodyCompChartList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 종합 월별 체성분 각 비교값 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyWeightList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyWeightList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 활동량 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyActChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyActChartList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 총활동량, 평균 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyActList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyActList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 칼로리, 운동시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyKcalTime(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyKcalTime", param);	
		return rsList;  
	}
	/**
	 * 월간리포트 총누적 거리 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyDstc(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyDstc", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 혈압 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBloodPressChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyBloodPressChartList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 혈압 평균데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBloodPressData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyBloodPressData", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 혈당 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBloodSugarChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyBloodSugarChartList", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 혈당 평균데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBloodSugarData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.monthlyreport.selectMonthlyBloodSugarData", param);	
		return rsList;  
	}
}
