package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : HealthExamMngtService.java
 * @Description : 관리자 WEB에서 사용하는 건강검진 관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.20		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface HealthExamMngtService {

	/**
	 * 건강검진관리 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getHealthExamList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getHealthExamMngtDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 계측정보저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateHealthExamBody(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 혈액검사정보저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateHealthExamBld(Map<String, Object> param) throws Exception;

	/**
	 * 건강검진관리 만성질환정보 저장
	 * @param param
	 * @throws Exception
	 */
	public void updateHealthExamChronic(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 인바디 정보 저장 검사일자 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
//	public Map<String, Object> getSelHealthExamDE(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 인바디 정보 저장 검사일자 리스트
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSelHealthExamDEList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 인바디 정보 체성분결과 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateHealthBodyComp(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 검사완료
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateHealthComplete(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 POCT
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertHealthExamDta(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectHealthMngtDetail(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진관리 저장여부 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectSaveYnCheck(Map<String, Object> param) throws Exception;
	
	//건강검진관리 삭제
	public void delHealthMngt(Map<String, Object> param) throws Exception;
	
	/**
	 * 체성분 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getHealthBodyComp(Map<String, Object> param) throws Exception;
	
	/**
	 * 주민등록번호 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateResNo(Map<String, Object> param) throws Exception;
	
	/**
	 * 생년월일 수정
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateBirth(Map<String, Object> param) throws Exception;
	
	/**
	 * 검진 생성 시 데이터 존재 여부 체크
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> healthExamCntChk(Map<String, Object> param) throws Exception;
	
	/**
	 * POCT 검진 완료 데이터 인입 여부 체크
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> poctSaveYnChk(Map<String, Object> param) throws Exception;

}