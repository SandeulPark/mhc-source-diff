package kr.go.mhc.mhcapp.ms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("ms.measrMastrDAO")
public class MeasrMastrDAO extends DMultiEgovAbstractMapper{
	
	
	public Map<String,Object> getMeasrSeq() throws Exception {

		return selectOne("mhc.app.ms.measrmas.selectMeasrSeq");  
	}
	
	public int insertMeasrMas(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertMeasrMas", param);			  
	}
	
	public int updateMeasrMas(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.updateMeasrMas", param);			  
	}
	
	public int deleteMeasrMas(Map<String, Object> param) throws Exception {
		
		return delete("mhc.app.ms.measrmas.deleteMeasrMas", param);			  
	}
	
	public int insertBloodPress(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertBloodPress", param);			  
	}
	
	public int updateBloodPress(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.updateBloodPress", param);			  
	}
	
	public int deleteBloodPress(Map<String, Object> param) throws Exception {
		
		return delete("mhc.app.ms.measrmas.deleteBloodPress", param);			  
	}
	
	public int insertBloodSugar(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertBloodSugar", param);			  
	}	
	
	public int updateBloodSugar(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.updateBloodSugar", param);			  
	}
	
	public int updateBloodSugarMealClf(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.updateBloodSugarMealClf", param);			  
	}
	
	public int deleteBloodSugar(Map<String, Object> param) throws Exception {
		
		return delete("mhc.app.ms.measrmas.deleteBloodSugar", param);			  
	}
	
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception {

		return selectOne("mhc.app.ms.measrmas.selectMeasrJudge", param);  
	}
	
	public int insertActDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertActDta", param);			  
	}
	
	public int insertHRArrDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertHRArrDta", param);			  
	}
	
	public int insertHRDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertHRDta", param);			  
	}
	
	//boram s
	
	public int insertHeartRateArr(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertHeartRateArr", param);			  
	}
	
	public int insertRunningHrArr(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertRunningHrArr", param);			  
	}
	
	public int insertRunningStatusDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertRunningStatusDta", param);			  
	}
	
	public int insertRunningHRArrDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertRunningHRArrDta", param);			  
	}
	
	public int insertRunningHRDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertRunningHRDta", param);			  
	}
	
	public int insertRunningCalorieArrDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertRunningCalorieArrDta", param);			  
	}
	
	public int insertRunningCalorieDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertRunningCalorieDta", param);			  
	}
	//boram e
	
	public int insertBodyCompDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertBodyCompDta", param);			  
	}
	
	public int insertBodyComp(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertBodyComp", param);			  
	}
	
	public int updatePairDeviceInfo(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.updatePairDeviceInfo", param);	  
	}
	
	public String callProcActIns(Map<String, Object> param) throws Exception {
		
		return selectOne("mhc.app.ms.measrmas.callProcActIns", param);			  
	}
	
	public int mergeEquipInfo(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.mergeEquipInfo", param);			  
	}
	
	public int duplChkBloodSugar(Map<String, Object> param) throws Exception {

		return selectOne("mhc.app.ms.measrmas.duplChkBloodSugar", param);  
	}
	
	public int duplChkBloodPress(Map<String, Object> param) throws Exception {

		return selectOne("mhc.app.ms.measrmas.duplChkBloodPress", param);  
	}
	
	public Map<String,Object> searchSerialNo(Map<String, Object> param) throws Exception {

		return selectOne("mhc.app.ms.measrmas.searchSerialNo", param);  
	}
	
	public String userTypeCheck(Map<String, Object> param) throws Exception {
		
		return selectOne("mhc.app.ms.measrmas.userTypeCheck", param);
	}
	
	public Map<String,Object> deviceUserInfo(Map<String, Object> param) throws Exception {
		
		return selectOne("mhc.app.ms.measrmas.deviceUserInfo", param);
	}
	
	public List<Map<String,Object>> searchSerialNoList(Map<String, Object> param) throws Exception {

		return selectList("mhc.app.ms.measrmas.searchSerialNoList", param);  
	}
	
	public Map<String,Object> selectHeightInfo(Map<String,Object> param) throws Exception{
		return selectOne("mhc.app.ms.measrmas.selectHeightInfo", param);
	}
	
	public int insertBodyCompManu(Map<String, Object> param) throws Exception {
		return insert("mhc.app.ms.measrmas.insertBodyCompManu", param);			  
	}
	
	public int deleteBodycomp(Map<String,Object> param) throws Exception{
		return delete("mhc.app.ms.measrmas.deleteBodycomp", param);
	}
	


	/********************************** OpenApi 적용 관련 START ***************************************/
	public Map<String,Object> checkLastData(Map<String, Object> param) throws Exception {
		
		return selectOne("mhc.app.ms.measrmas.checkLastData", param);  
	}
	
	public Map<String,Object> checkActCnt(Map<String, Object> param) throws Exception {
		
		return selectOne("mhc.app.ms.measrmas.checkActCnt", param);
	}
	
	public int insertAct(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.insertAct", param);			  
	}
	
	public int deleteAct(Map<String, Object> param) throws Exception {
		
		return delete("mhc.app.ms.measrmas.deleteAct", param);			  
	}
	
	public int insertHeartRate(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.insertHeartRate", param);			  
	}
	
	public int insertRunningStatus(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.insertRunningStatus", param);			  
	}
	
	public int insertRunningStatusDisOrd(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.insertRunningStatusDisOrd", param);			  
	}
	
	public int insertRunningCalorie(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.insertRunningCalorie", param);			  
	}
	
	public int insertRunningHR(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.insertRunningHR", param);			  
	}
	/********************************** OpenApi 적용 관련 END ***************************************/
	
	public List<Map<String,Object>> selectDeviceSerial(Map<String, Object> param) throws Exception {

		return selectList("mhc.app.ms.measrmas.selectDeviceSerial", param);  
	}
	
	public List<Map<String,Object>> selectEquipItem(Map<String, Object> param) throws Exception {

		return selectList("mhc.app.ms.measrmas.selectEquipItem", param);  
	}
	
	public List<Map<String,Object>> selectUserOta(Map<String, Object> param) throws Exception {

		return selectList("mhc.app.ms.measrmas.selectUserOta", param);  
	}
	
	public int insertUserOta(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.insertUserOta", param);			  
	}
	
	public String callProcInbodyActData(Map<String, Object> param) throws Exception {
		
		return selectOne("mhc.app.ms.measrmas.callProcInbodyActData", param);			  
	}
	
	public Map<String,Object> selectActMaxSn(Map<String, Object> param) throws Exception {
		
		return selectOne("mhc.app.ms.measrmas.selectActMaxSn", param);
	}
	
	public int updateInbodyTotalCnt(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.updateInbodyTotalCnt", param);			  
	}
	
	public int updateMeasrTotalCnt(Map<String, Object> param) throws Exception {
		
		return update("mhc.app.ms.measrmas.updateMeasrTotalCnt", param);			  
	}
	
	public int insertActNew(Map<String, Object> param) throws Exception {
		return insert("mhc.app.ms.measrmas.insertActNew", param);
	}
	
	public String callProcActDataNew(Map<String, Object> param) throws Exception {		
		return selectOne("mhc.app.ms.measrmas.callProcActDataNew", param);
	}
		
	
}
