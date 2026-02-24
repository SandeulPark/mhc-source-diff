package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

/**
 * @Class Name : GnrlMyhealthMainDashDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 우측메뉴 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.08.30		이태석			최초생성
 * 		
 * @author thejoin
 * @since 2019.08.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.gn.GnrlRightPanelDAO")
public class GnrlRightPanelDAO extends DMultiEgovAbstractMapper{

	/**
	 * 개인정보 수정
	 * @param param
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updatePrivacyInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.gn.rightpanel.updateCmUserInfo", param);
		update("mhcapp.gn.rightpanel.updateGnUserInfo", param);
	}
	
	/**
	 * 목표설정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, Object> selectObjSet(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhcapp.gn.rightpanel.selectObjSet", param);	
		return rsMap;  
	}
	
	/**
	 * 목표설정 수정
	 * @param param 
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateObjSet(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.gn.rightpanel.updateObjSet", param);
	}
	
	/**
	 * 알림내역 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNotificationList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.gn.rightpanel.selectNotificationList", param);	
		return rsList;  
	}
	
	/**
	 * 알림 확인 유무 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateNotification(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.gn.rightpanel.updateNotification", param);	
	}
	
	/**
	 * 알림내역 삭제 
	 */
	public int notificationDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.gn.rightpanel.notificationDel", param);	
		return rsList;  
	}
	
	/**
	 * 비밀번호 변경
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int myInfoPwdUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.gn.rightpanel.myInfoPwdUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 비밀번호 수정 전 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectChkMyInfo(Map<String, String> param) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.gn.rightpanel.selectChkMyInfo", param);	
		return rsList;
	}

	/**
	 * 새로온 게시물 갯수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectNewCnt(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsList = selectOne("mhcapp.gn.rightpanel.selectNewCnt", param);	
		return rsList;  
	}
	
	/**
	 * 설정화면 라디오 버튼 변경 값 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSetting(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.gn.rightpanel.updateSetting", param);	
	}
	
	/**
	 * 개인 프로필 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	public void updateThumbnail(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.gn.rightpanel.updateThumbnail", param);	
	}

	/**
	 * 자체활동측정여부
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateActselfmeasr(Map<String, Object> param) {
		update("mhcapp.gn.rightpanel.updateActselfmeasr", param);
	}
	
	/**
	 * 기기 정보 초기화
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public void resetEquipInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.gn.rightpanel.resetEquipInfo", param);
	}

	/**
	 * 활동량계 정보 유무 확인
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> selectExistActEquipInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhcapp.gn.rightpanel.selectExistActEquipInfo", param);	
		return rsMap;  
	}
}
