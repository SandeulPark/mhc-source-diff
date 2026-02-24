package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;

public interface GnrlSelfMissionMonitoringService {

	/**
	 * 셀프미션모니터링 조회
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 */
	public List<Map<String,Object>> getSelfMissionList(Map<String, Object> param) throws Exception;

	/**
	 * 셀프미션 상세 조회
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 */
	public List<Map<String, Object>> getSelfMissionDtls(Map<String, Object> param) throws Exception;

}
