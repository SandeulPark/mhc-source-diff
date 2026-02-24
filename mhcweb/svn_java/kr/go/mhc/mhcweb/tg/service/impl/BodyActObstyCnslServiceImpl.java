package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.tg.service.BodyActObstyCnslService;

/**
 * @Class Name :BodyActObstyCnslServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 신체활동비만 상담 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.23		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.tg.BodyActObstyCnslService")
public class BodyActObstyCnslServiceImpl extends EgovAbstractServiceImpl implements BodyActObstyCnslService{

	@Resource(name="web.tg.BodyActObstyCnslDAO")
	private BodyActObstyCnslDAO bodyActObstyCnslDAO;

	@Override
	public List<Map<String, String>> getBodyActObstyCnslList( Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getBodyActObstyCnslList(param);
	}

	@Override
	public int getBodyActObstyCnslCount() throws Exception {
		return bodyActObstyCnslDAO.getBodyActObstyCnslCount();
	}

	@Override
	public Map<String, String> getBodyActObstyCnslDtls(Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getBodyActObstyCnslDtls(param);
	}
	
	@Override
	public Map<String,String> getActEquipTestYn( Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getActEquipTestYn(param);
	}
	
	@Override
	public void getActEquipPymntY( Map<String, Object> param) throws Exception {
		bodyActObstyCnslDAO.getActEquipPymntY(param);
	}
	
	@Override
	public List<Map<String, String>> getCnslActRegDeList( Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getCnslActRegDeList(param);
	}
	
	@Override
	public Map<String, String> getCnslAct(Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getCnslAct(param);
	}
	
	@Override
	public List<Map<String, String>> getRiskFactorList( Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getRiskFactorList(param);
	}
	
	@Override
	public Map<String, String> getActCnslSn(Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getActCnslSn(param);
	}

	@Override
	public void getSaveActCnsl(Map<String, Object> param) throws Exception {
		bodyActObstyCnslDAO.getSaveActCnsl(param);		
	}
	
	/* 2017.04.12 이태석 추가 (운동 정보 팝업) */
	@Override
	public List<Map<String, String>> getBodyPartExcsList( Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getBodyPartExcsList(param);
	}
	
	@Override
	public Map<String, String> getMaxOxyIntakeAmJudge(Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getMaxOxyIntakeAmJudge(param);
	}
	
	@Override
	public void updateBodyObstyCnsl(Map<String, Object> param) throws Exception{
		bodyActObstyCnslDAO.updateBodyObstyCnsl(param);
	}
	
	@Override
	public Map<String,String> getCnslHistCnt(Map<String,Object> param) throws Exception{
		return bodyActObstyCnslDAO.getCnslHistCnt(param);
	}

	@Override
	public void addRecomExcsInfo(Map<String, Object> param) throws Exception {
		bodyActObstyCnslDAO.addRecomExcsInfo(param);
		
	}

	@Override
	public void deleteRecomExcsInfo(Map<String, Object> param) throws Exception {
		bodyActObstyCnslDAO.deleteRecomExcsInfo(param);
		
	}
	
	@Override
	public List<Map<String, String>> getRecomExcsSetList( Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getRecomExcsSetList(param);
	}	
	
	@Override
	public Map<String,Object> getHRCalAge(Map<String,Object> param) throws Exception{
		return bodyActObstyCnslDAO.getHRCalAge(param);
	}
	
	@Override
	public Map<String, Object> selectBodyActCnslContInfo(Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.selectBodyActCnslContInfo(param);
	}
	
	@Override
	public List<Map<String, Object>> getRecomExcsTemplateList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return bodyActObstyCnslDAO.getRecomExcsTemplateList(param);
	}
	
	@Override
	public int getRecomExcsTemplateUpdate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return bodyActObstyCnslDAO.getRecomExcsTemplateUpdate(param);
	}
	
	@Override
	public int getRecomExcsTemplateDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return bodyActObstyCnslDAO.getRecomExcsTemplateDel(param);
	}
	
	@Override
	public int getRecomExcsTemplateCnslUpdate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return bodyActObstyCnslDAO.getRecomExcsTemplateCnslUpdate(param);
	}
	
	@Override
	public List<Map<String, String>> getRecomExcsTempList( Map<String, Object> param) throws Exception {
		return bodyActObstyCnslDAO.getRecomExcsTempList(param);
	}
	
	@Override
	public int getRecomExcsPreCnsllUpdate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return bodyActObstyCnslDAO.getRecomExcsPreCnsllUpdate(param);
	}

	@Override
	public int getRecomExcsTempDelOne(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return bodyActObstyCnslDAO.getRecomExcsTempDelOne(param);
	}
	
}
