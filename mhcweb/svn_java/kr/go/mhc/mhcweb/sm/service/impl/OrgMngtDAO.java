package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : OrgMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 기관 정보 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.04.12		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.04.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sm.OrgMngtDAO")
public class OrgMngtDAO extends DMultiEgovAbstractMapper {
	
	/**
	 * 기관관리 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getOrgMngtList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.sm.orgmngt.selectOrgMngtList", param);	
		return rsList;  
	}

	/**
	 * 기관관리 정보 상세 조회(자가관리군 집중상담/중간검진 진행 여부)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getOrgDtlsList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.sm.orgmngt.selectOrgDtlsList", param);
		return rsList;
 	}
	
	/**
	 * 기관 등록 및 수정
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int saveOrgMngt(Map<String, Object> param) throws Exception {
		
		int rsInt = 0;		
		String orgCd = (String) param.get("ORG_CD");
		String etcPoct = (String) param.get("ETC_POCT");
		
		if("".equals(orgCd)){
			orgCd = selectOne("mhc.web.sm.orgmngt.selectOrgPk", param);
			if("".equals(orgCd)){
				orgCd = param.get("ORG_CLF") + "001";
			}
			if(param.get("ORG_CLF").toString().equals("G")){
				param.put("GRP_ORG_CD", orgCd);
				insert("mhc.web.sm.orgmngt.insertNewGroup", param);
			}
			//식생활 실천 미션 코드 자동 생성
			if(!param.get("ORG_CLF").toString().equals("G")){
				param.put("ORG_CD", orgCd);
				insert("mhc.web.sm.orgmngt.insertPractMissionSchCd", param);
			}
		}
		param.put("ORG_CD", orgCd);
		
		rsInt = update("mhc.web.sm.orgmngt.saveOrgInfo", param);
		update("mhc.web.sm.orgmngt.saveCmntyMastr", param);
		
		if(!"".equals(etcPoct)){
			insert("mhc.web.sm.orgmngt.insertEtcPoct", param);
		}
		
		return rsInt;
	}
	//20191203 양현우 추가
	/**
	 * 상담진행순서 변경 팝업
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectOrgMngtPop(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.orgmngt.selectOrgMngtPop",param);
		return rsList;  
	}
	//20191203 양현우 추가 끝
	
	
	/**
	 * 관리자 등록 승인
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int updateOrgApprovalYn(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.sm.orgmngt.updateOrgApprovalYn", param);		
		return rsInt;
	}
	/**
	 * 관리자 등록 및 수정(로그인페이지에서 호출시)
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int saveOrgMngter(Map<String, Object> param) throws Exception {
		int rsInt;
		
		System.out.println("param ::: " + param);
		 
		if(param.get("USER_ID").equals("")){
			Map<String,String> rsMap = selectOne("mhc.web.sm.mngterregmngt.getNewUserId");
			param.put("USER_ID", rsMap.get("USER_ID"));
			rsInt = insert("mhc.web.sm.mngterregmngt2.insertUserInfo", param);
			  if(param.get("CHECK").equals("A")){                                   //20191231양현우 추가
			      rsInt += insert("mhc.web.sm.mngterregmngt.insertDigiSign", param);
			}
		}else{
			Map<String, Object> rsMap = selectOne("common.cmmn.selectPwChangeChk", param);
			param.put("PW_CNT", rsMap.get("PW_CNT"));
			rsInt = update("mhc.web.sm.mngterregmngt2.updateUserInfo", param);
			insert("mhc.web.sm.mngterregmngt.insertManagerInfoHist", param);
			
			if(param.get("changeAck").equals("Y")|| param.get("changeAppYN").equals("N")){     //20191231양현우 추가
				rsInt += insert("mhc.web.sm.mngterregmngt.insertDigiSign", param);
			}
		}
		if(param.get("changeAck").equals("Y") || param.get("changeAppYN").equals("N")){
			rsInt += update("mhc.web.sm.mngterregmngt.saveManagerInfo", param);
			
		}else{
			rsInt += update("mhc.web.sm.mngterregmngt.saveManagerInfo2", param);
		}
		rsInt += update("mhc.web.sm.mngterregmngt2.saveManagerAuth", param);
		
		// 의사가 간호사에게 권한 부여
		rsInt += update("mhc.web.sm.mngterregmngt.saveNurseAuth", param);
		
		return rsInt;
	}

	public List<Map<String, String>> selectOrgList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.sm.orgmngt.selectOrgList", param);
		return rsList;
	}
}
