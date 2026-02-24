package kr.go.mhc.mhcweb.st.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.st.service.StatisticsExcelService;

import org.springframework.stereotype.Service;

@Service("web.st.StatisticsExcelService")
public class StatisticsExcelServiceImpl implements StatisticsExcelService{
	
	@Resource(name="web.st.StatisticsExcelServiceDAO")
	private StatisticsExcelServiceDAO statisticsExcelServiceDAO;
	
	@Override
	public List<Map<String,String>> statisticsList(Map<String, Object> param) throws Exception{
		return statisticsExcelServiceDAO.statisticsList(param);
	}
	
	@Override
	public List<Map<String,String>> statisticsStndList(Map<String, Object> param) throws Exception{
		return statisticsExcelServiceDAO.statisticsStndList(param);
	}
	
	@Override
	public List<Map<String,String>> statisticsInfo(Map<String,Object> param) throws Exception{
		List<Map<String,String>> paramList = statisticsExcelServiceDAO.statisticsTableColInfo(param);
		param.put("paramList", paramList);
		
		List<Map<String,String>> rsList = statisticsExcelServiceDAO.statisticsInfoInit(param);
		List<Map<String,String>> dataList = statisticsExcelServiceDAO.statisticsInfo(param);
		
		for(int i=0;i<dataList.size();i++){
			rsList.add(dataList.get(i));
		}
		return rsList;
	}
	
	@Override
	public List<Map<String,String>> statisticsTableColInfo(Map<String,Object> param) throws Exception{
		return statisticsExcelServiceDAO.statisticsTableColInfo(param);
	}
	
	@Override
	public String CALL_PRC_TM_STATS_ALL_INS(Map<String,Object> param) throws Exception{
		return statisticsExcelServiceDAO.CALL_PRC_TM_STATS_ALL_INS(param);
	}
}
