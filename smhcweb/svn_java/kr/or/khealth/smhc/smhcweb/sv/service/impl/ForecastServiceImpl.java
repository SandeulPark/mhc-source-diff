package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.khealth.smhc.smhcweb.sv.service.ForecastService;

@Service("common.forecastService")
public class ForecastServiceImpl extends EgovAbstractServiceImpl implements ForecastService {
	
	@Resource(name="forecastDAO")
	private ForecastDAO forecastDAO;
	
	@Override
	public int selectFcstSnSeq() throws Exception {
		// TODO Auto-generated method stub
		return forecastDAO.selectFcstSnSeq();
	}
	
	@Override
	public Map<String, String> getLastFcstInfo(Map<String, Object> mastrParam) throws Exception {
		// TODO Auto-generated method stub
		return forecastDAO.getLastFcstInfo(mastrParam);
	}

	@Override
	public void mergeFcstInfo(Map<String, Object> mastrParam) throws Exception {
		// TODO Auto-generated method stub
		forecastDAO.mergeFcstInfo(mastrParam);
	}

	@Override
	public void insertFcstInfoDtls(Map<String, Object> dtlsParam) throws Exception {
		// TODO Auto-generated method stub
		forecastDAO.insertFcstInfoDtls(dtlsParam);
	}

	@Override
	public void updateFcstDtaCnt(Map<String, Object> mastrParam) throws Exception {
		// TODO Auto-generated method stub
		forecastDAO.updateFcstDtaCnt(mastrParam);
	}

	@Override
	public List<Map<String, Object>> selectFcstRiskLevel(Map<String, Object> mastrParam) throws Exception {
		// TODO Auto-generated method stub
		return forecastDAO.selectFcstRiskLevel(mastrParam);
	}

	@Override
	public void delFcstInfoDtls(Map<String, Object> mastrParam) throws Exception {
		// TODO Auto-generated method stub
		forecastDAO.delFcstInfoDtls(mastrParam);
	}

}
