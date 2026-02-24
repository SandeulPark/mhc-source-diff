package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface RankService {

	/**
	 * 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankList(Map<String, Object> param) throws Exception;
	
	/**
	 * 차트 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 상세 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankDtlsList(Map<String, Object> param) throws Exception;
}
