package kr.go.mhc.mhcweb.cm.service;

import java.util.List;
import java.util.Map;


public interface ExcsCodeMngtService {

	/**
	 * 운동코드 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getExcsCodeList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 운동코드 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertExcsCode(Map<String, Object> param) throws Exception;

	
	/**
	 * 운동코드 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateExcsCode(Map<String, Object> param) throws Exception;	

	
	/**
	 * 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateExcsCodeApprovalYn(Map<String, Object> param) throws Exception;
	
}
