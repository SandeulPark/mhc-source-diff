package kr.go.mhc.common.service;

import java.util.List;
import java.util.Map;

public interface WebponentChartService {

	/**
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 단일 ROW 상세 정보 
	 * @throws Exception 
	 */
	public List<Map<String, String>> getChartData(Map<String, Object> param) throws Exception;
	
}
