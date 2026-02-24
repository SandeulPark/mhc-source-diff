package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

@Repository("forecastDAO")
public class ForecastDAO extends DMultiEgovAbstractMapper{

	public int selectFcstSnSeq() {		
		// TODO Auto-generated method stub
		return selectOne("smhc.web.sv.forecast.selectFcstSnSeq");
	}

	public Map<String, String> getLastFcstInfo(Map<String, Object> mastrParam) {
		// TODO Auto-generated method stub
		return selectOne("smhc.web.sv.forecast.getLastFcstInfo", mastrParam);
	}

	public void mergeFcstInfo(Map<String, Object> mastrParam) {
		// TODO Auto-generated method stub
		insert("smhc.web.sv.forecast.mergeFcstInfo", mastrParam);
	}

	public void insertFcstInfoDtls(Map<String, Object> dtlsParam) {
		// TODO Auto-generated method stub
		insert("smhc.web.sv.forecast.insertFcstInfoDtls", dtlsParam);
	}

	public void updateFcstDtaCnt(Map<String, Object> mastrParam) {
		// TODO Auto-generated method stub
		update("smhc.web.sv.forecast.updateFcstDtaCnt", mastrParam);
	}

	public List<Map<String, Object>> selectFcstRiskLevel(Map<String, Object> mastrParam) {
		// TODO Auto-generated method stub
		return selectList("smhc.web.sv.forecast.selectFcstRiskLevel",mastrParam);
	}

	public void delFcstInfoDtls(Map<String, Object> mastrParam) {
		// TODO Auto-generated method stub
		delete("smhc.web.sv.forecast.delFcstInfoDtls", mastrParam);
	}
}
