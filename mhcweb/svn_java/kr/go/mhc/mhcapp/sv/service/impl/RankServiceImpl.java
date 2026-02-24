package kr.go.mhc.mhcapp.sv.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.RankService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;



@Service("mhcapp.sv.RankService")
public class RankServiceImpl extends EgovAbstractServiceImpl implements RankService{
	
	@Resource(name="mhcapp.sv.RankDAO")
    private RankDAO rankDAO;

	/**
	 * 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return rankDAO.selectRankList(param);
	}
	/**
	 * 차트 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return rankDAO.selectRankChartList(param);
	}
	
	/**
	 * 상세 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return rankDAO.selectRankDtlsList(param);
	}

}