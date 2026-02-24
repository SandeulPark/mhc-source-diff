package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.st.service.CnslSttusService;

import org.springframework.stereotype.Service;

@Service("web.st.CnslSttusService")
public class CnslSttusServiceImpl implements CnslSttusService{
	
	@Resource(name="web.st.CnslSttusServiceDAO")
	private CnslSttusServiceDAO cnslSttusServiceDAO;

	@Override
	public List<Map<String, String>> bodyActIntensiveCnslSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.bodyActIntensiveCnslSttusList(param);
	}

	@Override
	public List<Map<String, String>> bodyActIntensiveCnslDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.bodyActIntensiveCnslDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> nutriIntensiveCnslSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.nutriIntensiveCnslSttusList(param);
	}
	
	@Override
	public List<Map<String, String>> nutriIntensiveCnslDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.nutriIntensiveCnslDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> nosmokTmprrncIntensiveSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.nosmokTmprrncIntensiveSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> comnCnslSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.comnCnslSttusList(param);
	}
	
	@Override
	public List<Map<String, String>> comnCnslDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.comnCnslDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> mthlyHealthRptSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.mthlyHealthRptSttusList(param);
	}
	
	@Override
	public List<Map<String, String>> mthlyHealthRptDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslSttusServiceDAO.mthlyHealthRptDtlsSttusList(param);
	}

}
