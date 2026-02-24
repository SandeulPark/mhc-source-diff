package kr.or.khealth.smhc.smhcapp.cm.service;

import java.util.List;
import java.util.Map;

public interface SettingService {
	
	public List<Map<String, String>> selectUserDrugInfo(Map<String, Object> param) throws Exception;
	
	public Map<String, String> selectUserObj(Map<String, Object> param) throws Exception;
	
	public void insertUserObj(Map<String, Object> param) throws Exception;
	
	public void userImgUpload(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getUserImg(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getLastConnectDt(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectWriteMeasrUseYn(Map<String, Object> param) throws Exception;

	public void updateWriteMeasrUseYn(Map<String, Object> param) throws Exception;
	
	public void updateWriteBloodSugarUseYn(Map<String, Object> param) throws Exception;
	
	public void updateWriteBloodPressUseYn(Map<String, Object> param) throws Exception;
	
	public void updateWriteBodycompUseYn(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectPushUseYn(Map<String, Object> param) throws Exception;

	public void updatePushUseYn(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectVerticalModeYn(Map<String, Object> param)  throws Exception;

	public void updateVerticalUseYn(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectUsersOption(Map<String, Object> param) throws Exception;

	public void updateMealSeqUseYn(Map<String, Object> param) throws Exception;
	
	public void updateSelfMeasrUseYn(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> selectUserBloodMission(Map<String, Object> param)  throws Exception;

	public List<Map<String, Object>> selectUserBloodPushInfo(Map<String, Object> param) throws Exception;

	public void delUserBloodPushInfo(Map<String, Object> param) throws Exception;

	public void insertUserBloodPushInfo(Map<String, Object> param) throws Exception;

	public void updateUserBloodPushInfo(Map<String, Object> param) throws Exception;
}
