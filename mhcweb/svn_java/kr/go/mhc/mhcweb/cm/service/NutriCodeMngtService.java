package kr.go.mhc.mhcweb.cm.service;

import java.util.List;
import java.util.Map;


public interface NutriCodeMngtService {

	/**
	 * 영양코드 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNutriCodeList(Map<String, Object> param) throws Exception;

	/**
	 * 영양소 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNutrientList(Map<String, Object> param) throws Exception;	

	/**
	 * 영양소 신청 관리 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNutriCodeReqMngtList(Map<String, Object> param) throws Exception;		

	/**
	 * 영양소 중복 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNutrientDupList(Map<String, Object> param) throws Exception;		
	
	
	/**
	 * 가공식품 영양코드 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertProcFoodReq(Map<String, Object> param) throws Exception;

	
	/**
	 * 가공식품 영양코드 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateProcFoodReq(Map<String, Object> param) throws Exception;	

	
	/**
	 * 가공식품 영양코드 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateProcFoodReqApprovalYn(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 영양소 첨부 파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNutriAttchFileList(Map<String, Object> param) throws Exception;		
	
	
	/**
	 * 조리식품 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertCookFoodReq(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 조리식품 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateCookFoodReq(Map<String, Object> param) throws Exception;

	
	/**
	 * 조리식품 신청 처리상태 변경
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateCookFoodReqApprovalYn(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 조리식품 일괄신청 데이터 저장
	 * @param param 코드 정보List
	 * @return totalData
	 * @throws Exception 
	 */
	public int importExcelGridCookReqInsert(List<Map<String, Object>> param) throws Exception;

	
	/**
	 * 조리식품 검증 결과 일괄 저장
	 * @param param 코드 정보List
	 * @return totalData
	 * @throws Exception 
	 */
	public int importExcelGridCookCompInsert(List<Map<String, Object>> param) throws Exception;
	
	/**
	 * 조리식품 신규 코드 등록 유효성 검사
	 * @param param 코드 정보List
	 * @return totalData
	 * @throws Exception 
	 */	
	public Map<String, Object> getNewCookFoodCompChk(Map<String, Object> param)throws Exception;
	
	/**
	 * 가공식품 신청현황 다운로드
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNutriCodeReqMngtExcelList(Map<String, Object> param) throws Exception;			

	/**
	 * 음식코드 일괄등록 유효성 체크
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public Map<String, Object> getFoodValidChk(Map<String, Object> param) throws Exception;		
	
	
	/**
	 * 가공식품 정보 일괄 저장
	 * @param param 코드 정보List
	 * @return totalData
	 * @throws Exception 
	 */
	public int importExcelGridProcInsert(List<Map<String, Object>> param) throws Exception;	

}


