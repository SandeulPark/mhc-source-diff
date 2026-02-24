package kr.or.khealth.smhc.smhcweb.sv.service;


import java.util.List;
import java.util.Map;

/**
 * @Class Name : ErrDataSttusService.java
 * @Description :  에러 데이터 현황 정보를 조회하는 서비스 interface
 * @Modification Information
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface ErrDataSttusService {

    // 에러 데이터 현황 연동 건수별 리스트 조회
   	public List<Map<String, String>> errDataSyncList(Map<String, Object> param) throws Exception;

    // 에러 데이터 현황 앱 버전별 리스트 조회
   	public List<Map<String, String>> errDataAppVerList(Map<String, Object> param) throws Exception;

    // 에러 데이터 현황 업체별 리스트 조회
   	public List<Map<String, String>> errDataModelList(Map<String, Object> param) throws Exception;

	// 에러 데이터 현황 오류 코드별 리스트 조회
	public List<Map<String, String>> errDataCodeList(Map<String, Object> param) throws Exception;
}

