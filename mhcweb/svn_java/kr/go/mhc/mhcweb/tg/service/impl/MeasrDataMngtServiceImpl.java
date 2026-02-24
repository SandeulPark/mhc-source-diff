package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.tg.service.MeasrDataMngtService;

/**
 * @Class Name : MeasrDataMngtServiceImpl.java
 * @Description : 관리자 WEB의 시스템을 관리하는 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.02.20		이태석			최초생성
 
 * @author thejoin
 * @since 2017.02.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.tg.MeasrDataMngtService")
public class MeasrDataMngtServiceImpl implements MeasrDataMngtService{
	@Resource(name="web.tg.MeasrDataMngtDAO")
	private MeasrDataMngtDAO measrDataMngtDAO;
	
	@Override
	public List<Map<String, String>> getTrgterDuplicationChkList(Map<String, Object> param) throws Exception {
		return measrDataMngtDAO.getTrgterDuplicationChkList(param);
	}
	
	@Override
	public List<Map<String, String>> getTrgterBodyCompDataList(Map<String, Object> param) throws Exception {
		return measrDataMngtDAO.getTrgterBodyCompDataList(param);
	}
	
	@Override
	public List<Map<String, String>> getTrgterBloodSugarDataList(Map<String, Object> param) throws Exception {
		return measrDataMngtDAO.getTrgterBloodSugarDataList(param);
	}

	@Override
	public List<Map<String, String>> getBodyCompDataDel(Map<String, Object> param) throws Exception {
		return measrDataMngtDAO.getBodyCompDataDel(param);
	}

	@Override
	public List<Map<String, String>> getBloodSugarDataUp(Map<String, Object> param) throws Exception {
		return measrDataMngtDAO.getBloodSugarDataUp(param);
	}
}
