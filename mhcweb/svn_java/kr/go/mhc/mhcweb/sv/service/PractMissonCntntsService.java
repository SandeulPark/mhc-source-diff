package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : PractMissonCntntsService.java
 * @Description : 관리자 WEB에서 실천미션 콘텐츠를 관리하는interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.04.04		이태석			최초생성
 *
 * @author thejoin
 * @since 2017.04.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface PractMissonCntntsService {

	public List<Map<String, Object>> getPractMissonCntntsList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, Object>> getPractMissonCont(Map<String, Object> param)throws Exception;
	
	public int updatePractMissonCont(Map<String, Object> param)throws Exception;
}
