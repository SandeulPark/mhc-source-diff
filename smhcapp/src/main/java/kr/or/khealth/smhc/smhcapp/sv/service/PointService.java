package kr.or.khealth.smhc.smhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface PointService {
	
	public List<Map<String, String>> selectMissionPoint(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectPointList(Map<String, Object> param) throws Exception;

}
