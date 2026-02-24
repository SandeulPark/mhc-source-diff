package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.HealthMngtCnslService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :HealthMngtCnslServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 건강관리 상담 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.08.28		이현규		최초생성
 *
 * @author gst
 * @since 2016.08.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.tg.HealthMngtCnslService")
public class HealthMngtCnslServiceImpl extends EgovAbstractServiceImpl implements HealthMngtCnslService {
	
	@Resource(name="web.tg.HealthMngtCnslDAO")
	private HealthMngtCnslDAO healthMngtCnslDAO;
	
	@Override
	public Map<String, String> selectHealthMngtCnslInfo(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectHealthMngtCnslInfo(param);
	}
	
	@Override
	public List<Map<String, String>> selectDangerFactorList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectDangerFactorList(param);
	}
	
	@Override
	public List<Map<String, String>> selectSmokServeyList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectSmokServeyList(param);
	}
	
	@Override
	public Map<String, String> selectCnslContMngt(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectCnslContMngt(param);
	}
	
	@Override
	public Map<String, String> selectMngtObj(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectMngtObj(param);
	}
	
	@Override
	public Map<String, String> selectBodyActFatMngtObj(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectBodyActFatMngtObj(param);
	}
	
	@Override
	public List<Map<String, String>> selectServiceSchedule(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectServiceSchedule(param);
	}
	
	@Override
	public Map<String, String> selectChkCreateSch (Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectChkCreateSch(param);
	}	
	
	@Override
	public List<Map<String, String>> selectDeviceDistList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectDeviceDistList(param);
	}
	
	@Override
	public void updateCnsl(Map<String, Object> param) throws Exception {
		healthMngtCnslDAO.updateCnsl(param);
	}
	
	@Override
	public void updateCnslContMngt(Map<String, Object> param) throws Exception {
		healthMngtCnslDAO.updateCnslContMngt(param);
	}
	
	@Override
	public void updateBloodPressPymnt(Map<String, Object> param) throws Exception {
		healthMngtCnslDAO.updateBloodPressPymnt(param);
	}
	
	@Override
	public void updateBloodSugarPymnt(Map<String, Object> param) throws Exception {
		healthMngtCnslDAO.updateBloodSugarPymnt(param);
	}
	
	@Override
	public void insertCreateSchedule(Map<String, Object> param) throws Exception {
		healthMngtCnslDAO.updateSvcBgnDe(param);		
		healthMngtCnslDAO.insertCreateSchedule(param);
	}
	
	@Override
	public Map<String, String> selectServeyList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectServeyList(param);
	}
	
	@Override
	public List<Map<String, String>> selectServeyResultList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectServeyResultList(param);
	}
	
	@Override
	public List<Map<String, String>> selectHealthCnslDe(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectHealthCnslDe(param);
	}
	
	@Override
	public String callProcCnslAllIns(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.callProcCnslAllIns(param);
	}

	@Override
	public int updateVisitCnslDe(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.updateVisitCnslDe(param);
	}
	
	@Override
	public List<Map<String, Object>> selectCnslRsltExamList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectCnslRsltExamList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectNurtObjList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectNurtObjList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectMissionResultList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectMissionResultList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectIntensiveNurtCnslList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectIntensiveNurtCnslList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectMealDiaryWeekCalList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectMealDiaryWeekCalList(param);
	}
	
	@Override
	public Map<String, Object> selectMealDiaryDayAvgPer(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectMealDiaryDayAvgPer(param);
	}
	
	@Override
	public Map<String, Object> selectCPFPer(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectCPFPer(param);
	}
	
	@Override
	public List<Map<String, Object>> selectBodyActObjList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectBodyActObjList(param);
	}
	
	@Override
	public Map<String, Object> selectBodyActAnalysis(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectBodyActAnalysis(param);
	}
	
	@Override
	public List<Map<String, Object>> selectBodyActWeekList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectBodyActWeekList(param);
	}
	
	@Override
	public Map<String, Object> selectNoDataCheckInfo(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectNoDataCheckInfo(param);
	}
	
	@Override
	public List<Map<String, Object>> selectWeekDayActList(Map<String, Object> param) throws Exception {
		return healthMngtCnslDAO.selectWeekDayActList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectMissionResultList2(Map<String, Object> param) throws Exception{
		return healthMngtCnslDAO.selectMissionResultList2(param);
	}
	
	@Override
	public List<Map<String, Object>> selectMealDiaryResult(Map<String, Object> param) throws Exception{
		return healthMngtCnslDAO.selectMealDiaryResult(param);
	}
	
	@Override
	public List<Map<String, Object>> selectCvdPointList(Map<String, Object> param) throws Exception{
		return healthMngtCnslDAO.selectCvdPointList(param);
	}
	
	@Override
	public Map<String, Object> selectBasicUserInfo(Map<String, Object> param) throws Exception{
		return healthMngtCnslDAO.selectBasicUserInfo(param);
	}
	
	@Override
	public Map<String, Object> selectMealtNutriRsltInfo(Map<String, Object> param) throws Exception{
		return healthMngtCnslDAO.selectMealtNutriRsltInfo(param);				
	}
	
	//금연절주콘텐츠 수신여부 조회
	@Override
	public Map<String, Object> selectCnslYnList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return healthMngtCnslDAO.selectCnslYnList(param);
	}
}


