package kr.go.mhc.mhcweb.sample.service;

import java.util.List;
import java.util.Map;

public interface TestService {
	/**
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public List<Map<String, Object>> getList(Map<String, Object> param) throws Exception;

	/**
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 단일 ROW 상세 정보 
	 */
	public Map<String, Object> getDetail(Map<String, Object> param) throws Exception;
	
	/**
	 * 신규 데이타 입력
	 * @param param 입력값
	 * @return 신규 데이타 PK
	 */
	public Map<String, Object> insert(Map<String, Object> param) throws Exception;
	
	/**
	 * 기존 데이타 수정
	 * @param param 입력값
	 * @return 신규 데이타 PK
	 */
	public int update(Map<String, Object> param) throws Exception; 
	
	/**
	 * 기존 데이타 삭제
	 * @param param 입력값
	 * @return 신규 데이타 PK
	 */
	public int delete(Map<String, Object> param) throws Exception; 
}
