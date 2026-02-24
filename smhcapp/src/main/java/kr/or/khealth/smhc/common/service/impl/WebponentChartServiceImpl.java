package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import kr.or.khealth.smhc.common.service.WebponentChartService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("webponentChartService")
public class WebponentChartServiceImpl extends EgovAbstractServiceImpl implements WebponentChartService{
	
	@Resource(name="webponentChartDAO")
    private WebponentChartDAO chartDAO;
	
	@Override
	public List<Map<String, String>> getChartData(Map<String, Object> param)
			throws Exception {
		
		return chartDAO.getChartData(param);
	}

}
