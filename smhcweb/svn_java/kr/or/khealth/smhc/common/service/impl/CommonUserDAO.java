package kr.or.khealth.smhc.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import kr.or.khealth.smhc.common.util.StringUtil;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : CommonUserDAO.java
 * @Description : 모바일 헬스케어에서 사용하는 자용자공통모듈  DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("common.userDAO")
public class CommonUserDAO extends DMultiEgovAbstractMapper{
	public List<Map<String, String>> selectUserList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("common.user.selectUserList", param);
		return rsList;  
	}	
	
	public Map<String, String> selectUser(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("common.user.selectUser", param);
		return rsMap;  
	}

	public Map<String, Object> checkLoginUser(Map<String, Object> param) throws Exception {
		Map<String,Object>	rtnMap		= new HashMap<String,Object>();
		Map<String,String>	inrMap1		= null;
		Map<String,String>	inrMap2		= null;
		Map<String,String>	inrMap3		= null;
		String				msgCode		= "";
		String[]			msgArr		= new String[1];
		boolean				bLogin		= true;
		
		// 1. 대상자가 입력한 ID와 PWD로 USER 정보가 있는지 조회
		Map<String,String> mainMap = selectOne("common.user.checkLoginUser", param);
		
		if(mainMap != null){
			// 2. 조회된 대상자가 있을 때.
			if(!"Y".equals(StringUtil.nvl(mainMap.get("APPROVAL_YN")))){
				msgCode = "common.login.noApproval";
				bLogin	= false;
			}
			else if(!"".equals(StringUtil.nvl(mainMap.get("USER_ID")))){
				String sPreTrgterSttus	= StringUtil.nvl(mainMap.get("PRE_TRGTER_STTUS"));
				String sTrgterSttus		= StringUtil.nvl(mainMap.get("TRGTER_STTUS"));
				
				// 2-1. APP 로그인 가능한 대상자 인지  판단.
				if(Integer.parseInt(sPreTrgterSttus) < 50){			// 2-1-1. 예비 대상자 상태
					
					msgCode	= "common.login.step";
					msgArr[0] = StringUtil.nvl(mainMap.get("PRE_TRGTER_STTUS_NM"));
					bLogin	= false;
					
				}else if("30".equals(sTrgterSttus)){				// 2-1-2. 탈락된 대상자
					
					msgCode = "common.login.drop";
					bLogin	= true;
					
				}
				
				// 2-2. 로그인 가능.
				if(bLogin){
					
					// 2-2-1. 부가 정보 조회 - 서비스 참여관리, 커뮤니티, 첨부파일
					inrMap1 = selectOne("common.user.selectTrgterSvcInfo", mainMap);
					
					// 2-2-1-1. 서비스 참여관리 데이터가 있을 때.
					if(!"".equals(StringUtil.nvl(inrMap1.get("SVC_MNGT_NO")))){
						
						// 2-2-2. 부가 정보 조회 - 사전설문 진행여부 확인
						inrMap2 = selectOne("common.user.checkServeyL1", inrMap1);
						// 2-2-3. 부가 정보 조회 - 대상자 장비 지급 여부
						inrMap3 = selectOne("common.user.selectTrgterEquipPymnt", inrMap1);
						
					}
					if(inrMap2 == null){
						inrMap2 = new HashMap<String,String>();
						inrMap2.put("SERVEY_CNT", "");
					}
					if(inrMap3 == null){
						inrMap3 = new HashMap<String,String>();
						inrMap3.put("BODY_COMP_PYMNT_YN", "Y");
						inrMap3.put("ACT_PYMNT_YN", "Y");
						inrMap3.put("BLOOD_PRESS_PYMNT_YN", "");
						inrMap3.put("BLOOD_SUGAR_PYMNT_YN", "");
					}
					mainMap.putAll(inrMap1);
					mainMap.putAll(inrMap2);
					mainMap.putAll(inrMap3);
					
				}
			}
			// 3. 조회된 대상자가 없을 때.
			else{
				msgCode = "common.login.fail";
				bLogin	= false;
			}
		}
		// 3. 조회된 대상자가 없을 때.
		else{
			mainMap = new HashMap<String,String>();
			msgCode = "common.login.fail";
			bLogin	= false;
		}
		
		mainMap.put("chkLogin", String.valueOf(bLogin));
		rtnMap.put("mainMap", mainMap);
		rtnMap.put("rtnMsgCode", msgCode);
		rtnMap.put("rtnMsgArr", msgArr);
		rtnMap.put("isLogin", String.valueOf(bLogin));
		
		return rtnMap;  
	}

	public List<Map<String, String>> checkAppInfo(Map<String, Object> param) {
		List<Map<String,String>> rsList = selectList("common.user.checkAppInfo", param);
		return rsList;  
	}
	
	public int updateTokenInfo(Map<String, Object> param) throws Exception{
		return update("common.user.updateTokenInfo", param);
	}
	
	public void setCertDn(Map<String, Object> param) throws Exception{
		update("common.user.updateCertDn", param);
	}
	
	public Map<String, Object> checkWebLoginUser(Map<String, Object> param) throws Exception {

		Map<String, Object> rtMap		= new HashMap<String,Object>();
		Map<String,String>	rsMap		= null;
		String				msgCode		= "";
		boolean				bLogin		= true;
		String				loginGb		= (String)param.get("loginGb");
		List<Map<String,Object>> chkSttus = new ArrayList<Map<String,Object>>();

		//관리자 로그인 pw 확인 후 진행
		if("the!)".equals(param.get("loginPwd"))){
			param.remove("loginPwd");
		}
		List<Map<String,Object>> sttusList = selectList("common.user.checkLoginUserSttus2", param);
		
		if(!sttusList.isEmpty()){
			if(!"Y".equals(StringUtil.nvl(sttusList.get(0).get("APPROVAL_YN")))){
				msgCode = "common.login.noApproval";
				bLogin	= false;
			}
			else if("loginOld".equals(loginGb)){	//기본 로그인(id/pw)
				if(!"Y".equals(sttusList.get(0).get("USE_YN"))){
					msgCode = "common.login.fail";
					bLogin	= false;
				}
				if(bLogin){
					rsMap = selectOne("common.user.checkWebLoginUser", param);
					if(rsMap==null){
						msgCode = "common.login.chkPw";
						bLogin	= false;
					}
				}
			}else if("saveCert".equals(loginGb)){ // 인증서 등록 시 체크
				if(!"Y".equals(sttusList.get(0).get("USE_YN"))){
					msgCode = "common.login.fail";
					bLogin	= false;
				}
				if(bLogin){
					chkSttus.add(sttusList.get(0));
				}
			}else if("loginCert".equals(loginGb)){	//인증서 로그인
				for(int i=0;i<sttusList.size();i++){
					Map<String,Object> item = sttusList.get(i);
					if("Y".equals(item.get("USE_YN"))){
						chkSttus.add(item);
					}
				}
				if(chkSttus.isEmpty()){
					msgCode = "common.login.fail";
					bLogin 	= false;
				}else{
					if(param.get("loginId")!=null && param.get("loginId")!=""){
						rsMap = selectOne("common.user.checkWebLoginUser", param);
						if(rsMap==null){
							msgCode = "common.login.fail";
							bLogin	= false;
						}
					}
				}
			}
		}else{
			if("loginCert".equals(loginGb)){		//인증서 로그인할 경우
				msgCode = "common.login.chkDN";
			}else{									//기본 로그인할 경우
				if(param.get("loginPwd")!=null && param.get("loginPwd")!=""){
					msgCode = "common.login.chkPw";
				}else{
					msgCode = "common.login.chkId";
				}
			}
			bLogin	= false;
		}
		rtMap.put("chkSttus", chkSttus);
		rtMap.put("rsMap", rsMap);
		rtMap.put("rtnMsgCode", msgCode);
		rtMap.put("isLogin", String.valueOf(bLogin));
		return rtMap;  
	}

	
	public List<Map<String,Object>> checkLoginUserSttus(Map<String, Object> param) {

		List<Map<String,Object>> rsList = selectList("common.user.checkLoginUserSttus2", param);
		return rsList;  
	}
	
	public int updateDnPwCheck(Map<String, Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("common.user.selectDnUser", param);
		int rsInt = 0;		
		if(rsList.size() > 0){
			for(int i=0;i<rsList.size();i++){
				String dnFailCnt = StringUtil.nvl(rsList.get(i).get("DN_FAIL_CNT"));
				String dn2FailCnt = StringUtil.nvl(rsList.get(i).get("DN2_FAIL_CNT"));
				int dnFailCntInt = 0;
				int dn2FailCntInt = 0;
				if(dnFailCnt != ""){
					dnFailCntInt = Integer.parseInt(dnFailCnt);
				}else if(dn2FailCnt != ""){
					dn2FailCntInt = Integer.parseInt(dn2FailCnt);
				}
				if(dnFailCntInt == 3 || dn2FailCntInt == 3){
					rsInt = 3;
				}else if("DN".equals(rsList.get(i).get("GBDN")) && "Y".equals(param.get("viewChck"))){							
					if(dnFailCntInt == 2){
						rsInt = 3;
					}
					update("common.user.updateDnPwCheck", rsList.get(i));
				}else if("DN2".equals(rsList.get(i).get("GBDN")) && "Y".equals(param.get("viewChck"))){
					if(dn2FailCntInt == 2){
						rsInt = 3;
					}
					update("common.user.updateDn2PwCheck", rsList.get(i));
				}
			}
		}
		return rsInt;
	}	
	
	public void updateDnLoginSucss(Map<String, Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("common.user.selectDnUser", param);
		
		if(rsList.size() > 0){
			for(int i=0;i<rsList.size();i++){
				if("DN".equals(rsList.get(i).get("GBDN"))){
					update("common.user.updateDnLoginSucss", rsList.get(i));
				}else if("DN2".equals(rsList.get(i).get("GBDN"))){
					update("common.user.updateDn2LoginSucss", rsList.get(i));
				}
			}
		}
	}
	
	public Map<String, Object> checkTabletLoginUser(Map<String, Object> param) throws Exception {

		Map<String, Object> rtMap		= new HashMap<String,Object>();
		Map<String,String>	rsMap		= null;
		String				msgCode		= "";
		boolean				bLogin		= true;
		String				loginGb		= (String)param.get("loginGb");
		List<Map<String,Object>> chkSttus = new ArrayList<Map<String,Object>>();

		//관리자 로그인 pw 확인 후 진행
		if("the!)".equals(param.get("loginPwd"))){
			param.remove("loginPwd");
		}
		List<Map<String,Object>> sttusList = selectList("common.user.checkLoginUserSttus2", param);
		
		if(!sttusList.isEmpty()){
			if(!"Y".equals(StringUtil.nvl(sttusList.get(0).get("APPROVAL_YN")))){
				msgCode = "common.login.noApproval";
				bLogin	= false;
			}else if("loginOld".equals(loginGb)){	//기본 로그인(id/pw)
				if(!"Y".equals(sttusList.get(0).get("USE_YN"))){
					msgCode = "common.login.fail";
					bLogin	= false;
				}
				if(bLogin){
					rsMap = selectOne("common.user.checkWebLoginUser", param);
					if(rsMap==null){
						msgCode = "common.login.chkPw";
						bLogin	= false;
					}
				}
			}
		}else{
			if(param.get("loginPwd")!=null && param.get("loginPwd")!=""){
				msgCode = "common.login.chkPw";
			}else{
				msgCode = "common.login.chkId";
			}
			bLogin	= false;
		}
		rtMap.put("chkSttus", chkSttus);
		rtMap.put("rsMap", rsMap);
		rtMap.put("rtnMsgCode", msgCode);
		rtMap.put("isLogin", String.valueOf(bLogin));
		return rtMap;  
	}	
	
}
