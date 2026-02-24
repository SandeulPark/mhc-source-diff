package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.HealthDisorderInfoService;
import kr.go.mhc.mhcweb.mr.service.impl.HealthDisorderInfoDAO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :HealthDisorderInfoServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 건강 이상 정보 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.09.19		이현규		최초생성
 *
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.mr.HealthDisorderInfoService")
public class HealthDisorderInfoServiceImpl extends EgovAbstractServiceImpl implements HealthDisorderInfoService {
	
	@Resource(name="web.mr.HealthDisorderInfoDAO")
	private HealthDisorderInfoDAO healthDisorderInfoDAO;
	
	@Override
	public List<Map<String, String>> selectHealthDisorderInfoList(Map<String, Object> param) throws Exception {
		return healthDisorderInfoDAO.selectHealthDisorderInfoList(param);
	}
}
