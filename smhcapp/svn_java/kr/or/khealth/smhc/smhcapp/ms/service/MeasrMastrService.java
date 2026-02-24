package kr.or.khealth.smhc.smhcapp.ms.service;

import java.util.List;
import java.util.Map;

public interface MeasrMastrService {
	
	public int insertActivityLink(List<Map<String, Object>> datas) throws Exception;
	
	public int insertBloodPressLink(List<Map<String, Object>> datas) throws Exception;
	
	public int insertBloodSugarLink(List<Map<String, Object>> datas) throws Exception;

	public Map<String,Object> insertBloodSugarManu(Map<String, Object> param) throws Exception;	
	
	public int insertBodyCompLink(List<Map<String, Object>> datas) throws Exception;

	public Map<String,Object> getMeasrSeq() throws Exception;
	
	public Map<String,Object> insertBloodPress(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> updateBloodPress(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> deleteBloodPress(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> insertBloodSugar(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> updateBloodSugar(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> deleteBloodSugar(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception; 
	
	public int insertActDta(Map<String, Object> param) throws Exception;
	
	public int insertHRArrDta(Map<String, Object> param) throws Exception;
	
	public int insertHRDta(Map<String, Object> param) throws Exception;
	
	/********************************** Mambo2 운동관련 START ***************************************/
	
	public int insertRunningStatusDta(Map<String, Object> param) throws Exception ;			  

	
	public int insertRunningHRArrDta(Map<String, Object> param) throws Exception;			  

	
	public int insertRunningHRDta(Map<String, Object> param) throws Exception;			  

	
	public int insertRunningCalorieArrDta(Map<String, Object> param) throws Exception;			  

	
	public int insertRunningCalorieDta(Map<String, Object> param) throws Exception;			  

	/********************************** Mambo2 운동관련 START ***************************************/
	
	public int insertBodyCompDta(Map<String, Object> param) throws Exception;
	
	public int insertBodyComp(Map<String, Object> param) throws Exception;
	
	public int updatePairDeviceInfo(Map<String, Object> param) throws Exception;
	
	public String callProcActIns(Map<String, Object> param) throws Exception;
	
	public int mergeEquipInfo(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> searchSerialNo(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> deviceUserInfo(Map<String, Object> param) throws Exception;
	
	public List<Map<String,Object>> searchSerialNoList(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> insertBodyCompManu_old(Map<String,Object> param) throws Exception;
	
	public Map<String,Object> deleteBodyCompManu(Map<String,Object> param) throws Exception;


	/********************************** OpenApi 적용 관련 START ***************************************/
	public Map<String,Object> checkLastData(Map<String, Object> param) throws Exception;
	
	public int insertAct(Map<String, Object> param) throws Exception;
	
	public int insertHeartRate(Map<String, Object> param) throws Exception;
	
	public int insertRunningStatus(Map<String, Object> param) throws Exception;
	
	public int insertRunningCalorie(Map<String, Object> param) throws Exception;
	
	public int insertRunningHR(Map<String, Object> param) throws Exception;

	public int insertHeartRateArr(Map<String, Object> param) throws Exception;
	
	public int insertRunningHrArr(Map<String, Object> param) throws Exception;
	
	
	/********************************** OpenApi 적용 관련 END ***************************************/
	
	
	public List<Map<String,Object>> selectDeviceSerial(Map<String, Object> param) throws Exception;
	
	public List<Map<String,Object>> selectEquipItem(Map<String, Object> param) throws Exception;
	
	public List<Map<String,Object>> selectUserOta(Map<String, Object> param) throws Exception;
	
	public int insertUserOta(Map<String, Object> param) throws Exception;
	
	
	/********************************** 혈당계 CLF값 보정 함수 ***************************************/
	public int updateLstBloodSugar(Map<String, Object> param) throws Exception;
	/********************************** 혈당계 CLF값 보정 함수 ***************************************/
	/*********************** 연동 결과값 산출 (M004,M005,W001 각 혈압, 혈당, 체중) ***********************/
	public Map<String, Object> getMeasureResultInf(Map<String, Object> param) throws Exception;
	/*********************** 연동 결과값 산출 (M004,M005,W001 각 혈압, 혈당, 체중) ***********************/

	public Map<String, Object> insertBodyCompManu(Map<String, Object> param) throws Exception;

	public Map<String, Object> insertBloodPressManu(Map<String, Object> param) throws Exception;

	public Map<String, Object> lstBloodSugarSaveChk(Map<String, Object> param) throws Exception;

	public int updateLstBloodSugarClf(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectLastData(Map<String, Object> param) throws Exception;

	public int insertSleepLink(List<Map<String, Object>> datas) throws Exception;

}
