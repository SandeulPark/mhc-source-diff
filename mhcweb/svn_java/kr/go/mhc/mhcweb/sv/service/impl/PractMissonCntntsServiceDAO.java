package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("web.sv.PractMissonCntntsServiceDAO")
public class PractMissonCntntsServiceDAO extends EgovAbstractMapper{

	public List<Map<String, Object>> getPractMissonCntntsList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissoncntnts.selectPractMissonCntnts", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getPractMissonCont(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissoncntnts.selectPractMissonCont", param);
		return rsList;
	}
	
	public int updatePractMissonCont(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.sv.practmissoncntnts.updatePractMissonCont", param);
		return rsInt;
	}
	
}
