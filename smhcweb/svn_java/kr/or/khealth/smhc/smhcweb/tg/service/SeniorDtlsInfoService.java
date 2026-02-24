package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : SeniorDtlsInfoController.java
 * @Description : 대면평가 등록 - 대상자 상세 정보
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.29		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.09.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
public interface SeniorDtlsInfoService {

	public int regUserDetail(Map<String, Object> param)throws Exception;

	public int updateServiceManageDetail(Map<String, Object> param) throws Exception;
	
	public int insertUserFormInfo(Map<String, Object> param) throws Exception;	

	public Map<String, Object> mngtUserInfoDetailChk(Map<String, Object> param) throws Exception;

	public Map<String, Object> setUserInfoDetail(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectUserInfoDetail(Map<String, Object> param) throws Exception;

	public Map<String, Object> setMultipleTimes(Map<String, Object> param);
	
}
