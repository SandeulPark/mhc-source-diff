package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : CmntyGSetDAO.java
 * @Description : 관리자 WEB에서 사용하는 건강정보관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.18		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sv.CmntyGSetDAO")
public class CmntyGSetDAO extends DMultiEgovAbstractMapper{
	
	/**
	  * 그룹 목록 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	public List<Map<String, String>> getCmntyGList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.sv.cmntygset.selectcmntyGList", param);		
		return rsList;  
	}
	
	/**
	  * 그룹 대상자 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	public List<Map<String, String>> getCmntyGTrgterList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.sv.cmntygset.selectcmntyGTrgterList", param);		
		return rsList;  
	}
	
	/**
	  *  새 커뮤니티 그룹번호 가져오기
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	public Map<String,Object> getNewCmntyGSn(Map<String, Object> param) throws Exception {		
		Map<String,Object> rsMap = selectOne("mhc.web.sv.cmntygset.selectNewCmntyGSn", param);
		return rsMap;  
	}
	
	/**
	  *  그룹 등록
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	public void getCmntyGInsert(Map<String, Object> param) throws Exception {
		insert("mhc.web.sv.cmntygset.insertCmntyG", param);
	}

	/**
	  * 그룹 수정
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	public void getCmntyGUpd(Map<String, Object> param) throws Exception {
		update("mhc.web.sv.cmntygset.updateCmntyG", param);
//		update("mhc.web.sv.cmntygset.updateCmntyGUseNMb", param);
	}
	
	/**
	  * 그룹 제외
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	public void getCmntyGUseN(Map<String, Object> param) throws Exception {
		update("mhc.web.sv.cmntygset.updateCmntyGUseN", param);
	}
	
	/**
	 * 대상자 제외 (Update)  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	
	public void getCmntyGTrgterUseN(Map<String, Object> param) throws Exception {
		String[] trgterInfo = param.get("trgterInfo").toString().split(",");	
		int trgterCnt = Integer.parseInt(param.get("trgterCnt").toString());
		Map<String, Object> trgterInfoMap = new HashMap<String, Object>();
		for(int i=0; i < trgterCnt; i++){
			trgterInfoMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
			trgterInfoMap.put("CMNTY_CD", param.get("CMNTY_CD"));
			trgterInfoMap.put("USER_ID", trgterInfo[i*2]);
			trgterInfoMap.put("GRP_SN", trgterInfo[1]);
//			update("mhc.web.sv.cmntygset.updateCmntyGTrgterUseN", trgterInfoMap);
			delete("mhc.web.sv.cmntygset.deleteCmntyGTrgter", trgterInfoMap);
		}
	}
	
	/**
	  * 관리군 목록 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	public List<Map<String, String>> getGclasList() throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.sv.cmntygset.selectGclasList");		
		return rsList;  
	}
	
	 /**
	  * 추가 대상자 조회 (팝업)
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	public List<Map<String, String>> getAddTrgterList(Map<String, Object> param) throws Exception {		
		System.out.println("================================================================== : ++ "+param);
		List<Map<String,String>> rsList = selectList("mhc.web.sv.cmntygset.selectAddTrgterList", param);		
		return rsList;  
	}
	
	/**
	 * 대상자 추가  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	
	public void getCmntyGTrgterInsert(Map<String, Object> param) throws Exception {
		String[] modalwrapInfo = param.get("modalwrapInfo").toString().split(",");	
		int modalwrapCnt = Integer.parseInt(param.get("modalwrapCnt").toString());
		Map<String, Object> modalwrapInfoMap = new HashMap<String, Object>();
			for(int i=0; i < modalwrapCnt; i++){
				modalwrapInfoMap.put("CMNTY_CD", param.get("CMNTY_CD"));
				modalwrapInfoMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
				modalwrapInfoMap.put("GRP_SN", param.get("GRP_SN"));
				modalwrapInfoMap.put("USER_ID", modalwrapInfo[i]);
				update("mhc.web.sv.cmntygset.selectMergeMember", modalwrapInfoMap);
			}
		}
	
	/**
	 * 그룹 삭제	
	 * @param param
	 */
	public void delCmntyGroup(Map<String, Object> param) {
		delete("mhc.web.sv.cmntygset.deleteCmntyGroup", param);		
	}
	/**
	 * 그룹 대상자 삭제
	 * @param param
	 */
	public void delCmntyGroupMember(Map<String, Object> param) {
		delete("mhc.web.sv.cmntygset.deleteCmntyGroupMember", param);			
	}
}
