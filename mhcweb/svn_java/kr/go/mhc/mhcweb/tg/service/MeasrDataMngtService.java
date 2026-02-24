package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MeasrDataMngtService.java
 * @Description : 관리자 WEB의 측정 데이터를 관리하는interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.02.20		이태석			최초생성
 *
 * @author thejoin
 * @since 2017.02.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface MeasrDataMngtService {

	public List<Map<String, String>> getTrgterDuplicationChkList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, String>> getTrgterBodyCompDataList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, String>> getTrgterBloodSugarDataList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, String>> getBodyCompDataDel(Map<String, Object> param)throws Exception;
	
	public List<Map<String, String>> getBloodSugarDataUp(Map<String, Object> param)throws Exception;
	
}
