package kr.go.mhc.common.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;
import kr.go.mhc.common.util.StringUtil;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : CommonDAO.java
 * @Description : 모바일 헬스케어에서 사용하는 통합공통업무 DataBase 연동 관리하는 Class
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

@Repository("common.cmmnDAO")
public class CommonDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, String>> selectCmmnCd(Map<String, Object> param)
			throws Exception {
		
		List<Map<String,String>> rsList = null;
		String sCmmnCd = StringUtil.nvl(String.valueOf(param.get("CMMN_CD")));
		if(!"".equals(sCmmnCd)){
			if("TC_CM_AUTH".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectAuthCd", param);
			}else if("TC_CM_ORG".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectOrgCd", param);
			// 만성질환 보건소 조회 추가  
			}else if("TC_CM_ORG_CHRONIC".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectChronicOrgCd", param);
			}else if("TC_SV_SVC_SCH_CD".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectSvcSchCd", param);
			}else if("TC_CM_CMMN_CD_LCLAS".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectLclasCd", param);
			//주차 정보 조회 추가	
			}else if("TC_CM_HLDY".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectWeekNm", param);				
			//음식 카테고리 정보 조회 추가
			}else if("TC_CM_FOOD_CATE".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectFoodCate", param);				
			//그룹코드 정보 조회 추가
			}else if("TN_SV_CMNTY_GRP".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectCmntyGrpSn", param);					
			}else if("TC_MS_EXCS_CD_RECOM".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectExcsCdRecom", param);
			// 보건소 군분류 조회 추가
			}else if("TC_SV_HEALTH_MNGT_OBJ".equals(sCmmnCd)) {
				rsList = selectList("common.cmmn.selectMclasCd", param);
			// 시도 검색용 기관 조회 추가(보건소 + 개발원)
			}else if ("TC_CM_ORG_SIDO".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectOrgForSido", param);
			}else{
				rsList = selectList("common.cmmn.selectCmmnCd", param);
			}
		}
		
		return rsList;  
	}
	
	public List<Map<String, String>> selectCmmnMenu(Map<String, Object> param)
			throws Exception {
		
		List<Map<String,String>> rsList = null;
		if(param.get("isMobile")!=null && "mobile".equals(param.get("isMobile"))){
			rsList = selectList("common.cmmn.selectCmmnMenu", param);
		}else{
			if((param.get("SESS_ISMOBILE")!=null && "mobile".equals(param.get("SESS_ISMOBILE"))) || 
					(param.get("SESS_ISMOBILE")!=null && "mobileAPP".equals(param.get("SESS_ISMOBILE")))){
				rsList = selectList("common.cmmn.selectCmmnMenu", param);
			}else{
				rsList = selectList("common.cmmn.selectCmmnMenuWeb", param);
			}
		}
		return rsList;
	}
	
	public Map<String, String> selectCmmnMenuInfo(Map<String, Object> param)
			throws Exception {
		
		Map<String,String> rsMap = selectOne("common.cmmn.selectCmmnMenuInfo", param);
		return rsMap;  
	}
	
	public String selectAttchFileSnSeq() throws Exception {
		
		Map<String,String> rsMap = selectOne("common.cmmn.selectAttchFileSnSeq");
		return String.valueOf(rsMap.get("ATTCH_FILE_SN_SEQ"));  
	}
	
	public String selectAttchFileDtlsSn(Map<String, Object> param) throws Exception {
		
		Map<String,String> rsMap = selectOne("common.cmmn.selectAttchFileDtlsSn", param);
		return String.valueOf(rsMap.get("ATTCH_FILE_DTLS_SN"));
	}
	
	public int insertAttchFile(List<Map<String,String>> fileList) throws Exception {
		

		int nCnt = 0;
		Map<String,String> fileMap;
		Iterator<Map<String,String>> iter = fileList.iterator();
		while (iter.hasNext()) {
			fileMap = iter.next();

			nCnt += insert("common.cmmn.insertAttchFile", fileMap);
		}

		return nCnt;
	}
	
	public int deleteAttchFileInfo(Map<String, Object> param) throws Exception {
		
		return delete("common.cmmn.deleteAttchFileInfo", param);
	}
	
	public int updateAttchFileUseYn(Map<String, Object> param) throws Exception {
		
		return delete("common.cmmn.updateAttchFileUseYn", param);
	}

	public List<Map<String, Object>> selectAttchFile(Map<String, Object> param)
			throws Exception {
		
		List<Map<String,Object>> rsList = selectList("common.cmmn.selectAttchFile", param);
		return rsList;  
	}
	public void insertCmmnLogInfo(Map<String, Object> param) throws Exception{
		insert("common.cmmn.insertCmmnLogInfo", param);
		update("common.cmmn.updateLastAccessDt", param);		// 마지막 접속 시간 update
	}
	
	public void insertVideoAttchFile(Map<String, Object> param) throws Exception {
		insert("common.cmmn.insertVideoAttchFile", param);
	}
	
	public void deleteVideoAttchFile(Map<String, Object> param) throws Exception {
		update("common.cmmn.deleteVideoAttchFile", param);
	}
	
	//현재 주차 정보 조회
	public Map<String, Object> selectTodayWeekNm(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("common.cmmn.selectTodayWeekNm", param);		
		return rsMap;
	}	
	
	//개인정보 조회 이력 정보
	public void insertCmmnLogPerSchInfo(Map<String, Object> param) throws Exception{
		insert("common.cmmn.insertCmmnLogPerSchInfo", param);
	}	
	
	public void updateAttchFileNoUseAll(Map<String, Object> param) throws Exception{
		update("common.cmmn.updateAttchFileNoUseAll", param);
	}		
	
	public void updateAttchFileUse(Map<String, Object> param) throws Exception{
		update("common.cmmn.updateAttchFileUse", param);
	}
	
	public List<Map<String, String>> selectMenuUpCd(Map<String, Object> param)
			throws Exception {
		
		List<Map<String,String>> rsList = null;		
		rsList = selectList("common.cmmn.selectMenuUpCd", param);
		
		return rsList;							
		
	}
	
	public List<Map<String, String>> selectMenuCd(Map<String, Object> param)
			throws Exception {
		
		List<Map<String,String>> rsList = null;		
		rsList = selectList("common.cmmn.selectMenuCd", param);
		
		return rsList;							
		
	}

	public void insertDownloadRsn(Map<String, Object> param) {
		// TODO Auto-generated method stub
		insert("common.cmmn.insertDownloadRsn", param);
	}
	
}
