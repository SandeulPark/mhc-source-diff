package kr.go.mhc.mhcapp.sv.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.sv.service.NutEvalService;

@Service("mhcapp.sv.NutEvalService")
public class NutEvalServiceImpl implements NutEvalService{

	@Resource(name="mhcapp.sv.NutEvalDAO")
    private NutEvalDAO nutEvalDAO;
	
	/**
	 * 영양평가1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNutEvalList1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectNutEvalList1(param);
	}
	/**
	 * 영양평가2 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNutEvalList2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectNutEvalList2(param);
	}
	/**
	 * 영양평가3 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectNutEvalList3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectNutEvalList3(param);
	}
	/**
	 * 서명 sn 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectSignLoad(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectSignLoad(param);
	}
	
	/**
	 * 영양평가 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int updateNutEvalLike(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.updateNutEvalLike(param);
	}
}
