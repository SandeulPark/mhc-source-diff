package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorHealthExamMngtService;

import org.springframework.stereotype.Repository;

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



@Repository("web.tg.SeniorHealthExamMngtDAO")
public class SeniorHealthExamMngtDAO extends DMultiEgovAbstractMapper {

	public int regHealthInfo(Map<String, Object> param) {
		int rsInt = update("smhc.web.tg.seniorhealthexammngt.regHealthInfo", param);
		return rsInt;
	}

	public Map<String, Object> mngtUserHealthChk(Map<String, Object> param) {
		return selectOne("smhc.web.tg.seniorhealthexammngt.mngtUserHealthChk",param);
	}

	public Map<String, Object> setUserHealthDetail(Map<String, Object> param) {
		return selectOne("smhc.web.tg.seniorhealthexammngt.setUserHealthDetail",param);
	}

	public int updateServiceManageHealth(Map<String, Object> param) {
		return update("smhc.web.tg.seniorhealthexammngt.updateServiceManageHealth", param);
	}

}
