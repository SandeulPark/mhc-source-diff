package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.VisitReservationService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : VisitReservationServiceImpl.java
 * @Description : 모바일 헬스케어 App에서 사용하는 방문예약에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.06.21		이태석			최초생성
 *
 * @author thejoin
 * @since 2019.06.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.sv.VisitReservationService")
public class VisitReservationServiceImpl extends EgovAbstractServiceImpl implements VisitReservationService{
	
	@Resource(name="mhcapp.sv.VisitReservationDAO")
    private VisitReservationDAO visitReservationDAO;

	/**
	 * 방문 달력 설정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectVisitCalendarSet(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return visitReservationDAO.selectVisitCalendarSet(param);
	}
	
	/**
	 * 방문가능 시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectVisitDeTm(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return visitReservationDAO.selectVisitDeTm(param);
	}
	
	/**
	 * 내 주차에 맞는 방문시간조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectVisitDeTmMyWeek(Map<String, Object> param) throws Exception {
		return visitReservationDAO.selectVisitDeTmMyWeek(param);
	}
	
	/**
	 * 내 현재 주차
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectMyWeek(Map<String, Object> param) throws Exception {
		return visitReservationDAO.selectMyWeek(param);
	}
	
	/**
	 * 예약방문 정보 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> insertVisitReservationInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return visitReservationDAO.insertVisitReservationInfo(param);
	}
	
	/**
	 * 예약 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectVisitReservationList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return visitReservationDAO.selectVisitReservationList(param);
	}
	
	/**
	 * 예약 수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectVisitReservationCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return visitReservationDAO.selectVisitReservationCnt(param);
	}

	/**
	 * 방문예약 취소
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void cancelVisitReservationInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		visitReservationDAO.cancelVisitReservationInfo(param);
	}
	
	/**
	 * 예약 상태 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectResveSttus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return visitReservationDAO.selectResveSttus(param);
	}
}
