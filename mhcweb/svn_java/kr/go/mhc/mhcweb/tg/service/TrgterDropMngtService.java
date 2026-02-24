package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : TrgterDropMngtService.java
 * @Description : 관리자 WEB에서 사용하는 대상자탈락관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.07.12		이태석			최초생성
 *
 * @author thejoin
 * @since 2018.07.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface TrgterDropMngtService {
	/**
	 * 대상자정보관리 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> trgterDropMngtList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자정보관리 중도탈락 업데이트
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateTrgterDrop(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자정보관리 중도탈락 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectTrgterDrop(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자정보관리 중도탈락 취소
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> cancelTrgterDrop(Map<String, Object> param) throws Exception;
}
