package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : PhisMonitoringService.java
 * @Description : 관리자 WEB에서 사용하는 phis 연계 모니터링 Service Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.06.14		윤찬호			최초생성
 *
 * @author chyoon
 * @since 2021.06.14
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface PhisMonitoringService {

	List<Map<String, String>> selectPhisInterfaceList(Map<String, Object> param);

	List<Map<String, String>> selectPhisActList(Map<String, Object> param);

	List<Map<String, String>> selectPhisBodyList(Map<String, Object> param);

	List<Map<String, String>> selectPhisBloodpressList(Map<String, Object> param);

	List<Map<String, String>> selectPhisBloodsugarList(Map<String, Object> param);

}
