package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sm.service.OpenApiMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :OpenApiMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 오픈api 게시판에 필요한 DAO와 연동 관리하는 Class
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

@Service("web.sm.OpenApiMngtService")
public class OpenApiMngtServiceImpl extends EgovAbstractServiceImpl implements OpenApiMngtService {

	@Resource(name="web.sm.OpenApiMngtDAO")
	private OpenApiMngtDAO openApiMngtDAO;
	
	 
	/**
	 * 연동신청 리스트 조회
	 */
	@Override
	public List<Map<String, Object>> selectLinkEquipReqList(Map<String, Object> param) throws Exception {
		return openApiMngtDAO.selectLinkEquipReqList(param);
	}
	
	/**
	 * 연동신청 상세 조회
	 */
	@Override
	public Map<String, Object> selectLinkEquipReqDtls(Map<String, Object> param) throws Exception{
		return openApiMngtDAO.selectLinkEquipReqDtls(param);
	}
	
	/**
	 * 연동기기 리스트 조회
	 */
	@Override
	public List<Map<String, Object>> selectLinkEquipModelList(Map<String, Object> param) throws Exception{
		return openApiMngtDAO.selectLinkEquipModelList(param);
	}
	
	/**
	 * 연동기기 등록 팝업 상세 조회
	 */
	@Override
	public Map<String,Object> selectLinkEquipReqPop(Map<String,Object> param) throws Exception{
		return openApiMngtDAO.selectLinkEquipReqPop(param);
	}
	
	/**
	 * 연동기기 신청 결과 저장
	 */
	@Override
	public int updateLinkEquipReq(Map<String,Object> param) throws Exception{
		return openApiMngtDAO.updateLinkEquipReq(param);
	}
	
	/**
	 * 검증신청 리스트 조회
	 */
	@Override
	public List<Map<String, Object>> selectVrfcReqList(Map<String, Object> param) throws Exception{
		return openApiMngtDAO.selectVrfcReqList(param);
	}
	
	/**
	 * 검증신청 상세 조회
	 */
	@Override
	public Map<String, Object> selectVrfcReqDtls(Map<String, Object> param) throws Exception{
		return openApiMngtDAO.selectVrfcReqDtls(param);
	}
	
	/**
	 * 검증신청 결과 저장
	 */
	@Override
	public int updateVrfcReq(Map<String, Object> param) throws Exception{
		return openApiMngtDAO.updateVrfcReq(param);
	}
	
	/**
	 * 연동로그 목록
	 */
	@Override
	public List<Map<String, Object>> selectPairingLog(Map<String, Object> param) throws Exception{
		return openApiMngtDAO.selectPairingLog(param);
	}
}
