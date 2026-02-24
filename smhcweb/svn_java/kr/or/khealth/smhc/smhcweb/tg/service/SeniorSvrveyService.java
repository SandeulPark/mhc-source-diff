package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface SeniorSvrveyService {

	public List<Map<String, Object>> selectSeniorSvrVeyList(Map<String, Object> param) throws Exception;	
	
	public int insertSeniorHealthSvrVey(Map<String, Object> param) throws Exception;

	public int insertDeviceFormInfo(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectSvrveyMastrChk(Map<String, Object> param) throws Exception;	
	
	public List<Map<String, Object>> selectSvrveyAnswr(Map<String, Object> param) throws Exception;	
	
	public List<Map<String, Object>> selectSvrveyMastrResult(Map<String, Object> param) throws Exception;	
	
	public int insertDeviceDistributes(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectDeviceDistributesList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectMissionNmList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectMissionGrpNmList(Map<String, Object> param) throws Exception;

	public int updateGetDeviceInfo(Map<String, Object> param) throws Exception;
	
	public int updSeniorHealthSvrVey(Map<String, Object> param) throws Exception;
	
	public int updDeviceDistributes(Map<String, Object> param) throws Exception;
	
	public String selectSvcMngtDevice(Map<String, Object> param)throws Exception;
}
