package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.SvcJoinMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :SvcJoinMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여 관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.22		이은주			최초생성
 
 * @author gst
 * @since 2016.08.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.SvcJoinMngtService")
public class SvcJoinMngtServiceImpl extends EgovAbstractServiceImpl implements SvcJoinMngtService {
	
	@Resource(name= "web.tg.SvcJoinMngtDAO")
	private SvcJoinMngtDAO svcJoinMngtDAO;

	//서비스참여관리 예비대상자 건수 조회
	@Override
	public Map<String, Object> getSvcJoinMngtCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getSvcJoinMngtCnt(param);
	}

	//서비스참여관리 예비대상자 목록 조회
	@Override
	public List<Map<String, Object>> getSvcJoinMngtList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = svcJoinMngtDAO.getSvcJoinMngtList(param);
		return rsList;
	}

	//서비스참여관리 상세화면 조회 기본정보, 선정의뢰정보 중 신장,체중,BMI 조회
	@Override
	public Map<String, Object> getSvcJoinMngtDtlsBasic(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rsMap = svcJoinMngtDAO.getSvcJoinMngtDtlsBasic(param);
		return rsMap;
	}

	//서비스참여관리 상세화면 조회 위험요인 조회
	@Override
	public List<Map<String, Object>> getSvcJoinMngtDtlsRisk(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getSvcJoinMngtDtlsRisk(param);
	}

	//서비스참여관리 상세화면 조회 상담내역 조회
	@Override
	public List<Map<String, Object>> getSvcJoinMngtDtlsCnsl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getSvcJoinMngtDtlsCnsl(param);
	}
	
	//서비스참여관리 상세화면 조회 일자에 따른 상담내역 조회
	@Override
	public Map<String, Object> getSvcJoinMngtDtlsCnslDate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getSvcJoinMngtDtlsCnslDate(param);
	}
	
	//서비스참여관리 상세화면 조회 질환정보 조회
	@Override
	public List<Map<String, Object>> getSvcJoinMngtDtlsDiss(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getSvcJoinMngtDtlsDiss(param);
	}

	//서비스참여관리 update 저장
	@Override
	public void updateSvcJoinMngt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		svcJoinMngtDAO.updateSvcJoinMngt(param);
	}
	
	//서비스참여관리 new insert 저장
	@Override
	public void newInsertSvcJoinMngt(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		svcJoinMngtDAO.newInsertSvcJoinMngt(param);
	}
	
	//서비스참여관리 검진결과 조회 팝업
	@Override
	public List<Map<String, Object>> getSvcJoinMngtRsltPop(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getSvcJoinMngtRsltPop(param);
	}

	//서비스참여관리 신규 클릭 시 군분류 조회
	@Override
	public Map<String, Object> getSelSvcJoinMngtGunClas(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getSelSvcJoinMngtGunClas(param);
	}
	
	//서비스참여관리 군분류 이력 insert
	@Override
	public void insertHistory(Map<String, Object> param) throws Exception {
		svcJoinMngtDAO.insertHistory(param);
	}

	/*@Override
	public int checkCnslClf(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.checkCnslClf(param);
	}*/
	
	//탈락 대상자 정보 백업 목록 조회
	@Override
	public List<Map<String, Object>> droptrageterInfoExcelList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.droptrageterInfoExcelList(param);
	}
	
	//건강위험요인 변화 결과보기 조회 팝업
	@Override
	public List<Map<String, Object>> getHealthRiskReducePop(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getHealthRiskReducePop(param);
	}
	
	//건강 행태 변화 결과보기 조회 팝업
	@Override
	public List<Map<String, Object>> getHealthBehevImpPop(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcJoinMngtDAO.getHealthBehevImpPop(param);
	}
	
	@Override
	public Map<String, Object> getSvcMngtNoForUserId(Map<String, Object> param) throws Exception {
		return svcJoinMngtDAO.getSvcMngtNoForUserId(param);
	}

	@Override
	public int chkDupSvcNo(Map<String, Object> param) throws Exception {		
		return svcJoinMngtDAO.chkDupSvcNo(param);
	}
	
	//플래그 변경
	@Override
	public void updateTrgterFlag(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		svcJoinMngtDAO.updateTrgterFlag(param);
	}
}