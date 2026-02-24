package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.gn.service.GnrlRightPanelService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : GnrlMyHealthMainDashServiceImpl.java
 * @Description : 보편건강 App에서 사용하는 우측메뉴에서 필요한 DAO와 연동 관리하는 Class
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

@Service("mhcapp.gn.GnrlRightPanelService")
public class GnrlRightPanelServiceImpl extends EgovAbstractServiceImpl implements GnrlRightPanelService{
	
	@Resource(name="mhcapp.gn.GnrlRightPanelDAO")
	private GnrlRightPanelDAO gnrlRightPanelDAO;

	/**
	 * 개인정보 수정
	 * @param param 
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updatePrivacyInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlRightPanelDAO.updatePrivacyInfo(param);
	}
	

	/**
	 * 목표설정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> selectObjSet(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRightPanelDAO.selectObjSet(param);
	}
	
	/**
	 * 목표설정 수정
	 * @param param 
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updateObjSet(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlRightPanelDAO.updateObjSet(param);
	}

	/**
	 * 알림내역 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNotificationList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlRightPanelDAO.selectNotificationList(param);
	}
	
	/**
	 * 알림 확인 유무 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updateNotification(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlRightPanelDAO.updateNotification(param);
	}
	
	/**
	 * 알림내역 삭제 
	 */
	@Override
	public int notificationDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlRightPanelDAO.notificationDel(param);
	}
	
	/**
	 * 개인정보 비밀번호 변경
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int myInfoPwdUpdate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlRightPanelDAO.myInfoPwdUpdate(param);
	}
	
	/**
	 * 비밀번호 수정 전 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectChkMyInfo(Map<String, String> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRightPanelDAO.selectChkMyInfo(param);
	}
	
	/**
	 * 새로온 게시물 갯수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectNewCnt(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRightPanelDAO.selectNewCnt(param);
	}
	
	/**
	 * 설정화면 라디오 버튼 변경 값 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updateSetting(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlRightPanelDAO.updateSetting(param);
	}
	
	/**
	 * 개인 프로필 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void updateThumbnail(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlRightPanelDAO.updateThumbnail(param);
	}

	/**
	 * 자체활동측정여부
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateActselfmeasr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlRightPanelDAO.updateActselfmeasr(param);
	}

	/**
	 * 기기 정보 초기화
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@Override
	public void resetEquipInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlRightPanelDAO.resetEquipInfo(param);
	}

	/**
	 * 활동량계 정보 유무 확인
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> selectExistActEquipInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return gnrlRightPanelDAO.selectExistActEquipInfo(param);
	}
}
