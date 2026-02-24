package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : VisitSchResrvtMngtService.java
 * @Description : 관리자 WEB에서 방문 예약 일정을 관리하는interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.06.12		오샘이			최초생성
 *
 * @author thejoin
 * @since 2019.06.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface VisitSchResrvtMngtService {

	public List<Map<String, Object>> getVisitSchResrvtMonthList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getVisitSchResrvtDayList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getVisitSchResrvtTimeList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getBundleSetTimeList(Map<String, Object> param)throws Exception;	

	public List<Map<String, Object>> getBundleSetPlaceList(Map<String, Object> param)throws Exception;

	public int saveVisitSchBuldleInfo(Map<String, Object> param)throws Exception;	

	public List<Map<String, Object>> getVisitResrvtTrgterAddList(Map<String, Object> param)throws Exception;	
	
	public void saveVisitSchResrvtCancel(Map<String, Object> param)throws Exception;	
	
	public int saveVisitSchSetInfo(Map<String, Object> param)throws Exception;

	public int saveVisitSchDtls(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getVisitSchResrvtExcel(Map<String, Object> param)throws Exception;

	public int saveVisitResrvtTrgter(Map<String, Object> param)throws Exception;		
	
	public int insertVisitResrvtTrgter(Map<String, Object> param)throws Exception;		

	public List<Map<String, Object>> getVisitDeTmList(Map<String, Object> param)throws Exception;
	
	public void saveVisitSchResrvtChange(Map<String, Object> param)throws Exception;	
	
	public List<Map<String, Object>> getVisitSchResrvtChangeChk(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getVisitMidWeekList()throws Exception;
	
	public Map<String, String> selectMyWeek(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectVisitResrvtDeTm(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> getVisitPlace(Map<String, Object> param) throws Exception;

	public Map<String, Object> getNewVisitPlaceSn(Map<String, Object> param) throws Exception;

	public int saveVisitPlace(Map<String, Object> param)throws Exception;

	public int deleteVisitPlace(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> selectResrvtVisitPlace(Map<String, Object> param)throws Exception;

	public Map<String, Object> selectResrvtUserCount(Map<String, Object> param)throws Exception;

	public Map<String, Object> selectResrvtCountByVisitDe(Map<String, Object> param) throws Exception;
}
