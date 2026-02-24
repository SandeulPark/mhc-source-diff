package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


@Repository("web.tg.PhisCnctTrgterCurDAO")
public class PhisCnctTrgterCurDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, String>> phisCnctTrgterCurCount(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsMap = selectList("mhc.web.tg.phisCnctTrgterCur.phisCnctTrgterCurCount",param);	
		return rsMap;
	}
	
	public List<Map<String, String>> phisCnctTrgterCurTrgterList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsMap = selectList("mhc.web.tg.phisCnctTrgterCur.phisCnctTrgterCurTrgterList",param);	
		return rsMap;
	}	
	public List<Map<String, String>> phisCnctTrgterCurPopTrgterExamList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsMap = selectList("mhc.web.tg.phisCnctTrgterCur.phisCnctTrgterCurPopTrgterExamList",param);
		return rsMap;
	}
}
