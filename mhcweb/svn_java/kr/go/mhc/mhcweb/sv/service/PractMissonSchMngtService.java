package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : PractMissonSchMngtService.java
 * @Description : 관리자 WEB에서 실천미션 일정을 관리하는interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.04.06		이태석			최초생성
 *
 * @author thejoin
 * @since 2017.04.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface PractMissonSchMngtService {

	public List<Map<String, Object>> getPractMissonSchList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> getPractMissonCdList(Map<String, Object> param) throws Exception;
	//추가
	public List<Map<String, Object>> getAllMissionCDList(Map<String, Object> param) throws Exception;
	//추가
	public int updatePublicHealthMissionDelete(Map<String, Object> param) throws Exception;
	//추가
	public int updatePublicHealthMissionUpdate(Map<String, Object> param) throws Exception;
	//추가 
	public Map<String,Object> insertPublicHealthMisson(Map<String,Object> param) throws Exception;
	//추가
	public List<Map<String, Object>> selectPublicMissionFile(Map<String,Object> param) throws Exception;
	
	public int getSelWeekTrgterChk(Map<String, Object> param) throws Exception;
	
	public int updatePractMissionSchCd(Map<String, Object> param) throws Exception;

	/* ################################################################################# */
	/* ######################### 만성질환 실천미션 추가 202304 ######################### */
	public List<Map<String, Object>> getPractMissonChronicSchList(Map<String, Object> param) throws Exception;
	public List<Map<String, Object>> getPractMissonCdChronicList(Map<String, Object> param) throws Exception;
	public List<Map<String, Object>> getAllMissionCDChronicList(Map<String, Object> param) throws Exception;
	public Map<String,Object> insertPublicHealthMissonChronic(Map<String,Object> param) throws Exception;
	public int updatePublicHealthMissionChronicUpdate(Map<String, Object> param) throws Exception;
	public int updatePractMissionChronicSchCd(Map<String, Object> param) throws Exception;
}
