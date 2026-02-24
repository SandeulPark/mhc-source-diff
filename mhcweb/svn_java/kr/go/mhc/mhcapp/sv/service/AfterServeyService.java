package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface AfterServeyService {

	public  Map<String,Object> insertafterServeyAnswr(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectAfterServeyCodeList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectAfterServeyList(Map<String, Object> param) throws Exception;
	
	public Integer updateAfterServeyAnswr(Map<String, Object> param) throws Exception;

}
