package kr.or.khealth.smhc.smhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.mr.service.MeasrStatusService;
import kr.or.khealth.smhc.smhcweb.mr.service.MissionImplStatusService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 조회 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			수정
 
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.mr.MeasrStatusService")
public class MeasrStatusServiceImpl extends EgovAbstractServiceImpl implements MeasrStatusService{
	
	@Resource(name= "web.mr.MeasrStatusDAO")
	private MeasrStatusDAO measrStatusDAO;

	@Override
	public List<Map<String, Object>> selectMeasrStatusList(Map<String, Object> param) throws Exception {
		return measrStatusDAO.selectMeasrStatusList(param);
	}

	@Override
	public Map<String, Object> unmeasuredCount(Map<String, Object> param) throws Exception {
		return measrStatusDAO.unmeasuredCount(param);
	}

}
