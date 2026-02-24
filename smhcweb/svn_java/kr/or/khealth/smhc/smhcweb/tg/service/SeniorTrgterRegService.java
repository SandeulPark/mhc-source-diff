package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : HealthMngtCnslService.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 등록을 관리하는 서비스 interface
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
public interface SeniorTrgterRegService {
	
	public List<Map<String, Object>> selectSeniorTrgterList(Map<String, Object> param)throws Exception;

	public Map<String, Object> selectSeniorTrgterDtls(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> selectManagerCombo(Map<String, Object> param)throws Exception;

	public int insertSeniorTrgter(Map<String, Object> param)throws Exception;
	
	public int updateSeniorTrgter(Map<String, Object> param)throws Exception;	
	
	public Map<String, Object> selectSeniorDuplicationSch(Map<String, Object> param)throws Exception;	

	public Map<String, Object> selectSeniorDuplicationMobileNo(Map<String, Object> param)throws Exception;

	public int seniorTrgterDeleteInfo(Map<String, Object> param)throws Exception;	

	public boolean updateLoginFailCnt(Map<String, Object> param)throws Exception;
	
	public int insertUnlockHist(Map<String, Object> param)throws Exception;
}
