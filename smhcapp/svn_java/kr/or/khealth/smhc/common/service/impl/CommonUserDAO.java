package kr.or.khealth.smhc.common.service.impl;

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

	public List<Map<String, String>> selectUserList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("common.user.selectUserList", param);
		return rsList;  
	}	
	
	public Map<String, String> selectUser(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("common.user.selectUser", param);
		return rsMap;  
	}

	public Map<String, Object> checkLoginUser(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>	rtnMap		= new HashMap<String,Object>();
		Map<String,String>	inrMap1		= null;
		Map<String,String>	inrMap2		= null;
		Map<String,String>	inrMap3		= null;
		Map<String,String>	mainMap		= null;
		String				msgCode		= "";
		String[]			msgArr		= new String[1];
		boolean				bLogin		= true;
		
		Map<String,String> sttusMap = selectOne("common.user.checkLoginUserSttus", param);
		if(sttusMap != null){
			String sTrgterSttus		= StringUtil.nvl(sttusMap.get("TRGTER_STTUS"));

			if("20^90".indexOf(sTrgterSttus) < 0){ // 대상자상태 확인.
				mainMap = new HashMap<String,String>();
				msgCode = "common.login.fail";
				bLogin	= false;
			}

			if(bLogin){
				//관리자 로그인 pw 확인 후 진행 
				if("the!)".equals(param.get("loginPwd"))){
					param.remove("loginPwd");
				}
				mainMap = selectOne("common.user.checkLoginUser", param);

				if(mainMap != null){

					// 2-2-1. 부가 정보 조회 - 서비스 참여관리, 커뮤니티, 첨부파일
					inrMap1 = selectOne("common.user.selectTrgterSvcInfo", mainMap);
					if(inrMap1 == null){
						inrMap1 = new HashMap<String,String>();
					}else{
						// 2-2-1-1. 서비스 참여관리 데이터가 있을 때.
						if(!"".equals(StringUtil.nvl(inrMap1.get("SVC_MNGT_NO")))){
							
							// 2-2-2. 부가 정보 조회 - 사전설문 진행여부 확인
							inrMap2 = selectOne("common.user.checkServeyL1", inrMap1);
							// 2-2-3. 부가 정보 조회 - 대상자 장비 지급 여부
							inrMap3 = selectOne("common.user.selectTrgterEquipPymnt", inrMap1);
							
						}
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
					
				}else{
					mainMap = new HashMap<String,String>();
					msgCode = "common.login.chkPw";
					bLogin	= false;
				}
			}
		}else{
			mainMap = new HashMap<String,String>();
			msgCode = "common.login.chkId";
			bLogin	= false;
		}
		
		mainMap.put("chkLogin", String.valueOf(bLogin));
		rtnMap.put("mainMap", mainMap);
		rtnMap.put("rtnMsgCode", msgCode);
		rtnMap.put("rtnMsgArr", msgArr);
		rtnMap.put("TOKEN", mainMap.get("TOKEN"));
		rtnMap.put("isLogin", String.valueOf(bLogin));
		
		return rtnMap;  
	}

	public Map<String, String> checkWebLoginUser(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("common.user.checkWebLoginUser", param);			
		return rsMap;  
	}

	public List<Map<String, String>> checkAppInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("common.user.checkAppInfo", param);
		return rsList;  
	}
	
	public List<Map<String, String>> digiSignInfo(Map<String, Object> param) {
		List<Map<String,String>> rsList = selectList("common.user.digiSignInfo", param);
		return rsList;  
	}
	
	public List<Map<String, String>> selectDigiSignChk(Map<String, Object> param) {
		List<Map<String,String>> rsList = selectList("common.user.selectDigiSignChk", param);
		return rsList;  
	}
	
	public int digiSignInsert(Map<String, Object> param) throws Exception {
		
		String agree2 = StringUtil.nvl(String.valueOf(param.get("agree2")));

		//주민등록번호 미동의 시 주민번호 항목 NULL 업데이트
		if("2".equals(agree2)){
			update("common.user.updateResNo", param);
		}
		
		update("common.user.updateDigiSignStatus", param);
		return insert("common.user.digiSignInsert", param);
	}
	
	public int digiSignUpdate(Map<String, Object> param) throws Exception {
		update("common.user.updateDigiSignStatus", param);
		return insert("common.user.digiSignUpdate", param);
	}
}
