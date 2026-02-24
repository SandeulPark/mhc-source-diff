package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : SelfMissionMngtService.java
 * @Description : 관리자 WEB에서 사용하는 자체미션설정관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.11.09		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.11.09	
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SelfMissionMngtService {
	
	public List<Map<String, Object>> selectSelfMissionMngtList(Map<String, Object> param) throws Exception;
	
	public int insertSelfMissionMngt(Map<String, Object> param) throws Exception;
	
	public int updSelfMissionMngt(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectSelfMissionMngtCount(Map<String, Object> param) throws Exception;

}
