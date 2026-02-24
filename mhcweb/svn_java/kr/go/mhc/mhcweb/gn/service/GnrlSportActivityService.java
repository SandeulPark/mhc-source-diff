package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : SportActivityService.java
 * @Description : 관리자 WEB에서 사용하는 스포츠 활동 인증 현황을 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2021.12.09		chyoon		최초생성
 * 
 * @author	chyoon
 * @since	2021.12.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlSportActivityService {

	public List<Map<String, Object>> getSportActivityUserList(Map<String, Object> param) throws Exception;

	public Map<String, Object> getSportActivityCertMsg(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> userInfo(String param) throws Exception;

}
