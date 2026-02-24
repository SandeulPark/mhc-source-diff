package kr.or.khealth.smhc.common.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import kr.or.khealth.smhc.common.util.StringUtil;

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

	public List<Map<String, String>> selectCmmnCd(Map<String, Object> param) throws Exception {
		
		List<Map<String,String>> rsList = null;
		String sCmmnCd = StringUtil.nvl(String.valueOf(param.get("CMMN_CD")));
		
		System.out.println("sCmmnCd ::: " + sCmmnCd);
		
		if(!"".equals(sCmmnCd)){
			if("TC_CM_AUTH".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectAuthCd", param);
			}else if("TC_CM_ORG".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectOrgCd", param);
			}else if("TC_SV_SVC_SCH_CD".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectSvcSchCd", param);
			}else if("TC_CM_CMMN_CD_LCLAS".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectLclasCd", param);
			//담당자 정보 조회
			}else if("TN_CM_MANAGER_INFO".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectMgrInfo", param);				
			}else if("TN_CM_CMMN_CD_SCALS_NN".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectTelNumBer", param);				
			}else if ("TC_CM_ORG_SIDO".equals(sCmmnCd)){
				rsList = selectList("common.cmmn.selectOrgForSido", param);
			}else{
				rsList = selectList("common.cmmn.selectCmmnCd", param);
			}
		}
		
		return rsList;  
	}
	
	public List<Map<String, String>> selectCmmnMenu(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = null;

		String SESS_ISMOBILE = (String) param.get("SESS_ISMOBILE");
		
		System.out.println("SESS_ISMOBILE :::::::: " + SESS_ISMOBILE);
		
		
		System.out.println("SESS_ISMOBILE ::: " + SESS_ISMOBILE);
		
//		if(SESS_ISMOBILE == null || "".equals(SESS_ISMOBILE)){
//			System.out.println("1111111");
			
			rsList = selectList("common.cmmn.selectCmmnMenuWeb", param);
//		}else{
//			
//			System.out.println("22222222222222");
//			
//			rsList = selectList("common.cmmn.selectCmmnMenu", param);
//		}
		return rsList;
	}
	
	public Map<String, String> selectCmmnMenuInfo(Map<String, Object> param) throws Exception {
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

	public List<Map<String, Object>> selectAttchFile(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("common.cmmn.selectAttchFile", param);
		return rsList;  
	}
	public void insertCmmnLogInfo(Map<String, Object> param) throws Exception{
		insert("common.cmmn.insertCmmnLogInfo", param);
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

	public Map<String, Object> idChk(Map<String, Object> param) throws Exception{
		return selectOne("common.cmmn.idChk", param);
	}	

	public Map<String,Object> selectCmmnCdUseYn(Map<String, Object> param) throws Exception {
		return selectOne("common.cmmn.selectCmmnCdUseYn", param);
	}
}
