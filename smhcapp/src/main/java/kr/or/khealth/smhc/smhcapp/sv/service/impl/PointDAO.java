package kr.or.khealth.smhc.smhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("smhcapp.sv.PointDAO")
public class PointDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> selectMissionPoint(Map<String, Object> param)
			throws Exception {
		
		List<Map<String, String>> rsList = selectList("smhcapp.sv.point.selectMissionPoint", param);
		
		return rsList;  
	}
	
	public List<Map<String, String>> selectPointList(Map<String, Object> param)
			throws Exception {
		
		List<Map<String, String>> rsList = selectList("smhcapp.sv.point.selectPointList", param);
		
		return rsList;  
	}

}
