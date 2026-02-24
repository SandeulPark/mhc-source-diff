package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.cm.NutriCodeMngtServiceDAO")
public class NutriCodeMngtServiceDAO extends DMultiEgovAbstractMapper {

	/**
	 * 영양코드 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getNutriCodeList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.cm.nutricodemngt.selectNutriCodeList", param);	
		return rsList;  
	}
	
	/**
	 * 영양소 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getNutrientList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.cm.nutricodemngt.selectNutrientList", param);	
		return rsList;  
	}	
	
	/**
	 * 영양코드 신청 관리 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getNutriCodeReqMngtList(Map<String, Object> param) throws Exception {
		String  foodDiv = param.get("SCH_FOOD_DIV").toString();
		
		List<Map<String,String>> rsList = new ArrayList<Map<String, String>>();
		
		//조리식품
		if(foodDiv.equals("01")){
			rsList = selectList("mhc.web.cm.nutricodemngt.selectNutriCodeReqMngtCookList", param);				
		//가공식품	
		}else if(foodDiv.equals("02")){
			rsList = selectList("mhc.web.cm.nutricodemngt.selectNutriCodeReqMngtProcList", param);				
		}
		

		return rsList;  
	}	

	/**
	 * 음식명 중복 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNutrientDupList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.cm.nutricodemngt.selectNutrientDupList", param);	
		return rsList;
	}	
	
	/**
	 * 가공식품 영양코드 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertProcFoodReq(Map<String, Object> param) throws Exception{
		
		Map<String, String> rsMap = selectOne("mhc.web.cm.nutricodemngt.selectNewProcFoodCd", param);
		String newFoodCd = rsMap.get("NEW_FOOD_CD");
		
		param.put("FOOD_CD",        newFoodCd);
		param.put("FOOD_CATE_DTLS", newFoodCd.substring(4, 10));		
		param.put("CLASSIFICATION", newFoodCd.substring(0, 3));

		int rsInt = update("mhc.web.cm.nutricodemngt.insertProcFoodReq", param);	
		update("mhc.web.cm.nutricodemngt.insertNutrient", param);	

		return rsInt;		
	}

	
	/**
	 * 가공식품 영양코드 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateProcFoodReq(Map<String, Object> param) throws Exception{		
		int rsInt = update("mhc.web.cm.nutricodemngt.updateProcFoodReq", param);	
		rsInt = update("mhc.web.cm.nutricodemngt.updateProcFoodReq", param);

		return rsInt;		
	}
	
	/**
	 * 가공식품 영양코드 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateProcFoodReqApprovalYn(Map<String, Object> param) throws Exception{
//		String[] foodCd = param.get("ALL_FOOD_CD").toString().split("\\,");
//		int rsInt = 0;
//		for(int i=0; i<foodCd.length; i++){
//			param.put("FOOD_CD", foodCd[i]);
//			update("mhc.web.cm.nutricodemngt.updateProcFoodReqApprovalYn", param);
//			rsInt ++;
//		}

		int rsInt = 0;
		rsInt = update("mhc.web.cm.nutricodemngt.updateProcFoodReqApprovalYn", param);
		return rsInt;
	}
	
	/**
	 * 첨부 파일 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getNutriAttchFileList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.cm.nutricodemngt.selectNutriAttchFileList", param);	
		return rsList;  
	}	
	
	
	
	
	/**
	 * 조리식품 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertCookFoodReq(Map<String, Object> param) throws Exception{
		
		Map<String, String> rsMap = selectOne("mhc.web.cm.nutricodemngt.selectNewCookFoodReqCd", param);
		String newCookFoodCd = rsMap.get("NEW_COOK_FOOD_CD").toString();
		
		param.put("REQ_COOK_SN",        newCookFoodCd);		
		
		int rsInt = update("mhc.web.cm.nutricodemngt.insertCookFoodReq", param);	
		return rsInt;
	}
	
	
	/**
	 * 조리식품 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateCookFoodReq(Map<String, Object> param) throws Exception{
		
		int rsInt = update("mhc.web.cm.nutricodemngt.updateCookFoodReq", param);			
		return rsInt;		
	}

	
	/**
	 * 조리식품 신청 처리상태 변경
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateCookFoodReqApprovalYn(Map<String, Object> param) throws Exception{
//		String[] foodCd = param.get("ALL_FOOD_CD").toString().split("\\,");
//		int rsInt = 0;
//		for(int i=0; i<foodCd.length; i++){
//			param.put("REQ_COOK_SN", foodCd[i]);
//			update("mhc.web.cm.nutricodemngt.updateCookFoodReqApprovalYn", param);
//			rsInt ++;
//		}

		int rsInt=0;
		rsInt = update("mhc.web.cm.nutricodemngt.updateCookFoodReqApprovalYn", param);
		return rsInt;			
	}
	
	
	/**
	 * 조리식품 일괄신청 데이터 저장
	 * @param param 예비대상자 정보 List
	 * @return totalData
	 * @throws Exception 
	 */
	public int importExcelGridCookReqInsert(List<Map<String, Object>> param) throws Exception{
		
		int dataCount = param.size();
		int InsertCount =0;
		

		for(int i=0; i < dataCount; i++){
			
			Map<String, String> rsMap = selectOne("mhc.web.cm.nutricodemngt.selectNewCookFoodReqCd", param);
			String newCookFoodCd = rsMap.get("NEW_COOK_FOOD_CD").toString();
			
			param.get(i).put("REQ_COOK_SN",        newCookFoodCd);													
			
			insert("mhc.web.cm.nutricodemngt.insertCookFoodReq", param.get(i));
				
			InsertCount ++;
		}

		
		return InsertCount;  
	}

	
	/**
	 * 조리식품 검증 결과 일괄 저장
	 * @param param 예비대상자 정보 List
	 * @return totalData
	 * @throws Exception 
	 */
	public int importExcelGridCookCompInsert(List<Map<String, Object>> param) throws Exception{

		int dataCount = param.size();
		int InsertCount =0;
		
		for(int i=0; i < dataCount; i++){
			
			
			param.get(i).put("CHK_FOOD_CD", param.get(i).get("INS_FOOD_CD"));
			param.get(i).put("CHK_FOOD_NM", param.get(i).get("FOOD_NM"));	
			
			Map<String, String> chkMap = selectOne("mhc.web.cm.nutricodemngt.selectNewCookFoodCompChk", param.get(i));	

			String chk = chkMap.get("CHK_YN").toString();
			
			Map<String, String> rsMap = selectOne("mhc.web.cm.nutricodemngt.selectNewCookFoodCompCd", param.get(i));
			String newCookFoodCd = rsMap.get("NEW_COOK_FOOD_CD").toString();	

			param.get(i).put("FOOD_CD",        	newCookFoodCd);			
			param.get(i).put("CLASSIFICATION",  newCookFoodCd.substring(0, 3));	
			param.get(i).put("FOOD_CATE",     	newCookFoodCd.substring(0, 4));				
			param.get(i).put("FOOD_CATE_DTLS",  newCookFoodCd.substring(4, 10));					
			param.get(i).put("USE_YN",  		'Y');		
			param.get(i).put("APPROVAL_YN",  	'Y');		
			
			
			System.out.println("param :::::: " + param);
			
			System.out.println("chk ::::: " + chk);
			
			//신규 코드 일때
			if(chk.equals("Y")){				
				//완료된 조리식품 코드 저장
				insert("mhc.web.cm.nutricodemngt.insertExcelFoodComp", param.get(i));
				update("mhc.web.cm.nutricodemngt.insertNutrient", param.get(i));	
				
			//동일한 음식코드 및 음식명이 있을 경우 UPDATE	
			}else if(chk.equals("U")){
				insert("mhc.web.cm.nutricodemngt.updateExcelFoodComp", param.get(i));
				update("mhc.web.cm.nutricodemngt.updateNutrient", param.get(i));					
			}			
			
			InsertCount ++;		

		}
		return InsertCount;  
	}

	/**
	 * 신규 코드 중복 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getNewCookFoodCompChk(Map<String, Object> param) throws Exception {
		Map<String,Object> rsMap = selectOne("mhc.web.cm.nutricodemngt.selectNewCookFoodCompChk", param);	
		return rsMap;  
	}	
	
	/**
	 * 조리식품 신청현황 엑셀 다운로드
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getNutriCodeReqMngtExcelList(Map<String, Object> param) throws Exception {

		List<Map<String,String>> rsList = new ArrayList<Map<String, String>>();				
		rsList = selectList("mhc.web.cm.nutricodemngt.selectNutriCodeReqMngtCookList", param);				
		return rsList;  
	}			
	
	/**
	 * 영양코드 엑셀파일을 통한 신규 등록 시 유효성 체크
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getFoodValidChk(Map<String, Object> param) throws Exception {
		
		Map<String,Object> rsMap = new HashMap<String, Object>();
		String div = param.get("DIV").toString();
		
		//음식 카테고리에 대한 유효성 검사
		if(div == "CATE"){
			rsMap = selectOne("mhc.web.cm.nutricodemngt.selectFoodCateValidChk", param);				
		//음식 1회 분량 단위에 대한 유효성 검사	
		}else if(div == "CLF"){
			rsMap = selectOne("mhc.web.cm.nutricodemngt.selectFoodClfValidChk", param);						
		}
		
		


		return rsMap;  
	}		
	
	
	/**
	 * 가공식품 검증 결과 일괄 저장
	 * @param param 예비대상자 정보 List
	 * @return totalData
	 * @throws Exception 
	 */
	public int importExcelGridProcInsert(List<Map<String, Object>> param) throws Exception{

		int dataCount = param.size();
		int InsertCount =0;
		
		for(int i=0; i < dataCount; i++){
			Map<String, String> rsMap = selectOne("mhc.web.cm.nutricodemngt.selectNewProcFoodCd", param.get(i));
			
			String newFoodCd = rsMap.get("NEW_FOOD_CD").toString();	

			param.get(i).put("FOOD_CD",        	newFoodCd);			
			param.get(i).put("CLASSIFICATION",  newFoodCd.substring(0, 3));	
			param.get(i).put("FOOD_CATE",     	newFoodCd.substring(0, 4));				
			param.get(i).put("FOOD_CATE_DTLS",  newFoodCd.substring(4, 10));					
			param.get(i).put("USE_YN",  		'Y');		
			param.get(i).put("APPROVAL_YN",  	'Y');		
			
			//완료된 조리식품 코드 저장
			insert("mhc.web.cm.nutricodemngt.insertExcelFoodComp", param.get(i));
			update("mhc.web.cm.nutricodemngt.insertNutrient", param.get(i));
			InsertCount ++;
		}
		return InsertCount;  
	}	

}
