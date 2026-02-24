package kr.go.mhc.mhcweb.ms.service.impl;

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
	
	public int insertBloodPress(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertBloodPress", param);			  
	}
	
	public int insertBloodSugar(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertBloodSugar", param);			  
	}	
	
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception {

		return selectOne("mhc.app.ms.measrmas.selectMeasrJudge", param);  
	}
	
	public int insertActDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertActDta", param);			  
	}
	
	public int insertHRDta(Map<String, Object> param) throws Exception {
		
		return insert("mhc.app.ms.measrmas.insertHRDta", param);			  
	}
	
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
	
	
}
