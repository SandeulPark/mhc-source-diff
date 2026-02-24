package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : WeekSchMngtService..java
 * @Description : 관리자 WEB에서 사용하는 주차별 일정 관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.04.10		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.04.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface WeekSchMngtService {

	
	public List<Map<String, String>> selectWeekSchMngtList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectWeekSchMngtUserList(Map<String, Object> param) throws Exception;
	
	public Map<String, String> selectWeekSchMngtBeforeWeekInfo(Map<String, Object> param) throws Exception;	
	
	public List<Map<String, String>> selectWeekSchMngtAfterWeekList(Map<String, Object> param) throws Exception;
	
	public int saveWeekSchMngt(Map<String, Object> param) throws Exception;
	
}
