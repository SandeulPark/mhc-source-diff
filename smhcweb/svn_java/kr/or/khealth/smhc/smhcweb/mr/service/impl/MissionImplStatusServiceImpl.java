package kr.or.khealth.smhc.smhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.mr.service.MissionImplStatusService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			최초생성
 
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.mr.MissionImplStatusService")
public class MissionImplStatusServiceImpl extends EgovAbstractServiceImpl implements MissionImplStatusService{
	
	@Resource(name="web.mr.MissionImplStatusDAO")
	private MissionImplStatusDAO missionImplStatusDAO;
	
	@Override
	public List<Map<String, Object>> selectMissonTrgterList(Map<String, Object> param) throws Exception {
		return missionImplStatusDAO.selectMissonTrgterList(param);
	}

	@Override
	public Map<String, Object> selectMissonNotEnteredCount(Map<String, Object> param) throws Exception {
		return missionImplStatusDAO.selectMissonNotEnteredCount(param);
	}

}
