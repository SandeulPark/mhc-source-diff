package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("mhcapp.sv.ServeyDAO")
public class ServeyDAO extends DMultiEgovAbstractMapper{

	/**
	 * 설문 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.servey.selectServeyList", param);	
		return rsList;  
	}
	
	/**
	 * 설문 코드 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyCodeList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.servey.selectServeyCodeList", param);	
		return rsList;  
	}
	
	/**
	 * 설문 마지막 코드 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectServeyLstQnaCD(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.servey.selectServeyLstQnaCD", param);	
		return rsList;  
	}
	
	/**
	 * 설문지 답변 등록 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyAwrInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.servey.serveyAwrInsert", param);	
		return rsList;  
	}
	
	/**
	 * 설문지 마스터 등록 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyMasterInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.servey.serveyMasterInsert", param);	
		return rsList;  
	}
	
	/**
	 * 설문지 영양 체크 리스트 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int serveyAnswrUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.servey.serveyAnswrUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 설문지 마스터 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int updateServeyMaster(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.servey.updateServeyMaster", param);	
		return rsList;  
	}
	
}
