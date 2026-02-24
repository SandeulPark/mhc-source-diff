package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface ExceEvalService {

	/**
	 * 평가 기간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPeriod(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList1(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가1-1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList1_1(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가1-3 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList1_3(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가2 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList2(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가2-1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList2_1(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가3 결과 칼로리, 운동시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList3(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가3 운동 기록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList3_1(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가3 일평균 칼로리 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList3_2(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가4 평가내용 및 결과 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList4(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가4 칼로리 운동 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList4_1(Map<String, Object> param) throws Exception;
	
	/**
	 * 서명 sn 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectSignLoad(Map<String, Object> param) throws Exception;

	/**
	 * 좋아요 update
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateEvalGood(Map<String, Object> param) throws Exception;
	
	/**
	 * 2017.03.03 이태석 추가(사진,동영상 보기)
	 * 운동평가5 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList5(Map<String, Object> param) throws Exception;
	
	/**
	 * 자동 발송 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectAutoSendYn(Map<String, Object> param) throws Exception;
	
	
}
