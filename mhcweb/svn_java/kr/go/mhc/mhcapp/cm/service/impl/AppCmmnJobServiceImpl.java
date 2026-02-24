package kr.go.mhc.mhcapp.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.cm.service.AppCmmnJobService;
import kr.go.mhc.mhcapp.cm.service.impl.AppCmmnJobDAO;

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
	 * 월간리포트 유무 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public int reportNullChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return acjDAO.reportNullChk(param);
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
	 * 푸쉬상태 on off
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updatePushSttus(Map<String, Object> param) throws Exception {
		acjDAO.updatePushSttus(param);
		
	}


	/**
	 * 신체상태 on off
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void updateBodySttus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		acjDAO.updateBodySttus(param);
	}

	@Override
	public void updateThumbnail(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		acjDAO.updateThumbnail(param);
	}

}
