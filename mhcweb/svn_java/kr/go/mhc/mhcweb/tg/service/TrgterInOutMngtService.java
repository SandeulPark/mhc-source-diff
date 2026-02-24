package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : TrgterInOutMngtService.java
 * @Description : 관리자 WEB에서 사용하는 대상자 전입/전출 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.11.06		오샘이			최초생성
 *
 * @author thejoin
 * @since 2019.11.06
 * @version 1.0
 * @see
 *
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface TrgterInOutMngtService {
	/**
	 * 대상자 전입/전출 관리 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTrgterInOutMngtList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 전입 정보 검색
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTrgterInInfoChk(Map<String, Object> param) throws Exception;	
	
	/**
	 * 대상자 전입 요청 정보 신규 입력
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int insertTrgterInReqInfo(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 대상자 전입 요청 정보 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int updateTrgterInReqInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 전입 요청 승인 완료
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateTrgterInApprovalYn(Map<String, Object> param) throws Exception;
	
}
