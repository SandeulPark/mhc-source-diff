package kr.or.khealth.smhc.smhcweb.cm.service;

import java.util.List;
import java.util.Map;

public interface MainService {
	
	/**
	 * 달력 총원 수 조회 
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public List<Map<String, Object>> selectSeniorCalendarMainList(Map<String, Object> param) throws Exception;
	
	/**
	 * 서버 시간 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectServerTime(Map<String,Object> param) throws Exception;
	
	/**
	 * 전체 대상자 수 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectSvcStatusIng(Map<String,Object> param) throws Exception;
	
	/**
	 * 금일 대상자 수 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectTodaySvcStatusReg(Map<String,Object> param) throws Exception;
	
	/**
	 * 달력 클릭 대상자 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSeniorFaceToFaceVisitList(Map<String, Object> param) throws Exception;

	/**
	 * 달력 클릭 대상자 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectTodayMeasrInfo(Map<String, Object> param) throws Exception;

}
