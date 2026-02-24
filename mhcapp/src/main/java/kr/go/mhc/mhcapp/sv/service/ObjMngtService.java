package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface ObjMngtService {
	
	/**
	 * 목표관리_상담 일자 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> selectObjCnsl(Map<String,Object> param) throws Exception;
	
	/**
	 * 목표관리_영양 상담 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjNutMngt(Map<String, Object> param) throws Exception;
	
	/**
	 * 식품섭취군별 권장 섭취 횟수 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectIntakeStnd(Map<String, Object> param) throws Exception;
	
	/**
	 * 목표관리_신체활동 상담 및 목표 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectObjMngtBodyActInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 목표관리_건강관리 상담 및 목표 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectObjMngtHealthMngtInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 목표관리_신체활동 근력 운동 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjMngtBodyPartExcsInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 근력 운동 상세 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBodyPartExcsInfo(Map<String, Object> param) throws Exception;
}
