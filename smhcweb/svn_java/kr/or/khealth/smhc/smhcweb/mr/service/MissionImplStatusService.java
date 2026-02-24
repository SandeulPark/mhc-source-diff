package kr.or.khealth.smhc.smhcweb.mr.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : HealthMngtCnslService.java
 * @Description : 관리자 WEB에서 사용하는 어르신  미션실천현황을 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2020.09.16		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
public interface MissionImplStatusService {

	public List<Map<String, Object>> selectMissonTrgterList(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectMissonNotEnteredCount(Map<String, Object> param) throws Exception;
	
}
