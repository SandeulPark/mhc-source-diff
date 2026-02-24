package kr.go.mhc.common.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import kr.go.mhc.common.DMultiEgovAbstractMapper;

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

	// 첨부_파일 사용 여부 수정
	public int updateAttchFileUseYn(Map<String, Object> param) throws Exception {
		return delete("common.cmmn.updateAttchFileUseYn", param);
	}

	public List<Map<String, Object>> selectAttchFile(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("common.cmmn.selectAttchFile", param);
		return rsList;  
	}
	public void insertCmmnLogInfo(Map<String, Object> param) throws Exception{
		insert("common.cmmn.insertCmmnLogInfo", param);
	}
	
	public void updatePairingLogInfo(Map<String, Object> param) throws Exception{
		update("common.cmmn.updatePairingLogInfo", param);
	}
	
	public void insertAppErrRport(Map<String,String> fileList) throws Exception {
		
		insert("common.cmmn.insertAppErrRport", fileList);

	}

	// 보건소 사용자 정보 조회
	public Map<String, String> selectAppErrRport(Map<String, String> param) throws Exception {
		Map<String, String> rsMap = selectOne("common.cmmn.selectAppErrRport", param);		
		return rsMap;
	}

	// 보편 사용자 정보 조회 
	public Map<String, String> selectAppErrRportGnUser(Map<String, String> param) throws Exception {
		Map<String, String> rsMap = selectOne("common.cmmn.selectAppErrRportGnUser", param);
		return rsMap;
	}

	public void insertAttchFile(Map<String,String> fileList) throws Exception {
		insert("common.cmmn.insertAttchFile", fileList);

	}
}
