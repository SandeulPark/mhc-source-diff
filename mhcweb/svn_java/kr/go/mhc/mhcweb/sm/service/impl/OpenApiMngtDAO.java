package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : OpenApiMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 오픈api 게시판 관리하는 DAO Class
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

@Repository("web.sm.OpenApiMngtDAO")
public class OpenApiMngtDAO extends DMultiEgovAbstractMapper {

	/**
	 * 연동신청 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectLinkEquipReqList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.openapimngt.selectLinkEquipReqList", param);		
		return rsList;  
	}
	
	/**
	 * 연동신청 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectLinkEquipReqDtls(Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("mhc.web.sm.openapimngt.selectLinkEquipReqDtls", param);
		return rsMap;
	}
	
	/**
	 * 연동기기 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectLinkEquipModelList(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.openapimngt.selectLinkEquipModelList", param);
		return rsList;
	}
	
	/**
	 * 연동기기 등록 팝업 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectLinkEquipReqPop(Map<String,Object> param) throws Exception{
		String sql = (String) param.get("LINK_CLF");
		Map<String,Object> rsMap = selectOne("mhc.web.sm.openapimngt.selectLinkEquipReqPop_"+sql, param);
		return rsMap;
	}
	
	/**
	 * 연동기기 신청 결과 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateLinkEquipReq(Map<String,Object> param) throws Exception{
		int rsInt = update("mhc.web.sm.openapimngt.updateLinkEquipReq", param);
		return rsInt;
	}
	
	/**
	 * 검증신청 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectVrfcReqList(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.openapimngt.selectVrfcReqList", param);
		return rsList;
	}
	
	/**
	 * 검증신청 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectVrfcReqDtls(Map<String,Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("mhc.web.sm.openapimngt.selectVrfcReqDtls", param);
		return rsMap;
	}
	
	/**
	 * 검증신청 결과 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateVrfcReq(Map<String,Object> param) throws Exception{
		int rsInt = update("mhc.web.sm.openapimngt.updateVrfcReq", param);
		return rsInt;
	}
	
	/**
	 * 연동로그 목록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectPairingLog(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.openapimngt.selectPairingLog", param);
		return rsList;
	}
}
