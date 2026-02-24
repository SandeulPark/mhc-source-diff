package kr.or.khealth.smhc.smhcapp.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import kr.or.khealth.smhc.smhcapp.cm.service.AppCmmnJobService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : AppCmmnJobDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 공통업무에서 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 * @	2016.06.28		오명빈			알림내역 추가
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.cm.AppCmmnJobService")
public class AppCmmnJobServiceImpl extends EgovAbstractServiceImpl implements AppCmmnJobService{
	
	@Resource(name="mhcapp.cm.AppCmmnJobDAO")
    private AppCmmnJobDAO acjDAO;

	/**
	 * 공지사항 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNoticeList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return acjDAO.selectNoticeList(param);
	}
	
	/**
	 * 공지사항 확인 체크
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String,String>> noticeCnfmChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return acjDAO.noticeCnfmChk(param);
	}
	
	/**
	 * 공지사항 확인 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int updateNoticeCnfm(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return acjDAO.updateNoticeCnfm(param);
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
		return acjDAO.selectNotificationList(param);
	}
	
	/**
	 * 개인정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMyInfoList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return acjDAO.selectMyInfoList(param);
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
		return acjDAO.myInfoPwdUpdate(param);
	}

	/**
	 * 건강설문 실행 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMyInfoServey(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return acjDAO.selectMyInfoServey(param);
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
		return acjDAO.selectChkMyInfo(param);
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
		acjDAO.updateSetting(param);
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
		acjDAO.updateNotification(param);
	}

	@Override
	public void updateThumbnail(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		acjDAO.updateThumbnail(param);
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
		return acjDAO.selectNewCnt(param);
	}

	/**
	 * 알림내역 삭제 
	 */
	@Override
	public int notificationDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return acjDAO.notificationDel(param);
	}
	
	/**
	 * 메인대쉬 편집 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updateMainEdit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		acjDAO.updateMainEdit(param);
	}
	
	/**
	 * 목표설정 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updateObjSet(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		acjDAO.updateObjSet(param);
	}
	
	/**
	 * 닉네임 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updateNickname(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		acjDAO.updateNickname(param);
	}	
	
	/**
	 * 닉네임 사용여부 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updateNicknameUseYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		acjDAO.updateNicknameUseYn(param);
	}		
}
