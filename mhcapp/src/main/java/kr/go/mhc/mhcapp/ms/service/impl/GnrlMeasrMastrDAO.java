package kr.go.mhc.mhcapp.ms.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("ms.gnrlMeasrMastrDAO")
public class GnrlMeasrMastrDAO extends DMultiEgovAbstractMapper{
	
	public Map<String,Object> getMeasrSeq() throws Exception {
		return selectOne("mhc.app.gn.measrmas.selectMeasrSeq");  
	}
	
	public int insertMeasrMas(Map<String, Object> param) throws Exception {
		return insert("mhc.app.gn.measrmas.insertMeasrMas", param);			  
	}
	
	public int updateMeasrMas(Map<String, Object> param) throws Exception {		
		return update("mhc.app.gn.measrmas.updateMeasrMas", param);			  
	}
	
	public int deleteMeasrMas(Map<String, Object> param) throws Exception {		
		return delete("mhc.app.gn.measrmas.deleteMeasrMas", param);			  
	}
	
	public int duplChkBloodPress(Map<String, Object> param) throws Exception {

		return selectOne("mhc.app.gn.measrmas.duplChkBloodPress", param);  
	}
	
	public int insertBloodPress(Map<String, Object> param) throws Exception {		
		return insert("mhc.app.gn.measrmas.insertBloodPress", param);			  
	}
	
	public int updateBloodPress(Map<String, Object> param) throws Exception {		
		return update("mhc.app.gn.measrmas.updateBloodPress", param);			  
	}
	
	public int deleteBloodPress(Map<String, Object> param) throws Exception {		
		return delete("mhc.app.gn.measrmas.deleteBloodPress", param);			  
	}
	
	public int duplChkBloodSugar(Map<String, Object> param) throws Exception {

		return selectOne("mhc.app.gn.measrmas.duplChkBloodSugar", param);  
	}
	
	public int insertBloodSugar(Map<String, Object> param) throws Exception {		
		return insert("mhc.app.gn.measrmas.insertBloodSugar", param);			  
	}	
	
	public int updateBloodSugar(Map<String, Object> param) throws Exception {		
		return update("mhc.app.gn.measrmas.updateBloodSugar", param);			  
	}
	
	public int deleteBloodSugar(Map<String, Object> param) throws Exception {
		return delete("mhc.app.gn.measrmas.deleteBloodSugar", param);			  
	}
	
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception {
		return selectOne("mhc.app.gn.measrmas.selectMeasrJudge", param);  
	}

	public int insertBodyComp(Map<String, Object> param) throws Exception {
		return insert("mhc.app.gn.measrmas.insertBodyComp", param);
	}

	public int insertBodyCompManu(Map<String, Object> param) throws Exception {
		return insert("mhc.app.gn.measrmas.insertBodyCompManu", param);
	}
	
	public int deleteBodycomp(Map<String,Object> param) throws Exception{
		return delete("mhc.app.gn.measrmas.deleteBodycomp", param);
	}
	
	/********************************** OpenApi 적용 관련 START ***************************************/
	public Map<String,Object> checkLastData(Map<String, Object> param) throws Exception {		
		return selectOne("mhc.app.gn.measrmas.checkLastData", param);  
	}
	
	public int insertAct(Map<String, Object> param) throws Exception {		
		return update("mhc.app.gn.measrmas.insertAct", param);			  
	}
	
	public Map<String,Object> selectActMaxSn(Map<String, Object> param) throws Exception {		
		return selectOne("mhc.app.gn.measrmas.selectActMaxSn", param);
	}
	
	public String callProcActData(Map<String, Object> param) throws Exception {		
		return selectOne("mhc.app.gn.measrmas.callProcActData", param);
	}
	
	public Map<String,Object> deviceUserInfo(Map<String, Object> param) throws Exception {
		return selectOne("mhc.app.gn.measrmas.deviceUserInfo", param);  
	}
	
	public int updatePairDeviceInfo(Map<String, Object> param) throws Exception {
		return update("mhc.app.gn.measrmas.updatePairDeviceInfo", param);	  
	}
}
