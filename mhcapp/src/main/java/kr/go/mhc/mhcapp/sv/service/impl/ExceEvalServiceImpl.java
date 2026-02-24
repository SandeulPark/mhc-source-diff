package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.sv.service.ExceEvalService;

@Service("mhcapp.sv.ExceEvalService")
public class ExceEvalServiceImpl implements ExceEvalService{

	@Resource(name="mhcapp.sv.ExceEvalDAO")
    private ExceEvalDAO exceEvalDAO;
	
	/**
	 * 평가 기간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectPeriod(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectPeriod(param);
	}
	
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
		return exceEvalDAO.selectExceEvalList1(param);
	}
	
	/**
	 * 운동평가1-1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectExceEvalList1_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList1_1(param);
	}
	
	/**
	 * 운동평가1-3 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExceEvalList1_3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList1_3(param);
	}
	
	/**
	 * 운동평가2 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExceEvalList2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList2(param);
	}
	
	/**
	 * 운동평가2-1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExceEvalList2_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList2_1(param);
	}
	
	/**
	 * 운동평가3 결과 칼로리, 운동시간 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectExceEvalList3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList3(param);
	}
	
	/**
	 * 운동평가3 운동 기록 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExceEvalList3_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList3_1(param);
	}
	
	/**
	 * 운동평가3 일평균 칼로리 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectExceEvalList3_2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList3_2(param);
	}
	
	/**
	 * 운동평가4 평가내용 및 결과 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectExceEvalList4(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList4(param);
	}
	
	/**
	 * 운동평가4 칼로리 운동 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectExceEvalList4_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList4_1(param);
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
		return exceEvalDAO.selectSignLoad(param);
	}

	/**
	 * 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateEvalGood(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		exceEvalDAO.updateEvalGood(param);
	}

	/**
	 * 2017.03.03 이태석 추가(사진,동영상 보기)
	 * 운동평가5 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectExceEvalList5(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return exceEvalDAO.selectExceEvalList5(param);
	}
	
	
	/**
	 * 집중상담 자동발송 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectAutoSendYn(Map<String, Object> param) throws Exception{
		return exceEvalDAO.selectAutoSendYn(param);
	}		
	
}
