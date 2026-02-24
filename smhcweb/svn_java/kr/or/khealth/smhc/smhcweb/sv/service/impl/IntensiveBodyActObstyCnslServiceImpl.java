package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.IntensiveBodyActObstyCnslService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :IntensiveBodyActObstyCnslServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 집중 상담업무를 관리하는 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.23		이태석			최초생성
 
 * @author gst
 * @since 2016.09.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sv.IntensiveBodyActObstyCnslService")
public class IntensiveBodyActObstyCnslServiceImpl extends EgovAbstractServiceImpl implements IntensiveBodyActObstyCnslService {
	
	@Resource(name= "web.sv.IntensiveBodyActObstyCnslDAO")
	private IntensiveBodyActObstyCnslDAO intensiveBodyActObstyCnslDAO;
	
	@Override
	public Map<String, String> getBasicUserInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getBasicUserInfo(param);
	}
	
	@Override
	public List<Map<String, Object>> getIntensiveBodyGoalMngt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getIntensiveBodyGoalMngt(param);
	}	
	
	@Override
	public Map<String, String> getCnslHistory(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslHistory(param);
	}
	
	@Override
	public List<Map<String, Object>> getCnslDe(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslDe(param);
	}
	
	@Override
	public List<Map<String, Object>> getCnslDePrdTotalInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslDePrdTotalInfo(param);
	}	
	
	@Override
	public Map<String, String> getTotEval(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getTotEval(param);
	}
	
	@Override
	public List<Map<String, Object>> getSvWeekSttus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getSvWeekSttus(param);
	}
	
	@Override
	public List<Map<String, Object>> getActRecord(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getActRecord(param);
	}
	
	@Override
	public int getTotEvalupdate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getTotEvalupdate(param);
	}
	
	@Override
	public int updateBodyCnslSubmit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.updateBodyCnslSubmit(param);
	}
	
	@Override
	public List<Map<String, Object>> getBodyActDEList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getBodyActDEList(param);
	}
	
	@Override
	public List<Map<String, Object>> getBodyActDYList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getBodyActDYList(param);
	}	
	
	@Override
	public int getTotEvalDelete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getTotEvalDelete(param);
	}

	@Override
	public List<Map<String, Object>> getCnslTemplateNm(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslTemplateNm(param);
	}
	
	@Override
	public Map<String, Object> getCnslTemplateConts(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslTemplateConts(param);
	}
	
	@Override
	public List<Map<String, Object>> getCnslTemplateList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslTemplateList(param);
	}
	
	@Override
	public int getCnslTemplateUpdate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslTemplateUpdate(param);
	}
	
	@Override
	public int getCnslTemplateDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslTemplateDel(param);
	}

	// 2017.02.27 이태석 추가(파일첨부)
	@Override
	public List<Map<String, Object>> getCnslAttchList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getCnslAttchList(param);
	}
	
	// 2017.03.30 이태석 추가(건강정보 조회)
	@Override
	public List<Map<String, Object>> getHelthExam(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getHelthExam(param);
	}
	
	// 2017.03.31 이태석 추가(목표 걸음수 달성율 조회)
	@Override
	public Map<String, Object> getObjWalkAchvPer(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getObjWalkAchvPer(param);
	}
	
	// 2017.04.03 이태석 추가(주 평균 운동시간,횟수 조회)
	@Override
	public Map<String, Object> getWeekExcsAvgTmCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getWeekExcsAvgTmCnt(param);
	}
	
	// 2017.04.12 이태석 추가(심박수 시간대별 조회)
	@Override
	public List<Map<String, Object>> getDayHeartRateDtaList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getDayHeartRateDtaList(param);
	}	
	
	// 2017.05. 18 (심박수 구간별 조회)
	@Override
	public List<Map<String, Object>> getDayHeartRateSecList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getDayHeartRateSecList(param);
	}
	
	// 2017.05. 18 (심박수 구간별 조회)
	@Override
	public List<Map<String, Object>> getDayHeartRateSecList2(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getDayHeartRateSecList2(param);
	}
	
	@Override
	public List<Map<String, Object>> getActRecordChart(Map<String, Object> param) throws Exception {
		return intensiveBodyActObstyCnslDAO.getActRecordChart(param);
	}
	
	@Override
	public Map<String, Object> getObjHrSucRate(Map<String, Object> param) throws Exception {
		return intensiveBodyActObstyCnslDAO.getObjHrSucRate(param);
	}

	// 2017.06.26 추가(공통 동영상 목록 조회)
	@Override
	public List<Map<String, Object>> getBodyActVdTemplateList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return intensiveBodyActObstyCnslDAO.getBodyActVdTemplateList(param);
	}

	@Override
	public int updateBodyActAllSubmit(Map<String, Object> param) throws Exception {
		return intensiveBodyActObstyCnslDAO.updateBodyActAllSubmit(param);
	}
	
	@Override
	public List<Map<String, Object>> bodyCompInfoList(Map<String, Object> param) throws Exception{
		return intensiveBodyActObstyCnslDAO.bodyCompInfoList(param);
	}
}
