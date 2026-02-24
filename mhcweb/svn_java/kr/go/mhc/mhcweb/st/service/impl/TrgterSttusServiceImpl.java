package kr.go.mhc.mhcweb.st.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.st.service.TrgterSttusService;

import org.springframework.stereotype.Service;

@Service("web.st.TrgterSttusService")
public class TrgterSttusServiceImpl implements TrgterSttusService{
	
	@Resource(name="web.st.TrgterSttusServiceDAO")
	private TrgterSttusServiceDAO trgterSttusServiceDAO;

	@Override
	public List<Map<String, String>> trgterRegSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.trgterRegSttusList(param);
	}

	@Override
	public List<Map<String, String>> trgterRegDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.trgterRegDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> svcJoinSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.svcJoinSttusList(param);
	}
	
	@Override
	public List<Map<String, String>> svcJoinDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.svcJoinDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> healthExamRsltSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.healthExamRsltSttusList(param);
	}
	
	@Override
	public List<Map<String, String>> healthExamRsltDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.healthExamRsltDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> deviceDistrbtSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.deviceDistrbtSttusList(param);
	}
	
	@Override
	public List<Map<String, String>> deviceDistrbtDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.deviceDistrbtDtlsSttusList(param);
	}

	/*******************************************************************************************************/

	@Override
	public List<Map<String, String>> svcBgnRegSttusTotalCnt(Map<String, Object> param) throws Exception {
		return trgterSttusServiceDAO.svcBgnRegSttusTotalCnt(param);
	}

	@Override
	public List<Map<String, String>> svcBgnDeRegSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.svcBgnDeRegSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> trgterCnctSttusList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return trgterSttusServiceDAO.trgterCnctSttusList(param);
	}

}
