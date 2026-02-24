package kr.go.mhc.mhcweb.sv.service.impl;

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

@Repository("web.sv.SvcMngtDAO")
public class SvcMngtDAO extends DMultiEgovAbstractMapper{
	public int getHealthInfoMngtListCount(Map<String, Object> param) throws Exception {
		param.put("ORG_CD", param.get("tabId").equals("tab_otherOrg") ? "K001" : param.get("SESS_ORG_CD").toString());
		int rsInt = ((Integer)selectOne("mhc.web.sv.svcmngt.selectHealthInfoMngtListCount", param)).intValue();
		return rsInt;
	}

	public List<Map<String, String>> getHealthInfoMngtList(Map<String, Object> param) throws Exception {
		if (param.get("SESS_ORG_CD").equals("K001"))
			param.put("ORG_CD", param.get("tabId").equals("tab_otherOrg") ? "K001" : "H1");
		else {
			param.put("ORG_CD", param.get("tabId").equals("tab_otherOrg") ? "K001" : param.get("SESS_ORG_CD").toString());
		}
		List rsList = selectList("mhc.web.sv.svcmngt.selectHealthInfoMngtList", param);
		return rsList;
	}
	
	public Map<String, Object> getHealthInfoDtls(Map<String, Object> param) throws Exception {		
		Map<String,Object> rsMap = selectOne("mhc.web.sv.svcmngt.selecthealthInfoDtls", param);	
		return rsMap;  
	}
	
	public List<Map<String, String>> getCmntyGroupList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.sv.svcmngt.selectCmntyGroupList", param);		
		return rsList;  
	}
	
	public List<Map<String, String>> getAllCmntyGroupList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.sv.svcmngt.selectAllCmntyGroupList", param);		
		return rsList;  
	}

	public List<Map<String, String>> getCmntyMclasList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.sv.svcmngt.selectCmntyMclasList", param);
		return rsList;
	}

	public List<Map<String, String>> getCmntyChronicList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.sv.svcmngt.selectCmntyChronicList", param);
		return rsList;
	}
	
	public List<Map<String, String>> getHealthInfoCmmntList(Map<String, Object> param) throws Exception {
		String selectListNm = "mhc.web.sv.svcmngt.selectHealthInfoCmmntList";
		if(param.get("selectListNm").equals("goodList")){
			selectListNm = "mhc.web.sv.svcmngt.selectGoodList";
		}
		List<Map<String,String>> rsList = selectList(selectListNm, param);		
		return rsList;  
	}
	
	public List<Map<String, String>> getHealthInfoCmmntAttchList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.sv.svcmngt.selectCmntAddFiles", param);		
		return rsList;  
	}
	
	public List<Map<String, String>> getHealthInfoGoodList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.sv.svcmngt.selectHealthInfoGoodList", param);		
		return rsList;  
	}
	
	public void getHealthInfoDtlsUpdate(Map<String, Object> param) throws Exception {
		update("mhc.web.sv.svcmngt.updateDetailHealthContents", param);
		// 커뮤니티 그룹
		delete("mhc.web.sv.svcmngt.deleteCmntyBoardPostG", param);
		String[] reCmmntTgrtInfo = param.get("selGroupList").toString().split(",");		
		int selGroupListCnt = Integer.parseInt(param.get("selGroupListCnt").toString());		
		for(int i=0; i < selGroupListCnt; i++){
			param.put("GRP_SN", reCmmntTgrtInfo[i]);
			insert("mhc.web.sv.svcmngt.insertCmntyBoardPostG", param);
		}

		// 군분류
		delete("mhc.web.sv.svcmngt.deleteCmntyBoardPostMclas", param);
		String[] mclasList = param.get("selMclasList").toString().split(",");
		int selMclasListCnt = Integer.parseInt(param.get("selMclasListCnt").toString());
		for(int i=0; i < selMclasListCnt; i++){
			param.put("MCLAS_CD", mclasList[i]);
			insert("mhc.web.sv.svcmngt.insertCmntyBoardPostMclas", param);
		}

		// 만성질환 군분류
		delete("mhc.web.sv.svcmngt.deleteCmntyBoardPostChronic", param);
		String[] chronicList = param.get("selChronicList").toString().split(",");
		int selChronicListCnt = Integer.parseInt(param.get("selChronicListCnt").toString());
		for(int i=0; i < selChronicListCnt; i++){
			param.put("CHRONIC_CD", chronicList[i]);
			insert("mhc.web.sv.svcmngt.insertCmntyBoardPostChronic", param);
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
		reCmmntTgrtInfoMap.put("SECRET_YN", param.get("SECRET_YN"));
		int j = 0;
		int reCmmntTgrtCnt = Integer.parseInt(param.get("reCmmntTgrtCnt").toString());
		for(int i=0; i < reCmmntTgrtCnt; i++){
			reCmmntTgrtInfoMap.put("CMNTY_CD", reCmmntTgrtInfo[i+j]);
			reCmmntTgrtInfoMap.put("BOARD_SN", reCmmntTgrtInfo[i+j+1]);
			reCmmntTgrtInfoMap.put("CMMNT_SN", reCmmntTgrtInfo[i+j+2]);
			insert("mhc.web.sv.svcmngt.insertHealthInfoReCmmnt", reCmmntTgrtInfoMap);
			update("mhc.web.sv.svcmngt.updateHealthInfoReCmmntCount", reCmmntTgrtInfoMap);
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
			update("mhc.web.sv.svcmngt.deleteHealthInfoReCmmnt", reCmmntTgrtInfoMap);
		}
		update("mhc.web.sv.svcmngt.deleteHealthInfoReCmmntCount", reCmmntTgrtInfoMap);
		
	}
	
	public void getnewHealthInfoInsert(Map<String, Object> param) throws Exception {
		int boardsn = selectOne("mhc.web.sv.svcmngt.selectBoardSn",param);
		param.put("BOARD_SN",boardsn);
		insert("mhc.web.sv.svcmngt.insertNewHealthInfo", param);
		String[] reCmmntTgrtInfo = param.get("selGroupList").toString().split(",");		
		// 커뮤니티 그룹
		int selGroupListCnt = Integer.parseInt(param.get("selGroupListCnt").toString());
		//if(!param.get("SESS_ORG_CD").equals("K001")){ // 기존코드
		if(!param.get("PROV_ORG").equals("K001")){
			for(int i=0; i < selGroupListCnt; i++){
				param.put("GRP_SN", reCmmntTgrtInfo[i]);
				insert("mhc.web.sv.svcmngt.insertCmntyBoardPostG", param);
			}
		}
		// 군분류
		String[] mclasCd = param.get("selMclasList").toString().split(",");
		int selMclasListCnt = Integer.parseInt(param.get("selMclasListCnt").toString());
		//if(!param.get("SESS_ORG_CD").equals("K001")){ // 기존코드
		// if(!param.get("PROV_ORG").equals("K001")){
			for(int i=0; i < selMclasListCnt; i++){
				param.put("MCLAS_CD", mclasCd[i]);
				insert("mhc.web.sv.svcmngt.insertCmntyBoardPostMclas", param);
			}
		// }
		// 만성분류
		String[] chronic = param.get("selChronicList").toString().split(",");
		int selChronicListCnt = Integer.parseInt(param.get("selChronicListCnt").toString());
		//if(!param.get("SESS_ORG_CD").equals("K001")){ // 기존코드
		if(!param.get("SESS_CHRONIC_ORG").equals("N") || param.get("SESS_ORG_CD").equals("K001")){
			for(int i=0; i < selChronicListCnt; i++){
				param.put("CHRONIC_CD", chronic[i]);
				insert("mhc.web.sv.svcmngt.insertCmntyBoardPostChronic", param);
			}
		}
	}

	public List<Map<String, String>> getSelectAttchFileList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.sv.svcmngt.selectAttchFileList", param);		
		return rsList;  
	}

	public void updateArticlePostClf(Map<String, Object> param) throws Exception {
		update("mhc.web.sv.svcmngt.updateArticlePostClf", param);
	}

	public void getHealthInfoCmmntInsert(Map<String, Object> param) throws Exception {
		insert("mhc.web.sv.svcmngt.insertHealthInfoCmmnt", param);
		update("mhc.web.sv.svcmngt.updateHealthInfoReCmmntCount", param);
	}
	
	public void healthInfoDtlsDelete(Map<String,Object> param) throws Exception{
		delete("mhc.web.sv.svcmngt.deleteHealthInfoDtls", param);
	}
	
	public int getReCnt(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.sv.svcmngt.reCnt", param);
		return rsList;
	}
	
	public int getGoodCnt(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.sv.svcmngt.goodCnt", param);
		return rsList;
	}
	
}
