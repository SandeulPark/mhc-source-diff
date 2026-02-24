package kr.go.mhc.mhcweb.cm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MobileNoticeMngtService.java
 * @Description : 관리자 WEB에서 사용하는 모바일 공지관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.10		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface MobileNoticeMngtService {
	
	/**
	 * 모바일 공지 관리 목록 수 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	public int getMobileNoticeMngtListCount(Map<String, Object> param) throws Exception;
	
	/**
	 * 모바일 공지 관리 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getMobileNoticeMngtList(Map<String, Object> param) throws Exception;
	
	/**
	 * 모바일 신규 공지 저장 작성자 조회
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getMobileNoticeReg(Map<String, Object> param) throws Exception;
	
	/**
	 * 모바일 신규 공지 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void getMobileNoticeRegInsert(Map<String, Object> param) throws Exception;

	/**
	 * 모바일 공지 상세
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getMobileNoticeDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 이전글_다음글 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getBefAfMobileNoticeList(Map<String, Object> param) throws Exception;
	
	/**
	 * 모바일 공지 수정
	 * param 업데이트
	 * @param param 수정 정보
	 * @return
	 * @throws Exception
	 */
	public void getMobileNoticeMngtUp(Map<String, Object> param) throws Exception;
	
	/**
	 * 모바일 공지 삭제	
	 * @param param PK 정보
	 * @return
	 * @throws Exception
	 */
	public void getMobileNoticeMngtDel(Map<String, Object> param) throws Exception;
	
	/**
	 * 모바일 공지 게시/게시취소
	 * @param param PK 정보
	 * @return
	 * @throws Exception
	 */
	public void getMobileNoticeMngtPostUp(Map<String, Object> param) throws Exception;

	public Map<String, String> mobileNoticeMngtInsertNumber(Map param) throws Exception;
}
	