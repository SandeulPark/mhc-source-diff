package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface ServeyService {
	/**
	 * 설문 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문 코드 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyCodeList(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문 마지막 코드 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyLstQnaCD(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문지 마스터 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyMasterInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문지 영양 체크리스트 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyAnswrUpdate(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문지 마스터 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int updateServeyMaster(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문지 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyAwrInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 설문 답변 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyAnwerList(Map<String, Object> param) throws Exception;

	/**
	 * 설문지 답변 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public void serveyAwrDel(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> serveyResearchAnswrList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> serveyResearchAnswrMastr(Map<String, Object> param) throws Exception;

	public int serveyResearchAnswrInsert(Map<String, Object> param) throws Exception;
}