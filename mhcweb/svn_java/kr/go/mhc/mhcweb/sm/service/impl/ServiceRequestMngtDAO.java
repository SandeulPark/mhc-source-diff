package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : ServiceRequestMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 운영처리 관리하는 DAO Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.30		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.03.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sm.ServiceRequestMngtDAO")
public class ServiceRequestMngtDAO extends DMultiEgovAbstractMapper {
	
	/**
	 * 운영처리 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getServiceRequestList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.servicerequestmngt.selectServiceRequestList", param);	
		return rsList;  
	}
	
	/**
	 * 운영처리 건수 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int getServiceRequestListCount(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("mhc.web.sm.servicerequestmngt.selectServiceRequestListCount", param);
		return rsInt;
	}

	/**
	 * 운영처리 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void saveServiceRequestMngt(Map<String, Object> param) throws Exception {
		String queryId = "";		
		if(param.get("SVC_REQ_SN") == null || "".equals(param.get("SVC_REQ_SN"))){
			queryId = "mhc.web.sm.servicerequestmngt.insertSvcReqProc";
		}else{
			queryId = "mhc.web.sm.servicerequestmngt.updateSvcReqProc";
		}		
		insert(queryId, param);
	}
	
	/**
	 * 운영처리 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getServiceRequestDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sm.servicerequestmngt.selectServiceRequestDtls", param);
		return rsMap;
	}
	
	/**
	 * 운영처리 대상메뉴 콤보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTrgtMenuCombo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.servicerequestmngt.selectTrgtMenuCd", param);
		return rsList;
	}
	
	/**
	 * 운영처리 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getServiceRequestExcelList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.servicerequestmngt.selectServiceRequestExcelList", param);	
		return rsList;  
	}
	
	/**
	 * 운영처리 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void delServiceRequest(Map<String, Object> param) throws Exception {
		update("mhc.web.sm.servicerequestmngt.deleteServiceRequest", param);
	}
	/**
	 * 기관 검색 팝업 기관 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>>getOrgChkList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sm.servicerequestmngt.selectOrgChkList",param);
		return rsList;
	}
	
	/**
	 * 관리자명 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<String>selectMngtList()throws Exception {
		List<String> rsList = selectList("mhc.web.sm.servicerequestmngt.selectMngtList");
		return rsList;
	}
}
