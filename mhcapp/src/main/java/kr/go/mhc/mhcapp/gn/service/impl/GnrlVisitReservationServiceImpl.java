package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.gn.service.GnrlVisitReservationService;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

@Service("mhcapp.gn.gnrlVisitReservationService")
public class GnrlVisitReservationServiceImpl extends EgovAbstractServiceImpl implements GnrlVisitReservationService{

	@Resource(name="mhcapp.gn.gnrlVisitReservationDAO")
    private GnrlVisitReservationDAO gnrlVisitReservationDAO;
	
	/**
	 * 보건소 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectOrgNmList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlVisitReservationDAO.selectOrgNmList(param);
	}
	
	/**
	 * 방문 달력 설정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectVisitCalendarSet(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlVisitReservationDAO.selectVisitCalendarSet(param);
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
		return gnrlVisitReservationDAO.selectVisitDeTm(param);
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
		return gnrlVisitReservationDAO.insertVisitReservationInfo(param);
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
		return gnrlVisitReservationDAO.selectVisitReservationList(param);
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
		return gnrlVisitReservationDAO.selectVisitReservationCnt(param);
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
		return gnrlVisitReservationDAO.selectResveSttus(param);
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
		gnrlVisitReservationDAO.cancelVisitReservationInfo(param);
	}
	
	/**
	 * 예약 상태 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectOrgCalendarSetCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlVisitReservationDAO.selectOrgCalendarSetCnt(param);
	}
}
