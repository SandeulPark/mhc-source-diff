package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : SeniorMeasrInfoService.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 측정정보 조회를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.19		
 *
 * @author thejoin
 * @since 2020.10.19		
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


public interface SeniorMissionRsltInfoService {

	
	/**
	 * 미션실천현황 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMissionRsltInfoList(Map<String, Object> param) throws Exception;

	/**
	 * 외출미션인증사진 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectPhotoRsltInfoList(Map<String, Object> param) throws Exception;
}
