package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : PointRankingService.java
 * @Description : 관리자 WEB에서 사용하는 포인트 및 랭킹 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.11.28		이태석		최초생성
 * 
 * @author	gst
 * @since	2016.11.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface PointMngtService {	

	public List<Map<String, Object>> selectPointMngtList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> searchPointLogPop(Map<String, Object> param) throws Exception;
	
}
