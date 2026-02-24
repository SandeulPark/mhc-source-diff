package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.MonthlyReportService;

import org.springframework.stereotype.Service;

@Service("mhcapp.sv.MonthlyReportService")
public class MonthlyReportServiceImpl implements MonthlyReportService {

	@Resource(name="mhcapp.sv.MonthlyReportDAO")
    private MonthlyReportDAO monthlyReportDAO;

	/**
	 * 월간리포트 카테고리 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectCategoryList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectCategoryList(param);
	}
	
	/**
	 * 리포트 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyReportList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyReportList(param);
	}
	
	/**
	 * 월간리포트 종합 체중변화 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectWeightChartList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectWeightChartList(param);
	}
	
	/**
	 * 월간리포트 종합 목표/현재 체중 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectWeightList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectWeightList(param);
	}
	
	/**
	 * 월간리포트 종합 월별 체성분 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyBodyCompChartList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyBodyCompChartList(param);
	}
	
	/**
	 * 월간리포트 종합 월별 체성분 각 비교값 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyWeightList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyWeightList(param);
	}
	
	/**
	 * 월간리포트 활동량 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyActChartList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyActChartList(param);
	}
	
	/**
	 * 월간리포트 총활동량 , 평균 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyActList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyActList(param);
	}
	
	/**
	 * 월간리포트 칼로리, 운동시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyKcalTime(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyKcalTime(param);
	}
	
	/**
	 * 월간리포트 총누적거리 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyDstc(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyDstc(param);
	}
	
	/**
	 * 월간리포트 혈압 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMonthlyBloodPressChartList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyBloodPressChartList(param);
	}
	
	/**
	 * 월간리포트 혈압 평균 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyBloodPressData(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyBloodPressData(param);
	}
	
	/**
	 * 월간리포트 혈당 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMonthlyBloodSugarChartList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyBloodSugarChartList(param);
	}
	
	/**
	 * 월간리포트 혈당 평균 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectMonthlyBloodSugarData(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monthlyReportDAO.selectMonthlyBloodSugarData(param);
	}
	
}
