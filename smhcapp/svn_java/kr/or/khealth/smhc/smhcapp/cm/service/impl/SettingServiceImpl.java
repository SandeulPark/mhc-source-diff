package kr.or.khealth.smhc.smhcapp.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcapp.cm.service.SettingService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("smhcapp.cm.SettingService")
public class SettingServiceImpl extends EgovAbstractServiceImpl implements SettingService{

	@Resource(name="smhcapp.cm.SettingDAO")
    private SettingDAO settingDAO;
	
	@Override
	public List<Map<String, String>> selectUserDrugInfo(Map<String, Object> param)
			throws Exception {
		
		return settingDAO.selectUserDrugInfo(param);
	}
	
	@Override
	public Map<String, String> selectUserObj(Map<String, Object> param)
			throws Exception {
		
		return settingDAO.selectUserObj(param);
	}
	
	@Override
	public void insertUserObj(Map<String, Object> param)
			throws Exception {
		
		settingDAO.insertUserObj(param);
	}

	@Override
	public void userImgUpload(Map<String, Object> param) 
			throws Exception {
		
		settingDAO.userImgUpload(param);
	}

	@Override
	public Map<String, Object> getUserImg(Map<String, Object> param) 
			throws Exception {
		// TODO Auto-generated method stub
		return settingDAO.getUserImg(param);
	}
	
	@Override
	public Map<String, Object> getLastConnectDt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return settingDAO.getLastConnectDt(param);
	}

	@Override
	public Map<String, Object> selectWriteMeasrUseYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return settingDAO.selectWriteMeasrUseYn(param);
	}

	@Override
	public void updateWriteMeasrUseYn(Map<String, Object> param) throws Exception {
		settingDAO.updateWriteMeasrUseYn(param);
	}
	
	@Override
	public void updateWriteBloodSugarUseYn(Map<String, Object> param) throws Exception {
		settingDAO.updateWriteBloodSugarUseYn(param);
	}
	
	@Override
	public void updateWriteBloodPressUseYn(Map<String, Object> param) throws Exception {
		settingDAO.updateWriteBloodPressUseYn(param);
	}
	
	@Override
	public void updateWriteBodycompUseYn(Map<String, Object> param) throws Exception {
		settingDAO.updateWriteBodycompUseYn(param);
	}

	@Override
	public Map<String, Object> selectPushUseYn(Map<String, Object> param) throws Exception {		
		return settingDAO.selectPushUseYn(param);
	}

	@Override
	public void updatePushUseYn(Map<String, Object> param) throws Exception {
		settingDAO.updatePushUseYn(param);		
	}

	@Override
	public Map<String, Object> selectVerticalModeYn(Map<String, Object> param) throws Exception {
		return settingDAO.selectVerticalModeYn(param);
	}

	@Override
	public void updateVerticalUseYn(Map<String, Object> param) throws Exception {
		settingDAO.updateVerticalUseYn(param);	
	}

	@Override
	public Map<String, Object> selectUsersOption(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return settingDAO.selectUsersOption(param);
	}

	@Override
	public void updateMealSeqUseYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		settingDAO.updateMealSeqUseYn(param);	
	}
	
	@Override
	public void updateSelfMeasrUseYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		settingDAO.updateSelfMeasrUseYn(param);	
	}

	@Override
	public List<Map<String, Object>> selectUserBloodMission(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return settingDAO.selectUserBloodMission(param);	
	}

	@Override
	public List<Map<String, Object>> selectUserBloodPushInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return settingDAO.selectUserBloodPushInfo(param);
	}

	@Override
	public void delUserBloodPushInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		settingDAO.delUserBloodPushInfo(param);
	}

	@Override
	public void insertUserBloodPushInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		settingDAO.insertUserBloodPushInfo(param);;
	}
	@Override
	public void updateUserBloodPushInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		settingDAO.updateUserBloodPushInfo(param);	
	}
	
	
}
