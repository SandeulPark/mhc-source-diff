package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : DeviceDistrbtController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.07		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
public interface IncentiveMngtService {

	public List<Map<String, Object>> selectIncentiveTarget(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> searchIncentiveLogPop(Map<String, Object> param) throws Exception;

	public int paymentPoint(Map<String, Object> param) throws Exception;

}
