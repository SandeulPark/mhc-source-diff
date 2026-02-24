package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MissionCodeMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 미션코드 관리업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.11		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.gn.GnrlMissionCodeMngtDAO")
public class GnrlMissionCodeMngtDAO extends DMultiEgovAbstractMapper {
	
	/**
	 * 미션코드관리 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List<Map<String, String>> getMissionCodeList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlmissioncodemngt.selectMissionCodeList", param);
		return rsList;
	}
	
	/**
	 * 미션코드관리 신규 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void getMissionCodeInsert(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlmissioncodemngt.insertMissionCode", param);
	}
	
	/**
	 * 미션코드관리 수정
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	public void getMissionCodeUp(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlmissioncodemngt.updateMissionCode", param);
	}
	
	/**
	 * 미션코드관리 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMissionCodeDtls(Map<String, Object> param) throws Exception { 
		Map<String, Object> rsMap = selectOne("mhc.web.gn.gnrlmissioncodemngt.selectMissionCodeDtls", param);
		return rsMap;
	}
	
	/**
	 * 미션코드관리 삭제	
	 * @param param PK 정보
	 * @return
	 * @throws Exception
	 */
	public void getMissionCodeDel(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlmissioncodemngt.deleteMissionCode", param);
	}
	
	/**
	  *  새 커뮤니티 그룹번호 가져오기
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	public Map<String,Object> selectNewMissionCode(Map<String, Object> param) throws Exception {		
		Map<String,Object> rsMap = selectOne("mhc.web.gn.gnrlmissioncodemngt.selectNewMissionCode", param);
		return rsMap;  
	}

}
