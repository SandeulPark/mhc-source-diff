package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.HealthExamMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :HealthExamMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 건강검진 관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.20		이은주			최초생성
 
 * @author gst
 * @since 2016.08.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.tg.HealthExamMngtService")
public class HealthExamMngtServiceImpl extends EgovAbstractServiceImpl implements HealthExamMngtService {
	
	@Resource(name= "web.tg.HealthExamMngtDAO")
	private HealthExamMngtDAO healthExamMngtDAO;

	//건강검진관리 조회
	@Override
	public List<Map<String, String>> getHealthExamList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return healthExamMngtDAO.getHealthExamList(param);
	}
	
	//건강검진관리 상세 조회
	@Override
	public Map<String, Object> getHealthExamMngtDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return healthExamMngtDAO.getHealthExamMngtDtls(param);
	}

	//건강검진관리 계측정보저장
	@Override
	public void updateHealthExamBody(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		healthExamMngtDAO.updateHealthExamBody(param);
	}

	//건강검진관리 혈액검사정보저장
	@Override
	public void updateHealthExamBld(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		healthExamMngtDAO.updateHealthExamBld(param);
	}

	// 건강검진관리 만성질환정보 저장
	@Override
	public void updateHealthExamChronic(Map<String, Object> param) throws Exception {
		System.out.println("############################################impl  ");
		healthExamMngtDAO.updateHealthExamChronic(param);
	}
//건강검진관리 인바디 정보 저장 검사일자 조회
//	@Override
//	public Map<String, Object> getSelHealthExamDE(Map<String, Object> param) throws Exception {
//		// TODO Auto-generated method stub
//		return healthExamMngtDAO.getSelHealthExamDE(param);
//	}
	
	//건강검진관리 인바디 정보 저장 검사일자 리스트
	@Override
	public List<Map<String, Object>> getSelHealthExamDEList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return healthExamMngtDAO.getSelHealthExamDEList(param);
	}

	//건강검진관리 인바디 정보 체성분결과저장
	@Override
	public void updateHealthBodyComp(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		healthExamMngtDAO.updateHealthBodyComp(param);
	}

	//건강검진관리 검사완료
	@Override
	public Map<String, Object> updateHealthComplete(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		return healthExamMngtDAO.updateHealthComplete(param);
	}
	
	//건강검진관리 POCT
	@Override
	public void insertHealthExamDta(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		healthExamMngtDAO.insertHealthExamDta(param);
	}

	//건강검진관리 상세 조회
	@Override
	public Map<String, Object> selectHealthMngtDetail(Map<String, Object> param) throws Exception {
		return healthExamMngtDAO.selectHealthMngtDetail(param);
	}
	
	//건강검진관리 저장여부 조회
	@Override
	public Map<String, Object> selectSaveYnCheck(Map<String, Object> param) throws Exception {
		return healthExamMngtDAO.selectSaveYnCheck(param);
	}
	
	//건강검진관리 삭제
	@Override
	public void delHealthMngt(Map<String, Object> param) throws Exception {
		healthExamMngtDAO.delHealthMngt(param);
	}
	
	//체성분 조회
	@Override
	public Map<String, Object> getHealthBodyComp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return healthExamMngtDAO.getHealthBodyComp(param);
	}

	@Override
	public Map<String, Object> updateResNo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return healthExamMngtDAO.updateResNo(param);
	}
	
	@Override
	public void updateBirth(Map<String, Object> param) throws Exception {
		healthExamMngtDAO.updateBirth(param);
	}
	
	@Override
	public Map<String, Object> healthExamCntChk(Map<String, Object> param) throws Exception{
		return healthExamMngtDAO.healthExamCntChk(param);
	}

	@Override
	public Map<String, Object> poctSaveYnChk(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return healthExamMngtDAO.poctSaveYnChk(param);
	}
}
