package kr.go.mhc.mhcweb.pm.service.impl;

import kr.go.mhc.mhcweb.pm.service.TrgterProcSttusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @Class Name :TrgterProcSttusServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대상자 진행현황 실적관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.pm.TrgterProcSttusService")
public class TrgterProcSttusServiceImpl implements TrgterProcSttusService {

	@Resource(name= "web.pm.TrgterProcSttusDAO")
	private TrgterProcSttusDAO trgterProcSttusDAO;

	@Override
	public List<Map<String, Object>> selectTrgterProcSttusList(Map<String, Object> param) throws Exception {
		return trgterProcSttusDAO.selectTrgterProcSttusList(param);
	}

	@Override
	public void trgterProcSttusAfterSchedule(Map<String, Object> param) throws Exception {
		trgterProcSttusDAO.trgterProcSttusAfterSchedule(param);
	}

	@Override
	public int updateTrgterProcSttus(Map<String, Object> param) throws Exception {
		return trgterProcSttusDAO.updateTrgterProcSttus(param);
	}
}
