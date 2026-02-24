package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.st.StatisticsExcelServiceDAO")
public class StatisticsExcelServiceDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> statisticsList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.st.statisticsexcel.statisticsList", param);
		return rsList;
	}
	
	public List<Map<String, String>> statisticsStndList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.st.statisticsexcel.statisticsStndList", param);
		return rsList;
	}
	
	public List<Map<String, String>> statisticsInfoInit(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.st.statisticsexcel.statisticsInfoInit", param);
		return rsList;
	}
	
	public List<Map<String, String>> statisticsInfo(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.st.statisticsexcel.statisticsInfo", param);
		return rsList;
	}
	
	public List<Map<String,String>> statisticsTableColInfo(Map<String,Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.st.statisticsexcel.statisticsTableColInfo", param);
		return rsList;
	}
	
	public String CALL_PRC_TM_STATS_ALL_INS(Map<String, Object> param) throws Exception {
		
		String rsStr = "";

		selectOne("mhc.web.st.statisticsexcel.CALL_PRC_TM_STATS_ALL_INS_TMP", param);
		selectOne("mhc.web.st.statisticsexcel.CALL_PRC_TM_CNTNTS_ACT_SEND_INS", param);		
		selectOne("mhc.web.st.statisticsexcel.CALL_PRC_TM_CNTNTS_NURT_INS", param);
		selectOne("mhc.web.st.statisticsexcel.CALL_PRC_TM_CNTNTS_BODY_INS", param);	

		
		// TODO Auto-generated method stub
		return rsStr;	
	}
}
