package kr.go.mhc.mhcapp.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : AppGeneralCnslService.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-일반상담 서비스 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.29		오명빈			최초생성
 *
 * @author gst
 * @since 2016.06.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface AppGeneralCnslService {
	
	/**
	 * 일반상담 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectGeneralCnslList(Map<String, Object> param) throws Exception;
	
	/**
	 * 일반상담 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCnslDetailList(Map<String, Object> param) throws Exception;
	
	/**
	 * 일반상담 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public void insertGeneralCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 일반상담 확인 유무 업데이트
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public void updateCnslCnfm(Map<String, Object> param) throws Exception;
	
}
