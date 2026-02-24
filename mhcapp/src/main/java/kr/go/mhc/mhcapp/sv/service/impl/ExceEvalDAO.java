package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("mhcapp.sv.ExceEvalDAO")
public class ExceEvalDAO extends DMultiEgovAbstractMapper{
	

	/**
	 * 평가기간 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPeriod(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.exceeval.selectPeriod", param);	
		return rsList;  
	}
	
	/**
	 * 운동평가1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.exceeval.selectExceEvalList1", param);	
		return rsList;  
	}
	
	/**
	 * 운동평가1-1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList1_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.exceeval.selectExceEvalList1_1", param);	
		return rsMap;  
	}
	
	/**
	 * 운동평가1-3 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList1_3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.exceeval.selectExceEvalList1_3", param);	
		return rsList;  
	}
	
	/**
	 * 운동평가1-2 두번째 차트 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.exceeval.selectExceEvalList2", param);	
		return rsList;  
	}
	
	/**
	 * 운동평가1-2 두번째 차트 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList2_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.exceeval.selectExceEvalList2_1", param);	
		return rsList;  
	}
	
	/**
	 * 운동평가3 결과 칼로리, 운동시간 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.exceeval.selectExceEvalList3", param);	
		return rsMap;
	}
	
	/**
	 * 운동평가3 운동 기록 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList3_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.exceeval.selectExceEvalList3_1", param);	
		return rsList;
	}
	
	
	/**
	 * 운동평가3 일 평균 칼로리 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList3_2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhcapp.sv.exceeval.selectExceEvalList3_2", param);	
		return rsMap;
	}
	
	/**
	 * 운동평가4 평가내용 및 결과 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList4(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.exceeval.selectExceEvalList4", param);	
		return rsMap;
	}
	
	/**
	 * 운동평가4 칼로리 운동 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectExceEvalList4_1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhcapp.sv.exceeval.selectExceEvalList4_1", param);	
		return rsMap;
	}
	
	/**
	 * 서명 sn 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectSignLoad(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.exceeval.selectSignLoad", param);	
		return rsList;  
	}

	/**
	 * 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateEvalGood(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.sv.exceeval.updateEvalGood", param);
	}
	
	/**
	 * 2017.03.03 이태석 추가(사진,동영상 보기)
	 * 운동평가3 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExceEvalList5(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.exceeval.selectExceEvalList5", param);	
		return rsList;  
	}
	
	/**
	 * 집중상담 자동발송 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectAutoSendYn(Map<String, Object> param) throws Exception{		
		Map<String, String> rsMap = selectOne("mhcapp.sv.exceeval.selectAutoSendYn", param);
		return rsMap;
	}			
	
}
