package kr.go.mhc.mhcapp.mr.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : AppCeneralCnslDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-일반상담 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * 		2016.06.29		오명빈				최초생성
 *
 * @author gst
 * @since 2016.06.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.mr.AppGeneralCnslDAO")
public class AppGeneralCnslDAO extends DMultiEgovAbstractMapper{

	/**
	 * 일반상담 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectGeneralCnslList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.mr.myhealth.selectGeneralCnslList", param);	
		return rsList;  
	}
	
	/**
	 * 일반상담 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCnslDetailList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.mr.myhealth.selectCnslDetailList", param);	
		return rsList;  
	}

	/**
	 * 일반상담 상세 첨부파일 목록 조회 - 첨부파일 조회 20231107
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectCnslDetailAttchFiles(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhcapp.mr.myhealth.selectCnslDetailAttchFiles", param);
		System.out.println("#######################$@$#$@#$@$@#$@#$@#$#$#@#$@#@#$##@  2 ");
		return rsList;
	}

	/**
	 * 일반상담 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public int insertGeneralCnsl(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		insert("mhcapp.mr.myhealth.insertGeneralCnsl", param);
		int sn = (Integer) param.get("cnslSn");
		return sn; 
	}
	
	/**
	 * 일반상담 확인유무 업데이트
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public void updateCnslCnfm(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.mr.myhealth.updateCnslCnfm", param);	
	}
	
	/**
	 * 일반상담 수정
	 * @param param
	 * @throws Exception
	 */
	public void updateGeneralCnsl(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		update("mhcapp.mr.myhealth.updateGeneralCnsl", param);
	}
	
	/**
	 * 일반상담 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteGeneralCnsl(Map<String, Object> param) throws Exception{
		delete("mhcapp.mr.myhealth.deleteGeneralCnsl", param);
	}
	
	 /**
	 * 집중상담 관련 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public int insensiveCnslQuesInsert(Map<String, Object> param) throws Exception{
		int rsInt = insert("mhcapp.mr.myhealth.insensiveCnslQuesInsert", param);
		return rsInt;
	}		
	
	 /**
	 * 집중상담 관련 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public Map<String, Object> selectIntensCnslQuesDtls(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhcapp.mr.myhealth.selectIntensCnslQuesDtls", param);
		return rsMap;
	}		
	
	
	
}
