package kr.go.mhc.mhcweb.st.service;

import java.util.List;
import java.util.Map;



public interface StatisticsExcelService {

	public List<Map<String,String>> statisticsList(Map<String,Object> param) throws Exception;
	
	public List<Map<String,String>> statisticsStndList(Map<String,Object> param) throws Exception;
	
	public List<Map<String,String>> statisticsInfo(Map<String,Object> param) throws Exception;
	
	public List<Map<String,String>> statisticsTableColInfo(Map<String,Object> param) throws Exception;
	
	public String CALL_PRC_TM_STATS_ALL_INS(Map<String, Object> param) throws Exception;
}
