package kr.or.khealth.smhc.smhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : HealthDisorderInfoService.java
 * @Description : 관리자 WEB에서 사용하는 어르신 건강 이상 정보 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2020.09.16		양현우		수정
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface HealthDisorderStatusService {

	public List<Map<String, Object>> selectHealthDisorderStatusList(Map<String, Object> param)throws Exception;
	
	public void updateDisorderExamProc(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> selectDisorderStatusCount(Map<String, Object> param) throws Exception;
	
}
