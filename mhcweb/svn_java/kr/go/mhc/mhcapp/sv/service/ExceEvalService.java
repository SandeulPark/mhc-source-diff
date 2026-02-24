package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface ExceEvalService {

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
	public List<Map<String, String>> selectExceEvalList1_1(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동평가2 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList2(Map<String, Object> param) throws Exception;
	
	/**
	 * 서명 sn 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectSignLoad(Map<String, Object> param) throws Exception;
}
