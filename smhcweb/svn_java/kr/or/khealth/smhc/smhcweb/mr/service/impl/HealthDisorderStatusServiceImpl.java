package kr.or.khealth.smhc.smhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;








import kr.or.khealth.smhc.smhcweb.mr.service.HealthDisorderStatusService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :HealthDisorderInfoServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 어르신 건강 이상 정보 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2020.09.16		양현우		수정
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.mr.HealthDisorderStatusService")
public class HealthDisorderStatusServiceImpl extends EgovAbstractServiceImpl implements HealthDisorderStatusService {
	
	@Resource(name="web.mr.HealthDisorderStatusDAO")
	private HealthDisorderStatusDAO healthDisorderstatusDAO;

	@Override
	public List<Map<String, Object>> selectHealthDisorderStatusList(Map<String, Object> param) throws Exception {
		return healthDisorderstatusDAO.selectHealthDisorderStatusList(param);
	}
	
	@Override
	public void updateDisorderExamProc(Map<String, Object> param) throws Exception {
		healthDisorderstatusDAO.updateDisorderExamProc(param);
	}

	@Override
	public List<Map<String, Object>> selectDisorderStatusCount(Map<String, Object> param) throws Exception {
		return healthDisorderstatusDAO.selectDisorderStatusCount(param);
	}
	
	
}
