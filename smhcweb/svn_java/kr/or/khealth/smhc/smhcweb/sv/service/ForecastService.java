package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;

public interface ForecastService {
	
	public int selectFcstSnSeq() throws Exception;

	public Map<String, String> getLastFcstInfo(Map<String, Object> mastrParam)  throws Exception;

	public void mergeFcstInfo(Map<String, Object> mastrParam) throws Exception;

	public void insertFcstInfoDtls(Map<String, Object> dtlsParam) throws Exception;

	public void updateFcstDtaCnt(Map<String, Object> mastrParam)  throws Exception;

	public List<Map<String, Object>> selectFcstRiskLevel(Map<String, Object> mastrParam)  throws Exception;

	public void delFcstInfoDtls(Map<String, Object> mastrParam)  throws Exception;
}
