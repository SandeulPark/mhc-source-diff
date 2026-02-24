package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.SeniorHealthExamMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

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

@Service(value="web.tg.SeniorHealthExamMngtService")
public class SeniorHealthExamMngtServiceImpl extends EgovAbstractServiceImpl implements SeniorHealthExamMngtService{

	@Resource(name="web.tg.SeniorHealthExamMngtDAO")
	private SeniorHealthExamMngtDAO seniorHealthExamMngtDAO;

	@Override
	public int regHealthInfo(Map<String, Object> param) throws Exception {
		return seniorHealthExamMngtDAO.regHealthInfo(param);
	}

	@Override
	public Map<String, Object> mngtUserHealthChk(Map<String, Object> param) throws Exception {
		return seniorHealthExamMngtDAO.mngtUserHealthChk(param);
	}

	@Override
	public Map<String, Object> setUserHealthDetail(Map<String, Object> param) throws Exception {
		return seniorHealthExamMngtDAO.setUserHealthDetail(param);
	}

	@Override
	public int updateServiceManageHealth(Map<String, Object> param) throws Exception {
		return seniorHealthExamMngtDAO.updateServiceManageHealth(param);
	}
	
}
