package kr.go.mhc.mhcapp.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.mr.service.AppGeneralCnslService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : AppGeneralCnslDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-일반상담에서 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.28		오명빈			최초생성
 *
 * @author gst
 * @since 2016.06.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.mr.AppGeneralCnslService")
public class AppGeneralCnslServiceImpl extends EgovAbstractServiceImpl implements AppGeneralCnslService{
	
	@Resource(name="mhcapp.mr.AppGeneralCnslDAO")
    private AppGeneralCnslDAO agcDAO;

	/**
	 * 일반상담 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectGeneralCnslList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return agcDAO.selectGeneralCnslList(param);
	}
	
	/**
	 * 일반상담 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectCnslDetailList(
			Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return agcDAO.selectCnslDetailList(param);
	}

	/**
	 * 일반상담 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@Override
	public void insertGeneralCnsl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		agcDAO.insertGeneralCnsl(param);
	}
	
	/**
	 * 일반상담 확인유무 업데이트
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@Override
	public void updateCnslCnfm(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		agcDAO.updateCnslCnfm(param);
	}

}
