package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.tg.service.ServiceObjMngtService;

@Service("web.tg.ServiceObjMngtService")
public class ServiceObjMngtServiceImpl implements ServiceObjMngtService{
	
	@Resource(name="web.tg.ServiceObjMngtServiceDAO")
	private ServiceObjMngtServiceDAO serviceObjMngtServiceDAO;

	@Override
	public Map<String, String> getUserInfo(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getUserInfo(param);
	}

	@Override
	public Map<String, String> getCnslInfo(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getCnslInfo(param);
	}

	@Override
	public List<Map<String, String>> getActiceCnt(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getActiceCnt(param);
	}

	@Override
	public List<Map<String, String>> getControlCnt(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getControlCnt(param);
	}

	@Override
	public List<Map<String, String>> getActiveControl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serviceObjMngtServiceDAO.getActiveControl(param);
	}

	@Override
	public Map<String, String> changeNeedam(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.changeNeedam(param);
	}

	@Override
	public Map<String, String> changeObjNeedam(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.changeObjNeedam(param);
	}

	@Override
	public List<Map<String, String>> getObjEatNeed(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getObjEatNeed(param);
	}

	@Override
	public List<Map<String, String>> getRecommendEatCnt(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getRecommendEatCnt(param);
	}

	@Override
	public List<Map<String, String>> getDangerFactor(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getDangerFactor(param);
	}

	@Override
	public void updateCnslInfo(Map<String, Object> param) throws Exception {
		serviceObjMngtServiceDAO.updateCnslInfo(param);
	}

	@Override
	public void updateCnslNurtInfo(Map<String, Object> param) throws Exception {
		serviceObjMngtServiceDAO.updateCnslNurtInfo(param);
	}

	@Override
	public int getCnslSnSeq(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getCnslSnSeq(param);
	}

	@Override
	public void insertNewCnslInfo(Map<String, Object> param) throws Exception {
		serviceObjMngtServiceDAO.insertNewCnslInfo(param);
	}

	@Override
	public void insertNewCnslNurtInfo(Map<String, Object> param) throws Exception {
		serviceObjMngtServiceDAO.insertNewCnslNurtInfo(param);
	}

	@Override
	public List<Map<String, String>> getDateList(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getDateList(param);
	}

	@Override
	public Map<String, String> getMyWeek(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getMyWeek(param);
	}
	
	public Map<String, String> getRecomCRFPer(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getRecomCRFPer(param);
	}	
	
	@Override
	public Map<String, String> checkingIntegration(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.checkingIntegration(param);
	}

	@Override
	public void success_pgmt(Map<String, Object> param) throws Exception {
		serviceObjMngtServiceDAO.success_pgmt(param);
	}

	@Override
	public List<Map<String, String>> getSerivceObjMngtList(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getSerivceObjMngtList(param);
	}

	@Override
	public Map<String, String> getCountServiceObjMngt(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getCountServiceObjMngt(param);
	}

	@Override
	public List<Map<String, String>> selectPractMissionSch(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.selectPractMissionSch(param);
	}

	@Override
	public List<Map<String, String>> selectPractMissionSchChronic(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.selectPractMissionSchChronic(param);
	}

	@Override
	public int insertCreatePractMissionSch(Map<String, Object> param)throws Exception {
		return serviceObjMngtServiceDAO.insertCreatePractMissionSch(param);
		
	}

	@Override
	public int insertCreatePractMissionSchChronic(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.insertCreatePractMissionSchChronic(param);
	}

	@Override
	public List<Map<String, String>> selectPractMission(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.selectPractMission(param);
	}

	@Override
	public List<Map<String, String>> selectPractMissionChronic(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.selectPractMissionChronic(param);
	}

	@Override
	public int updatePractMissionSch(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.updatePractMissionSch(param);
	}

	@Override
	public int updatePractMissionSchChronic(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.updatePractMissionSchChronic(param);
	}

	@Override
	public Map<String, Object> getSvcSchCreateYn(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getSvcSchCreateYn(param);
	}
	
	@Override
	public Map<String, Object> getCnslCompleteSvcBgnAppontYn(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getCnslCompleteSvcBgnAppontYn(param);
	}

	@Override
	public List<Map<String, Object>> getSelfMngtSurveyList(Map<String, Object> param) throws Exception {
		return serviceObjMngtServiceDAO.getSelfMngtSurveyList(param);
	}

	@Override
	public String getCnslSn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serviceObjMngtServiceDAO.getCnslSn(param);
	}	
}
