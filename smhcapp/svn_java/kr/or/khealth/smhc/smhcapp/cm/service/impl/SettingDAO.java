package kr.or.khealth.smhc.smhcapp.cm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("smhcapp.cm.SettingDAO")
public class SettingDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> selectUserDrugInfo(Map<String, Object> param)
			throws Exception {
		
		List<Map<String, String>> rsList = selectList("cm.setting.selectUserDrugInfo", param);
		
		return rsList;  
	}
	
	public Map<String, String> selectUserObj(Map<String, Object> param)
			throws Exception {
		
		Map<String, String> rsMap = selectOne("cm.setting.selectUserObj", param);
		
		return rsMap;  
	}
	
	public void insertUserObj(Map<String, Object> param)
			throws Exception {
		
		insert("cm.setting.insertUserObj", param);
		
	}
	
	public void userImgUpload(Map<String, Object> param) 
			throws Exception{
		update("cm.setting.userImgUpload", param);
	}
	
	public Map<String, Object> getUserImg(Map<String, Object> param) 
			throws Exception{
		
		Map<String, Object> rsMap = selectOne("cm.setting.getUserImg", param);
		
		return rsMap;  
	}
	
	public Map<String, Object> getLastConnectDt(Map<String, Object> param) {
		return selectOne("cm.setting.getLastConnectDt",param);
	}

	public Map<String, Object> selectWriteMeasrUseYn(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("cm.setting.selectWriteMeasrUseYn", param);
		return rsMap;
	}

	public void updateWriteMeasrUseYn(Map<String, Object> param) {
		update("cm.setting.updateWriteMeasrUseYn", param);		
	}
	
	public void updateWriteBloodSugarUseYn(Map<String, Object> param) {
		update("cm.setting.updateWriteBloodSugarUseYn", param);		
	}
	
	public void updateWriteBloodPressUseYn(Map<String, Object> param) {
		update("cm.setting.updateWriteBloodPressUseYn", param);		
	}
	
	public void updateWriteBodycompUseYn(Map<String, Object> param) {
		update("cm.setting.updateWriteBodycompUseYn", param);		
	}

	public Map<String, Object> selectPushUseYn(Map<String, Object> param) {		
		Map<String, Object> rsMap = selectOne("cm.setting.selectPushUseYn", param);
		return rsMap;
	}

	public void updatePushUseYn(Map<String, Object> param) {
		update("cm.setting.updatePushUseYn", param);			
	}

	public Map<String, Object> selectVerticalModeYn(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("cm.setting.selectVerticalModeYn", param);
		return rsMap;
	}

	public void updateVerticalUseYn(Map<String, Object> param) {
		update("cm.setting.updateVerticalUseYn", param);		
	}

	public Map<String, Object> selectUsersOption(Map<String, Object> param) {
		Map<String, Object> rsMap = selectOne("cm.setting.selectUsersOption", param);
		return rsMap;
	}

	public void updateMealSeqUseYn(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("cm.setting.updateMealSeqUseYn", param);		
	}
	
	public void updateSelfMeasrUseYn(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("cm.setting.updateSelfMeasrUseYn", param);		
	}

	public List<Map<String, Object>> selectUserBloodMission(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("cm.setting.selectUserBloodMission", param);
		return rsList;
	}

	public List<Map<String, Object>> selectUserBloodPushInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("cm.setting.selectUserBloodPushInfo", param);
		return rsList;
	}

	public void delUserBloodPushInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		delete("cm.setting.delUserBloodPushInfo", param);	
	}

	public void insertUserBloodPushInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		insert("cm.setting.insertUserBloodPushInfo", param);
		
		//PRC_AUTO_PUSH_INS(15분/45분에 실행)에 걸리지 않은 시간에 등록시 수행 로직
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");		
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("YYYYMMdd");
				
		// 데이터 들어온 현재 시간 
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		String nowDay = sdf3.format(now);				
		String nowHour = sdf1.format(now);
		String nowMinute = sdf2.format(now);
		
		// 한시간 뒤
		cal.add(Calendar.HOUR, 1);
		String afterHour = sdf1.format(cal.getTime());
		
		// 푸시 시작 일자가 오늘 일자와 같을 경우 
		if(String.valueOf(param.get("PUSH_BGN_DE")).equals(nowDay)) {			
			try {				
				//푸시 설정 시간
				String pushTime = String.valueOf(param.get("PUSH_TIME"));
				String pushHour = pushTime.substring(0,2);
				String pushMinute = pushTime.substring(2,4);
				pushTime = pushHour + ":" + pushMinute;
				
				//프로시저 시간
				String procedureTime = "";
				//데이터 들어온 시간 기점으로 다음 프로시저 시간 확인
				if(Integer.parseInt(nowMinute) < 15) {
					procedureTime = nowHour + ":" + "15";
				}else if(Integer.parseInt(nowMinute) > 15 && Integer.parseInt(nowMinute) < 45) {
					procedureTime = nowHour + ":" + "45";			
				}else if(Integer.parseInt(nowMinute) > 45){
					procedureTime = afterHour + ":" + "15";
				}
				
				//푸시 설정 시간과 다음 프로시저 시간 비교
				Date date1 = sdf.parse(pushTime);
				Date date2 = sdf.parse(procedureTime);
				
				long timeMil1 = date1.getTime();
				long timeMil2 = date2.getTime();
				
				long diff = timeMil2 - timeMil1;
				
				//푸시 설정 시간이 다음 프로시저 시간보다 앞일 경우
				if(diff > 0) {
					String pushNm = "";
					if((String.valueOf(param.get("MISSION_CD"))).equals("M004")) {
						pushNm = "혈압 측정";
					}else {
						pushNm = "혈당 측정";
					}					
					// seq 가져오기
					String sndSn = selectOne("cm.setting.getPushSeq", param);
					param.put("PUSH_NM", pushNm);
					param.put("SND_SN", sndSn);
					insert("cm.setting.insertUserBloodPushMaster", param);
					insert("cm.setting.insertUserBloodPushHist", param);
				}
			}catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateUserBloodPushInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("cm.setting.updateUserBloodPushInfo", param);
	}
}
