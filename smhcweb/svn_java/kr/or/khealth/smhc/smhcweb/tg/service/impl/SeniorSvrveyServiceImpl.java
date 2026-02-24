package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.khealth.smhc.smhcweb.sv.service.impl.OrgMngtDAO;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorSvrveyService;


/**
 * @Class Name :SeniorSvrveyServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대면정보 (면접조사지) 에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.09.28		양현우			최초생성
 *
 * @author theJoin
 * @since 2020.09.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.tg.SeniorSvrveyService")
public class SeniorSvrveyServiceImpl extends EgovAbstractServiceImpl implements SeniorSvrveyService{
	
	@Resource(name="web.tg.SeniorSvrveyDAO")
	private SeniorSvrveyDAO seniorSvrveyDAO;
	
	@Override
	public List<Map<String, Object>> selectSeniorSvrVeyList( Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.selectSeniorSvrVeyList(param);
	}

	@Override
	public int insertSeniorHealthSvrVey(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.insertSeniorHealthSvrVey(param);
	}

	@Override
	public List<Map<String, Object>> selectSvrveyMastrChk(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.selectSvrveyMastrChk(param);
	}

	@Override
	public List<Map<String, Object>> selectSvrveyAnswr(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.selectSvrveyAnswr(param);
	}
	
	@Override
	public List<Map<String, Object>> selectSvrveyMastrResult(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.selectSvrveyMastrResult(param);
	}
	
	@Override
	public int insertDeviceDistributes(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.insertDeviceDistributes(param);
	}
	
	@Override
	public int insertDeviceFormInfo(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.insertDeviceFormInfo(param);
	}	

	@Override
	public List<Map<String, Object>> selectDeviceDistributesList(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.selectDeviceDistributesList(param);
	}

	@Override
	public List<Map<String, Object>> selectMissionNmList(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.selectMissionNmList(param);
	}

	@Override
	public List<Map<String, Object>> selectMissionGrpNmList(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.selectMissionGrpNmList(param);
	}

	@Override
	public int updateGetDeviceInfo(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.updateGetDeviceInfo(param);
	}

	@Override
	public int updSeniorHealthSvrVey(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.updSeniorHealthSvrVey(param);
	}

	@Override
	public int updDeviceDistributes(Map<String, Object> param) throws Exception {
		return seniorSvrveyDAO.updDeviceDistributes(param);
	}

	@Override
	public String selectSvcMngtDevice(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return seniorSvrveyDAO.selectSvcMngtDevice(param);
	}

	
}
