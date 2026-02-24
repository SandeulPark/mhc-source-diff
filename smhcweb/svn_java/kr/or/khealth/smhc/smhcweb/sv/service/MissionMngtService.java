package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MissionMngtService.java
 * @Description : 관리자 WEB에서 사용하는 미션설정관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16			이은주			최초생성
 *
 * @author gst
 * @since 2020.09.16	
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface MissionMngtService {
	
	public List<Map<String, Object>> selectMissionMngtList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> setDeviceInfo(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> setMissionInfo(Map<String, Object> param) throws Exception;
	
	public int updMissionSet(Map<String, Object> param) throws Exception;
	
	public int updDrugInfo(Map<String, Object> param) throws Exception;
	
	public int updDrugMissionSet(Map<String, Object> param) throws Exception;
}
