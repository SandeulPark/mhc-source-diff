package kr.or.khealth.smhc.smhcweb.tb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tb.service.TabletMainDashService;

import org.springframework.stereotype.Service;

/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 TABLET에서 사용하는 어르신 건강  업무 필요한 DAO와 연동 관리하는 Class
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

@Service(value="web.tb.TabletMainDashService")
public class TabletMainDashServiceImpl implements TabletMainDashService{
	
	@Resource(name= "web.tb.TabletMainDashDAO")
	private TabletMainDashDAO tabletMainDashDAO;

	//태블릿 메인 화면 대상자 수 조회
	@Override
	public Map<String, Object> selectTrgterTodayTotalCount(Map<String, Object> param) throws Exception {
		return tabletMainDashDAO.selectTrgterTodayTotalCount(param);
	}

	@Override
	public Map<String, Object> selectTrgterTodayIngCount(Map<String, Object> param) throws Exception {
		return tabletMainDashDAO.selectTrgterTodayIngCount(param);
	}

	@Override
	public Map<String, Object> selectTrgterTodayEndCount(Map<String, Object> param) throws Exception {
		return tabletMainDashDAO.selectTrgterTodayEndCount(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterList(Map<String, Object> param) throws Exception {
		return tabletMainDashDAO.selectTrgterList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectAllTrgterList(Map<String, Object> param) throws Exception {
		return tabletMainDashDAO.selectAllTrgterList(param);
	}	

}
