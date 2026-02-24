package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface NutEvalService {

	/**
	 * 영양평가1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalList1(Map<String, Object> param) throws Exception;
	
	/**
	 * 영양평가1 사진 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalAttchFileList(Map<String, Object> param) throws Exception;
	/**
	 * 영양평가2 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalList2(Map<String, Object> param) throws Exception;
	/**
	 * 영양평가3 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalList3(Map<String, Object> param) throws Exception;
	/**
	 * 2017.03.06 이태석 추가(사진,동영상 보기)
	 * 영양평가4 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalList4(Map<String, Object> param) throws Exception;
	
	/**
	 * 평가기간조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectEvalPeriod(Map<String, Object> param) throws Exception;
	
	/**
	 * 끼니별 칼로리 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealCalList(Map<String, Object> param) throws Exception;
	
	/**
	 * 요일별, 끼니별 칼로리 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectWeekMealCalList(Map<String, Object> param) throws Exception;
	
	/**
	 * 자동 발송 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectAutoSendYn(Map<String, Object> param) throws Exception;
	
}
