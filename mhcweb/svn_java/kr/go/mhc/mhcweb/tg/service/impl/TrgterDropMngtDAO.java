package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : TrgterDropMngtService.java
 * @Description : 관리자 WEB에서 사용하는 대상자탈락관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.07.12		이태석			최초생성
 *
 * @author thejoin
 * @since 2018.07.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.TrgterDropMngtDAO")
public class TrgterDropMngtDAO extends EgovAbstractMapper{
	//대상자정보관리 대상자 목록 조회
	public List<Map<String, Object>> trgterDropMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterdropmngt.trgterDropMngtList", param);
		return rsList;
	}
		
	//대상자정보관리 중도탈락 업데이트
	public void updateTrgterDrop(Map<String, Object> param) throws Exception {
		insert("mhc.web.tg.trgterdropmngt.insertSvcJoinMngtInfoHist", param);		//현재 상태 이력에 저장
		update("mhc.web.tg.trgterdropmngt.updateSvcJoinMngtDrop", param);		//서비스참여 상태 변경
		update("mhc.web.tg.trgterdropmngt.updatePreTrgterInfoDrop", param);		//예비대상자 상태 변경 '80'
		delete("mhc.web.tg.trgterdropmngt.dropTrgterAllCmntyGrpMbDel", param);	//보건소 커뮤니티 모든 그룹 맴버 삭제 
	}
	
	//대상자정보관리 중도탈락 조회
	public Map<String, Object> selectTrgterDrop(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterdropmngt.selectSvcJoinMngtDrop", param);
		return rsMap;
	}
	
	//대상자정보관리 중도탈락 취소
	public Map<String, Object> cancelTrgterDrop(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterdropmngt.selecSvcJoinMngtDropHist", param);	//중도탈락 이전 TRGTER_STTUS 값 가져옴. 
		insert("mhc.web.tg.trgterdropmngt.insertSvcJoinMngtInfoHist", param);		//현재 상태 이력에 저장
		param.put("TRGTER_STTUS", rsMap.get("TRGTER_STTUS"));
		update("mhc.web.tg.trgterdropmngt.updateSvcJoinMngtCancelDrop", param);	//서비스참여 상태 변경
		update("mhc.web.tg.trgterdropmngt.updatePreTrgterInfoDrop", param);		//예비대상자 상태 변경 '50'
		insert("mhc.web.tg.trgterdropmngt.dropTrgterCmntyGrpMbAdd", param);		//보건소 커뮤니티 기본 그룹 맴버 추가 
		return rsMap;
	}	
}
