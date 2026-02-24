package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : HealthExamReqService.java
 * @Description : 관리자 WEB에서 사용하는 건강검진 데이터 수정 요청사항 업무를 관리하는 서비스 DAO
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


@Repository("web.cm.HealthExamReqDAO")
public class HealthExamReqDAO extends DMultiEgovAbstractMapper{
	

	
	public List<Map<String, String>> getHealthExamReqList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.cm.healthexamreq.selectHealthExamReqList", param);		
		return rsList;  
	}

	
	public Map<String, String> getHealthExamReqDtls(Map<String, Object> param)throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.cm.healthexamreq.selectHealthExamReqDtls",param);	
		
		System.out.println("rsMap ::::::::::: " + rsMap);
		return rsMap;  
	}
	
	public List<Map<String, String>> getHealthExamReqBeAfList(Map<String, Object> param)throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.cm.healthexamreq.selectHealthExamReqBeAfList",param);	
		return rsList;  
	}
	
	public void insertHealthExamReq(Map<String, Object> param)throws Exception {
		insert("mhc.web.cm.healthexamreq.insertHealthExamReq", param);			
	}

	
	public void updateHealthExamReq(Map<String, Object> param) throws Exception {
		update("mhc.web.cm.healthexamreq.updateHealthExamReq", param);			
		
	}

	
	public void deleteHealthExamReq(Map<String, Object> param) throws Exception {
		delete("mhc.web.cm.healthexamreq.deleteHealthExamReq", param);			
	}	
	
	
	public List<Map<String, Object>> getHealthExamReqCommentList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.healthexamreq.selectHealthExamReqCommentList", param);		
		
		return rsList;
	}

	
	public void insertHealthExamReqComment(Map<String, Object> param)throws Exception {
		insert("mhc.web.cm.healthexamreq.insertHealthExamReqComment", param);	
		
	}

	
	public void updateHealthExamReqComment(Map<String, Object> param)throws Exception {
		update("mhc.web.cm.healthexamreq.updateHealthExamReqComment", param);	
		
	}

	
	public void deleteHealthExamReqComment(Map<String, Object> param)throws Exception {
		update("mhc.web.cm.healthexamreq.deleteHealthExamReqComment", param);	
		
	}
	
	public List<Map<String, String>> getAttachFileList(Map<String, String> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.cm.healthexamreq.selectAttachFileList", param);		
		return rsList;
	}


}
