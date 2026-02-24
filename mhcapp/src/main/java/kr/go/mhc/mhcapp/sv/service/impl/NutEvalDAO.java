package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : NutEvalDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 영양평가결과 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * 		2016.08.12		오명빈				최초생성
 *
 * @author gst
 * @since 2016.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.sv.NutEvalDAO")
public class NutEvalDAO extends DMultiEgovAbstractMapper{
	

	/**
	 * 영양평가1 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalList1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.nuteval.selectNutEvalList1", param);	
		return rsList;  
	}
	
	/**
	 * 영양평가1 사진 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalAttchFileList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.nuteval.selectNutEvalAttchFileList", param);	
		return rsList;  
	}
	/**
	 * 영양평가2 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalList2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.nuteval.selectNutEvalList2", param);	
		return rsList;  
	}
	/**
	 * 영양평가3 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalList3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.nuteval.selectNutEvalList3", param);	
		return rsList;  
	}
	/**
	 * 2017.03.06 이태석 추가(사진,동영상 보기)
	 * 영양평가4 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNutEvalList4(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.nuteval.selectNutEvalList4", param);	
		return rsList;  
	}
	
	/**
	 * 평가기간 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectEvalPeriod(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.nuteval.selectEvalPeriod", param);
		return rsList;
	}
	
	/**
	 * 끼니별 칼로리 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealCalList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.nuteval.selectMealCalList", param);
		return rsList;
	}
	
	/**
	 * 요일별, 끼니별 칼로리 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectWeekMealCalList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.nuteval.selectWeekMealCalList", param);
		return rsList;
	}
	
	
	/**
	 * 집중상담 자동발송 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectAutoSendYn(Map<String, Object> param) throws Exception{		
		Map<String, String> rsMap = selectOne("mhcapp.sv.nuteval.selectAutoSendYn", param);
		return rsMap;
	}		
	
	
	
}

