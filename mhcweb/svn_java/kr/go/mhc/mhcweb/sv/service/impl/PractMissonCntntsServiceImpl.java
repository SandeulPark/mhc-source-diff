package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.sv.service.PractMissonCntntsService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : PractMissonCntntsServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 실천미션 콘텐츠를 관리하는 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.04.04		이태석			최초생성
 *
 * @author thejoin
 * @since 2017.04.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sv.PractMissonCntntsService")
public class PractMissonCntntsServiceImpl extends EgovAbstractServiceImpl implements PractMissonCntntsService{

	@Resource(name = "web.sv.PractMissonCntntsServiceDAO")
	private PractMissonCntntsServiceDAO practMissonCntntsServiceDAO;

	@Override
	public List<Map<String, Object>> getPractMissonCntntsList(Map<String, Object> param) throws Exception {
		return practMissonCntntsServiceDAO.getPractMissonCntntsList(param);
	}

	@Override
	public List<Map<String, Object>> getPractMissonCont(Map<String, Object> param)	throws Exception {
		return practMissonCntntsServiceDAO.getPractMissonCont(param);
	}

	@Override
	public int updatePractMissonCont(Map<String, Object> param)	throws Exception {
		return practMissonCntntsServiceDAO.updatePractMissonCont(param);
	}
}
