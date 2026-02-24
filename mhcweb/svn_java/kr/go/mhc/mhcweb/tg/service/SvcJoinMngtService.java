package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : SvcJoinMngtService.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여 관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.22		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SvcJoinMngtService {
	
	

	/**
	 * 서비스참여관리 예비대상자 건수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSvcJoinMngtCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 예비대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSvcJoinMngtList(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 상세화면 조회
	 * 기본정보, 선정의뢰정보 중 신장,체중,BMI 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSvcJoinMngtDtlsBasic(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 상세화면 조회
	 * 위험요인 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSvcJoinMngtDtlsRisk(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 상세화면 조회
	 * 상담내역 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSvcJoinMngtDtlsCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 상세화면 조회
	 * 일자에 따른 상담내역 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSvcJoinMngtDtlsCnslDate(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 서비스참여관리 상세화면 조회
	 * 질환정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSvcJoinMngtDtlsDiss(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 update 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateSvcJoinMngt(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 new insert 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void newInsertSvcJoinMngt(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 검진결과 조회 팝업
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSvcJoinMngtRsltPop(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스참여관리 신규 클릭 시 군분류 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSelSvcJoinMngtGunClas(Map<String, Object> param) throws Exception;
	
	//서비스참여관리 군분류 이력 insert
	public void insertHistory(Map<String, Object> param) throws Exception;

//	public int checkCnslClf(Map<String, Object> param)throws Exception;
	
	//유준영 탈락대상자 정보백업 목록 조회
	/**
	 * 탈락대상자 정보백업 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>>droptrageterInfoExcelList(Map<String, Object> param) throws Exception;

	/**
	 * 건강위험요인 변화 결과보기 조회 팝업
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getHealthRiskReducePop(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강 행태 변화 결과보기 조회 팝업
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getHealthBehevImpPop(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getSvcMngtNoForUserId(Map<String, Object> param) throws Exception;

	public int chkDupSvcNo(Map<String, Object> param) throws Exception;

	public void updateTrgterFlag(Map<String, Object> param) throws Exception;
}
