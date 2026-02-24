package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;




/**
 * @Class Name : SeniorHealthExamMngtController.java
 * @Description : 대면평가 정보 등록 - 생체정보 항목
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.29		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.09.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SeniorHealthExamMngtService {

	int regHealthInfo(Map<String, Object> param)throws Exception;

	Map<String, Object> mngtUserHealthChk(Map<String, Object> param)throws Exception;

	Map<String, Object> setUserHealthDetail(Map<String, Object> param)throws Exception;

	int updateServiceManageHealth(Map<String, Object> param)throws Exception;

	
}
