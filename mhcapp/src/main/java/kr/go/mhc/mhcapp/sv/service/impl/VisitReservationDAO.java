package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : CommunityDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 방문예약에 DataBase 연동 관리하는 Class
 * @Modification Information @ @ 수정일 수정자 수정내용 
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.06.21		이태석			최초생성
 *
 * @author thejoin
 * @since 2019.06.21
 * @version 1.0
 * @see Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.sv.VisitReservationDAO")
public class VisitReservationDAO extends DMultiEgovAbstractMapper {

	/**
	 * 방문 달력 설정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectVisitCalendarSet(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.visitreservation.selectVisitCalendarSet", param);	
		return rsList;  
	}
	
	/**
	 * 방문가능 시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectVisitDeTm(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.visitreservation.selectVisitDeTm", param);	
		return rsList;  
	}
	
	/**
	 * 방문가능 시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectVisitDeTmMyWeek(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.visitreservation.selectVisitDeTmMyWeek", param);	
		return rsMap;  
	}	
	
	/**
	 * 내 현재 주차
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectMyWeek(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.visitreservation.selectMyWeek", param);	
		return rsMap;  
	}
	
	
	/**
	 * 예약방문 정보 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> insertVisitReservationInfo(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.visitreservation.selectReservationYn", param);	
		if(rsMap.get("RESERVATION_YN").equals("Y")){
			insert("mhcapp.sv.visitreservation.insertVisitReservationInfo", param);
			param.put("RESVERT_STTUS", "04");
			insert("mhcapp.sv.visitreservation.insertVisitReservationInfoHist", param);
		}
		return rsMap;  
	}
	
	/**
	 * 예약 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectVisitReservationList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.visitreservation.selectVisitReservationList", param);	
		return rsList;  
	}
	
	/**
	 * 예약 수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectVisitReservationCnt(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.visitreservation.selectVisitReservationCnt", param);	
		return rsMap;  
	}
	
	/**
	 * 방문예약 취소
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public void cancelVisitReservationInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.sv.visitreservation.cancelVisitReservationInfo", param);
		param.put("RESVERT_STTUS", "05");
		insert("mhcapp.sv.visitreservation.insertVisitReservationInfoHist", param);
	}
	
	/**
	 * 예약 상태 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectResveSttus(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.visitreservation.selectResveSttus", param);	
		return rsMap;  
	}
}
