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
	 * 서명 sn 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectSignLoad(Map<String, Object> param) throws Exception;
	/**
	 * 영양평가 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int updateNutEvalLike(Map<String, Object> param) throws Exception;
}
