package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sv.service.WeekSchMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :WeekSchMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 주차별 일정관리에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.04.10		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.04.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sv.WeekSchMngtService")
public class WeekSchMngtServiceImpl extends EgovAbstractServiceImpl implements WeekSchMngtService{
	
	@Resource(name="web.sv.WeekSchMngtDAO")
	private WeekSchMngtDAO weekSchMngtDAO;

	@Override
	public List<Map<String, String>> selectWeekSchMngtList(Map<String, Object> param) throws Exception{
		return weekSchMngtDAO.selectWeekSchMngtList(param);
	}
	
	@Override
	public Map<String, String> selectWeekSchMngtBeforeWeekInfo(Map<String, Object> param) throws Exception{
		return weekSchMngtDAO.selectWeekSchMngtBeforeWeekInfo(param);
	}	
	
	@Override
	public List<Map<String, String>> selectWeekSchMngtAfterWeekList(Map<String, Object> param) throws Exception{
		return weekSchMngtDAO.selectWeekSchMngtAfterWeekList(param);
	}	

	@Override
	public List<Map<String, String>> selectWeekSchMngtUserList(Map<String, Object> param) throws Exception{
		return weekSchMngtDAO.selectWeekSchMngtUserList(param);
	}
	
	@Override
	public int saveWeekSchMngt(Map<String, Object> param) throws Exception{
		return weekSchMngtDAO.saveWeekSchMngt(param);
	}
	
}
