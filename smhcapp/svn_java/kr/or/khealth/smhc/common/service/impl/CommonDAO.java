package kr.or.khealth.smhc.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

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
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("common.cmmn.selectCmmnCd", param);
		return rsList;  
	}
	
	public List<Map<String, String>> selectCmmnMenu(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("common.cmmn.selectCmmnMenu", param);
		return rsList;
	}
	
	public Map<String, String> selectCmmnMenuInfo(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("common.cmmn.selectCmmnMenuInfo", param);
		return rsMap;  
	}
	
	public String selectAttchFileSnSeq() throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("common.cmmn.selectAttchFileSnSeq");
		return String.valueOf(rsMap.get("ATTCH_FILE_SN_SEQ"));  
	}
	
	public String selectAttchFileDtlsSn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("common.cmmn.selectAttchFileDtlsSn", param);
		return String.valueOf(rsMap.get("ATTCH_FILE_DTLS_SN"));
	}
	
	public int insertAttchFile(List<Map<String,String>> fileList) throws Exception {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return delete("common.cmmn.deleteAttchFileInfo", param);
	}

	public List<Map<String, Object>> selectAttchFile(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("common.cmmn.selectAttchFile", param);
		return rsList;  
	}
	public void insertCmmnLogInfo(Map<String, Object> param) throws Exception{
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String tableName = "TN_CM_CONNECT_LOG_" + dateFormat.format(today);
		param.put("TABLE_NAME", tableName);
		insert("common.cmmn.insertCmmnLogInfo", param);
	}
	
	public void updatePairingLogInfo(Map<String, Object> param) throws Exception{
		update("common.cmmn.updatePairingLogInfo", param);
	}
	
	public Map<String, Object> getBloodSugar(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("common.cmmn.getBloodSugar",param);
	}
	
	public Map<String, Object> getBloodPress(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("common.cmmn.getBloodPress",param);
	}
	
	public Map<String, Object> getWeight(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("common.cmmn.getWeight",param);
	}
	
	public Map<String, Object> getActCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("common.cmmn.getActCnt",param);
	}
	
	public Map<String, Object> getActTime(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectOne("common.cmmn.getActTime",param);
	}
	
	public Map<String, Object> getPoint(Map<String, Object> param) {
		return selectOne("common.cmmn.getPoint",param);
	}
	
	public List<String> remainMission(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return selectList("common.cmmn.remainMission",param);
	}
	
	public Map<String,Object> selectCmmnCdUseYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return selectOne("common.cmmn.selectCmmnCdUseYn", param);
	}
}
