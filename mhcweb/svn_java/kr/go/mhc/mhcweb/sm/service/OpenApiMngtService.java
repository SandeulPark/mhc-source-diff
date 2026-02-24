package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : OpenApiMngtService.java
 * @Description : 관리자 WEB에서 사용하는 오픈api 게시판 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------	------	---------------------------
 * @	2017.11.27	나연이		최초생성
 *
 * @author theJoin
 * @since 2017.11.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface OpenApiMngtService {
	
	/**
	 * 연동신청 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectLinkEquipReqList(Map<String, Object> param) throws Exception;
	
	/**
	 * 연동신청 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectLinkEquipReqDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 연동기기 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectLinkEquipModelList(Map<String, Object> param) throws Exception;
	
	/**
	 * 연동기기 등록 팝업 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectLinkEquipReqPop(Map<String,Object> param) throws Exception;
	
	/**
	 * 연동기기 신청 결과 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateLinkEquipReq(Map<String, Object> param) throws Exception;
	
	/**
	 * 검증신청 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectVrfcReqList(Map<String,Object> param) throws Exception;
	
	/**
	 * 검증신청 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectVrfcReqDtls(Map<String,Object> param) throws Exception;
	
	/**
	 * 검증신청 결과 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateVrfcReq(Map<String,Object> param) throws Exception;
	
	/**
	 * 연동로그 목록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectPairingLog(Map<String,Object> param) throws Exception;
}
