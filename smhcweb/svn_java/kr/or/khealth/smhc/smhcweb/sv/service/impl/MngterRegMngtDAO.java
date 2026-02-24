package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MngterRegMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 관리자 정보 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.09.22		양현우			최초생성
 *
 * @author theJoin
 * @since 2020.09.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sv.MngterRegMngtDAO")
public class MngterRegMngtDAO extends DMultiEgovAbstractMapper{


	public List<Map<String, String>> selectMngterRegMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("smhc.web.sv.mngterregmngt.selectMngterRegMngtList", param);
		return rsList;
	}
	
	public int saveManagerInfo(Map<String, Object> param) throws Exception {
		int rsInt = 0;		
		if(param.get("CRUDDIV").equals("C")){		//신규 등록
			Map<String,String> rsMap = selectOne("smhc.web.sv.mngterregmngt.getNewUserId");
			param.put("MANAGER_ID",rsMap.get("MANAGER_ID"));
			rsInt = update("smhc.web.sv.mngterregmngt.saveManagerInfo", param);
			rsInt += update("smhc.web.sv.mngterregmngt.saveManagerAuth", param);
		}else if(param.get("CRUDDIV").equals("U")){  //수정
			if(param.get("CHKSEC").equals("N")){    //정보보안각서 필요 시
				rsInt = update("smhc.web.sv.mngterregmngt.saveManagerInfo", param);
				rsInt += update("smhc.web.sv.mngterregmngt.saveManagerAuth", param);
			}else if(param.get("CHKSEC").equals("Y")){  //정보보안 각서 필요 없을 시
				rsInt = update("smhc.web.sv.mngterregmngt.saveManagerInfoNoChk", param);
			}
		}
		return rsInt;
	}
	
	public int updatedn1Use(Map<String, Object> param) throws Exception {
		int rsInt = update("smhc.web.sv.mngterregmngt.updatedn1Use", param);		
		return rsInt;
	}
	
	public int updatedn2Use(Map<String, Object> param) throws Exception {
		int rsInt = update("smhc.web.sv.mngterregmngt.updatedn2Use", param);		
		return rsInt;
	}
	
	public int updateApprovalYn(Map<String, Object> param) throws Exception {
		int rsInt = update("smhc.web.sv.mngterregmngt.updateApprovalYn", param);		
		return rsInt;
	}
	
	public Map<String, Object> getManagerDuplicationCnt(Map<String, Object> param) throws Exception {
		Map<String,Object> rsMap = selectOne("smhc.web.sv.mngterregmngt.selectManagerDuplicationCnt",param);	
		return rsMap;
	}

	public List<Map<String, Object>> getServiceRequestList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.mngterregmngt.selectServiceRequestList", param);
		return rsList;
	}

	public int getServiceRequestListCount(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("smhc.web.sv.mngterregmngt.selectServiceRequestListCount", param);
		return rsInt;
	}

	public List<Map<String, Object>> getServiceRequestExcelList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.mngterregmngt.selectServiceRequestExcelList", param);
		return rsList;
	}

	public List<Map<String, Object>> getTrgtMenuCombo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.mngterregmngt.selectTrgtMenuCd", param);
		return rsList;
	}

	public void saveServiceRequestMngt(Map<String, Object> param) throws Exception {
		String queryId = "";
		if(param.get("SVC_REQ_SN") == null || "".equals(param.get("SVC_REQ_SN"))){
			queryId = "smhc.web.sv.mngterregmngt.insertSvcReqProc";
		}else{
			queryId = "smhc.web.sv.mngterregmngt.updateSvcReqProc";
		}
		insert(queryId, param);
	}

	public Map<String, Object> getServiceRequestDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("smhc.web.sv.mngterregmngt.selectServiceRequestDtls", param);
		return rsMap;
	}

	public void delServiceRequest(Map<String, Object> param) throws Exception {
		update("smhc.web.sv.mngterregmngt.deleteServiceRequest", param);
	}
	
	public List<Map<String, String>> selectMngtList()throws Exception {
		List<Map<String, String>> rsList = selectList("smhc.web.sv.mngterregmngt.selectMngtList");
		return rsList;
	}
}
