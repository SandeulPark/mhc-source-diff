package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;



import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("webponentChartDAO")
public class WebponentChartDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> getChartData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("getChartData===========================================");		
		List<Map<String,String>> rsList = selectList("mhc.common.chart.chartData", param);	
		System.out.println("getChartData========================================" + rsList);
		return rsList;  
	}
	
}
