package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : HealthMngtCnslService.java
 * @Description : 관리자 WEB에서 사용하는  어르신 대면평가정보등록을 관리하는 서비스 interface
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
public interface FaceToFaceInfoRegService {

	public List<Map<String, Object>> selectFaceToFaceInfoList(Map<String, Object> param) throws Exception;	
	
	public Map<String, Object> selectFaceToFaceSeniorDtls(Map<String, Object> param)throws Exception;
}
