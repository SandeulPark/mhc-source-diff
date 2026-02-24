package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.cm.service.HealthExamReqService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : HealthExamReqService.java
 * @Description : 관리자 WEB에서 사용하는 건강검진 데이터 수정 요청사항 업무를 관리하는 서비스 imple
 * @Modification Information
 * @
 * @	  수정일		수정자			수정내용
 * @	----------		--------		---------------------------
 * @	2018.09.11		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.09.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.cm.HealthExamReqService")
public class HealthExamReqServiceImpl extends EgovAbstractServiceImpl implements HealthExamReqService{
	
	@Resource(name="web.cm.HealthExamReqDAO")
	private HealthExamReqDAO healthExamReqDAO;

	@Override
	public List<Map<String, String>> getHealthExamReqList(Map<String, Object> param) throws Exception {		
		return healthExamReqDAO.getHealthExamReqList(param);
	}

	@Override
	public Map<String, String> getHealthExamReqDtls(Map<String, Object> param)throws Exception {		
		return healthExamReqDAO.getHealthExamReqDtls(param);
	}
	
	@Override
	public List<Map<String, String>> getHealthExamReqBeAfList(Map<String, Object> param)throws Exception {		
		return healthExamReqDAO.getHealthExamReqBeAfList(param);
	}
	
	

	@Override
	public void insertHealthExamReq(Map<String, Object> param)throws Exception {		
		healthExamReqDAO.insertHealthExamReq(param);
	}

	@Override
	public void updateHealthExamReq(Map<String, Object> param) throws Exception {		
		healthExamReqDAO.updateHealthExamReq(param);
	}
	
	@Override
	public void deleteHealthExamReq(Map<String, Object> param) throws Exception {
		healthExamReqDAO.deleteHealthExamReq(param);
	}

	@Override
	public List<Map<String, Object>> getHealthExamReqCommentList(Map<String, Object> param) throws Exception {		
		return healthExamReqDAO.getHealthExamReqCommentList(param);
	}

	@Override
	public void insertHealthExamReqComment(Map<String, Object> param)throws Exception {	
		healthExamReqDAO.insertHealthExamReqComment(param);
	}

	@Override
	public void updateHealthExamReqComment(Map<String, Object> param)throws Exception {	
		healthExamReqDAO.updateHealthExamReqComment(param);
	}

	@Override
	public void deleteHealthExamReqComment(Map<String, Object> param)throws Exception {		
		healthExamReqDAO.deleteHealthExamReqComment(param);
	}


	@Override
	public List<Map<String, String>> getAttachFileList(Map<String, String> param) throws Exception {
		return healthExamReqDAO.getAttachFileList(param);
	}
	
}
