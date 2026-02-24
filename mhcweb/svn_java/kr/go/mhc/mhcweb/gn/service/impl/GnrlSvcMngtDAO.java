package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SvcMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 건강정보관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.08		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.gn.GnrlSvcMngtDAO")
public class GnrlSvcMngtDAO extends DMultiEgovAbstractMapper{
	public int getHealthInfoMngtListCount(Map<String, Object> param) throws Exception {
		param.put("TAB_ORG", param.get("tabId").equals("tab_otherOrg") ? "K001" : "H");
		int rsInt = selectOne("mhc.web.gn.gnrlsvcmngt.selectHealthInfoMngtListCount",param);	
		return rsInt;  
	}
	
	public List<Map<String, String>> getHealthInfoMngtList(Map<String, Object> param) throws Exception {
		param.put("TAB_ORG", param.get("tabId").equals("tab_otherOrg") ? "K001" : "H");
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlsvcmngt.selectHealthInfoMngtList", param);		
		return rsList;  
	}
	
	public Map<String, Object> getHealthInfoDtls(Map<String, Object> param) throws Exception {		
		Map<String,Object> rsMap = selectOne("mhc.web.gn.gnrlsvcmngt.selecthealthInfoDtls", param);	
		return rsMap;  
	}
	
	public List<Map<String, String>> getCmntyGroupList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlsvcmngt.selectCmntyGroupList", param);		
		return rsList;  
	}
	
	public List<Map<String, String>> getAllCmntyGroupList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlsvcmngt.selectAllCmntyGroupList", param);		
		return rsList;  
	}
	
	public List<Map<String, String>> getHealthInfoCmmntList(Map<String, Object> param) throws Exception {
		String selectListNm = "mhc.web.gn.gnrlsvcmngt.selectHealthInfoCmmntList";
		if(param.get("selectListNm").equals("goodList")){
			selectListNm = "mhc.web.gn.gnrlsvcmngt.selectGoodList";
		}
		List<Map<String,String>> rsList = selectList(selectListNm, param);		
		return rsList;  
	}
	
	public List<Map<String, String>> getHealthInfoCmmntAttchList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlsvcmngt.selectCmntAddFiles", param);		
		return rsList;  
	}
	
	public List<Map<String, String>> getHealthInfoGoodList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlsvcmngt.selectHealthInfoGoodList", param);		
		return rsList;  
	}
	
	public void getHealthInfoDtlsUpdate(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlsvcmngt.updateDetailHealthContents", param);
		delete("mhc.web.gn.gnrlsvcmngt.deleteCmntyBoardPostG", param);
		String[] reCmmntTgrtInfo = param.get("selGroupList").toString().split(",");		
		int selGroupListCnt = Integer.parseInt(param.get("selGroupListCnt").toString());		
		for(int i=0; i < selGroupListCnt; i++){
			param.put("GRP_SN", reCmmntTgrtInfo[i]);
			insert("mhc.web.gn.gnrlsvcmngt.insertCmntyBoardPostG", param);
		}
	}
	
	public void getHealthInfoReCmmntInsert(Map<String, Object> param) throws Exception {
		String[] reCmmntTgrtInfo = param.get("reCmmntTgrtInfo").toString().split(",");
		Map<String, Object> reCmmntTgrtInfoMap = new HashMap<String, Object>();
		reCmmntTgrtInfoMap.put("CONT", param.get("CONT"));
		reCmmntTgrtInfoMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
		reCmmntTgrtInfoMap.put("SESS_USER_NM", param.get("SESS_USER_NM"));
		reCmmntTgrtInfoMap.put("SESS_AUTH_TYPE", param.get("SESS_AUTH_TYPE"));
		reCmmntTgrtInfoMap.put("SESS_CMNTY_CD", param.get("SESS_CMNTY_CD"));
		int j = 0;
		int reCmmntTgrtCnt = Integer.parseInt(param.get("reCmmntTgrtCnt").toString());
		for(int i=0; i < reCmmntTgrtCnt; i++){
			reCmmntTgrtInfoMap.put("CMNTY_CD", reCmmntTgrtInfo[i+j]);
			reCmmntTgrtInfoMap.put("BOARD_SN", reCmmntTgrtInfo[i+j+1]);
			reCmmntTgrtInfoMap.put("CMMNT_SN", reCmmntTgrtInfo[i+j+2]);
			insert("mhc.web.gn.gnrlsvcmngt.insertHealthInfoReCmmnt", reCmmntTgrtInfoMap);
			update("mhc.web.gn.gnrlsvcmngt.updateHealthInfoReCmmntCount", reCmmntTgrtInfoMap);
			j += 2;
			reCmmntTgrtInfoMap.remove("CMNTY_CD");
			reCmmntTgrtInfoMap.remove("BOARD_SN");
			reCmmntTgrtInfoMap.remove("CMMNT_SN");
		}
	}
	
	public void gethealthInfoReCmmntDelete(Map<String, Object> param) throws Exception{
		String[] reCmmntTgrtInfo = param.get("reCmmntTgrtInfo").toString().split(",");
		Map<String, Object> reCmmntTgrtInfoMap = new HashMap<String, Object>();
		reCmmntTgrtInfoMap.put("CONT", param.get("CONT"));
		reCmmntTgrtInfoMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
		reCmmntTgrtInfoMap.put("SESS_USER_NM", param.get("SESS_USER_NM"));
		reCmmntTgrtInfoMap.put("SESS_AUTH_TYPE", param.get("SESS_AUTH_TYPE"));
		reCmmntTgrtInfoMap.put("SESS_CMNTY_CD", param.get("SESS_CMNTY_CD"));
		reCmmntTgrtInfoMap.put("TOTAL_CMMNT_CNT", param.get("TOTAL_CMMNT_CNT"));
		int j = 0;
		int reCmmntTgrtCnt = Integer.parseInt(param.get("reCmmntTgrtCnt").toString());
		for(int i=0; i < reCmmntTgrtCnt; i++){
			reCmmntTgrtInfoMap.put("CMNTY_CD", reCmmntTgrtInfo[i+j]);
			reCmmntTgrtInfoMap.put("BOARD_SN", reCmmntTgrtInfo[i+j+1]);
			reCmmntTgrtInfoMap.put("CMMNT_SN", reCmmntTgrtInfo[i+j+2]);
			j += 2;
			update("mhc.web.gn.gnrlsvcmngt.deleteHealthInfoReCmmnt", reCmmntTgrtInfoMap);
		}
		update("mhc.web.gn.gnrlsvcmngt.deleteHealthInfoReCmmntCount", reCmmntTgrtInfoMap);
		
	}
	
	public void getnewHealthInfoInsert(Map<String, Object> param) throws Exception {
		int boardsn = selectOne("mhc.web.gn.gnrlsvcmngt.selectBoardSn",param);
		param.put("BOARD_SN",boardsn);
		insert("mhc.web.gn.gnrlsvcmngt.insertNewHealthInfo", param);
		String[] reCmmntTgrtInfo = param.get("selGroupList").toString().split(",");		
		int selGroupListCnt = Integer.parseInt(param.get("selGroupListCnt").toString());	
		if(!param.get("SESS_ORG_CD").equals("K001")){
			for(int i=0; i < selGroupListCnt; i++){
				param.put("GRP_SN", reCmmntTgrtInfo[i]);
				insert("mhc.web.gn.gnrlsvcmngt.insertCmntyBoardPostG", param);
			}
		}
	}

	public List<Map<String, String>> getSelectAttchFileList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlsvcmngt.selectAttchFileList", param);		
		return rsList;  
	}

	public void updateArticlePostClf(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlsvcmngt.updateArticlePostClf", param);
	}

	public void getHealthInfoCmmntInsert(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlsvcmngt.insertHealthInfoCmmnt", param);
		update("mhc.web.gn.gnrlsvcmngt.updateHealthInfoReCmmntCount", param);
	}
	
	public void healthInfoDtlsDelete(Map<String,Object> param) throws Exception{
		delete("mhc.web.gn.gnrlsvcmngt.deleteHealthInfoDtls", param);
	}
	
	public int getReCnt(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.gn.gnrlsvcmngt.reCnt", param);
		return rsList;
	}
	
	public int getGoodCnt(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.gn.gnrlsvcmngt.goodCnt", param);
		return rsList;
	}
	
}
