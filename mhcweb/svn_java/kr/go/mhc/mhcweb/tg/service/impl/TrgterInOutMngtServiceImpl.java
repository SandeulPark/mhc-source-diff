package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.TrgterInOutMngtService;

import org.springframework.stereotype.Service;

/**
 * @Class Name : TrgterInOutMngtService.java
 * @Description : 관리자 WEB에서 사용하는 대상자 전입/전출 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.11.06		오샘이			최초생성
 *
 * @author thejoin
 * @since 2019.11.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.TrgterInOutMngtService")
public class TrgterInOutMngtServiceImpl implements TrgterInOutMngtService{

	@Resource(name= "web.tg.TrgterInOutMngtDAO")
	private TrgterInOutMngtDAO trgterInOutMngtDAO;

	@Override
	public List<Map<String, Object>> getTrgterInOutMngtList(Map<String, Object> param) throws Exception {
		return trgterInOutMngtDAO.getTrgterInOutMngtList(param);
	}

	@Override
	public Map<String, Object> getTrgterInInfoChk(Map<String, Object> param) throws Exception {
		return trgterInOutMngtDAO.getTrgterInInfoChk(param);
	}	
	
	@Override
	public int insertTrgterInReqInfo(Map<String, Object> param) throws Exception {
		return trgterInOutMngtDAO.insertTrgterInReqInfo(param);
	}

	@Override
	public int updateTrgterInReqInfo(Map<String, Object> param) throws Exception {
		return trgterInOutMngtDAO.updateTrgterInReqInfo(param);
	}

	@Override
	public Map<String, Object> updateTrgterInApprovalYn(Map<String, Object> param) throws Exception {
		return trgterInOutMngtDAO.updateTrgterInApprovalYn(param);
	}
	
	

}
