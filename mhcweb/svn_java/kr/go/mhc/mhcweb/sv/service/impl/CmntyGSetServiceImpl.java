package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.sv.service.CmntyGSetService;

/**
 * @Class Name :CmntyGSetServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 그룹설정에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.18		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sv.CmntyGSetService")
public class CmntyGSetServiceImpl extends EgovAbstractServiceImpl implements CmntyGSetService{

	@Resource(name="web.sv.CmntyGSetDAO")
	private CmntyGSetDAO cmntyGSetDAO;
	
	/**
	  * 그룹 목록 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	@Override
	public List<Map<String, String>> getCmntyGList(Map<String, Object> param) throws Exception {
		return cmntyGSetDAO.getCmntyGList(param);
	}

	/**
	  * 그룹 대상자 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public List<Map<String, String>> getCmntyGTrgterList(Map<String, Object> param) throws Exception {
		return cmntyGSetDAO.getCmntyGTrgterList(param);
	}

	/**
	  *  새 커뮤니티 그룹번호 가져오기
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public Map<String,Object> getNewCmntyGSn(Map<String, Object> param) throws Exception {
		return cmntyGSetDAO.getNewCmntyGSn(param);
	}

	/**
	  *  그룹 등록
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public void getCmntyGInsert(Map<String, Object> param) throws Exception {
		cmntyGSetDAO.getCmntyGInsert(param);
	}

	/**
	  * 그룹 수정
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	@Override
	public void getCmntyGUpd(Map<String, Object> param) throws Exception {
		cmntyGSetDAO.getCmntyGUpd(param);
	}

	/**
	  * 그룹 제외
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public void getCmntyGUseN(Map<String, Object> param) throws Exception {
		cmntyGSetDAO.getCmntyGUseN(param);
	}

	/**
	 * 대상자 제외 (Update)  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void getCmntyGTrgterUseN(Map<String, Object> param) throws Exception {
		cmntyGSetDAO.getCmntyGTrgterUseN(param);		
	}
	
	/**
	  * 관리군 목록 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public List<Map<String, String>> getGclasList() throws Exception {
		return cmntyGSetDAO.getGclasList();
	}
	
	/**
	  * 추가 대상자 조회 (팝업)
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public List<Map<String, String>> getAddTrgterList(Map<String, Object> param) throws Exception {
		return cmntyGSetDAO.getAddTrgterList(param);
	}
	

	/**
	 * 대상자 추가  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void getCmntyGTrgterInsert(Map<String, Object> param) throws Exception {
		cmntyGSetDAO.getCmntyGTrgterInsert(param);
	}
	
	/**
	 * 그룹 삭제
	 */
	@Override
	public void delCmntyGroup(Map<String, Object> param) {
		cmntyGSetDAO.delCmntyGroup(param);	
	}
	
	/**
	 * 그룹 대상자 삭제
	 */
	@Override
	public void delCmntyGroupMember(Map<String, Object> param) {
		cmntyGSetDAO.delCmntyGroupMember(param);		
	}
}
