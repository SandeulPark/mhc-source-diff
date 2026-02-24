package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


@Repository("mhcapp.sv.RankDAO")
public class RankDAO extends DMultiEgovAbstractMapper{

	/**
	 * 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.rank.selectRankList", param);	
		return rsList;  
	}
	
	/**
	 * 차트 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.rank.selectRankChartList", param);	
		return rsList;  
	}
	
	/**
	 * 상세 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.rank.selectRankDtlsList", param);	
		return rsList;  
	}
}