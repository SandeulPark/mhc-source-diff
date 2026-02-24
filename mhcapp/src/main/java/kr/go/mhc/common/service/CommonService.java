package kr.go.mhc.common.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : CommonService.java
 * @Description : 모바일 헬스케어에서 사용하는 통합공통업무를 관리하는 서비스 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface CommonService {

	/**
	 * 공통코드 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmmnCd(Map<String, Object> param) throws Exception;
	
	/**
	 * 메뉴 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmmnMenu(Map<String, Object> param) throws Exception;
	
	/**
	 * 메뉴 조회
	 * @param codeId
	 * @return rtnUrl
	 * @throws Exception 
	 */
	public Map<String, String> selectCmmnMenuInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 첨부_파일_순번 조회
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public String selectAttchFileSnSeq() throws Exception;
	
	/**
	 * 첨부_파일_상세_순번 조회
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public String selectAttchFileDtlsSn(Map<String, Object> param) throws Exception;
	
	/**
	 * 첨부_파일 정보 저장
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public int insertAttchFile(List<Map<String,String>> fileList) throws Exception;
	
	/**
	 * 첨부_파일 정보 삭제
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public int deleteAttchFileInfo(Map<String, Object> param) throws Exception;

	/**
	 * 첨부_파일 사용 여부 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateAttchFileUseYn(Map<String, Object> param) throws Exception;

	/**
	 * 첨부_파일 정보 조회
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectAttchFile(Map<String, Object> param) throws Exception;
	
	/**
	 * 첨부_파일 정보 조회
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void insertCmmnLogInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 연동_로그_정보 저장
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void updatePairingLogInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 보건소 APP 오류파일 정보 저장
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void insertAppErrRport(Map<String,String> fileList) throws Exception;
	
	/**
	 * 보건소 APP 오류파일 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> selectAppErrRport(Map<String, String> param) throws Exception;

	/**
	 * 보건소 APP 오류파일 보편 사용자 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> selectAppErrRportGnUser(Map<String, String> param) throws Exception;
	
	/**
	 * 첨부_파일 정보 저장
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void insertAttchFile(Map<String,String> fileList) throws Exception;
	
}
