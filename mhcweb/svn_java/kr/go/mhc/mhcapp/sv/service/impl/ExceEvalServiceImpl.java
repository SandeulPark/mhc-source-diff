package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.sv.service.ExceEvalService;

@Service("mhcapp.sv.ExceEvalService")
public class ExceEvalServiceImpl implements ExceEvalService{

	@Resource(name="mhcapp.sv.ExceEvalDAO")
    private ExceEvalDAO nutEvalDAO;
	
	/**
	 * 운동평가1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExceEvalList1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectExceEvalList1(param);
	}
	
	/**
	 * 운동평가1-1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExceEvalList1_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectExceEvalList1_1(param);
	}
	
	/**
	 * 운동평가2 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExceEvalList2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return nutEvalDAO.selectExceEvalList2(param);
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

}
