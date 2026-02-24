package kr.or.khealth.smhc.smhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import kr.or.khealth.smhc.smhcweb.cm.service.MainService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService{
	
	@Resource(name="mainDAO")
    private MainDAO mainDAO;

	@Override
	public List<Map<String, Object>> selectSeniorCalendarMainList(Map<String, Object> param) throws Exception {
		return mainDAO.selectSeniorCalendarMainList(param);
	}
	
	@Override
	public Map<String,Object> selectServerTime(Map<String,Object> param) throws Exception{
		return mainDAO.selectServerTime(param);
	}
	
	@Override
	public Map<String,Object> selectSvcStatusIng(Map<String,Object> param) throws Exception{
		return mainDAO.selectSvcStatusIng(param);
	}

	@Override
	public Map<String,Object> selectTodaySvcStatusReg(Map<String,Object> param) throws Exception{
		return mainDAO.selectTodaySvcStatusReg(param);
	}
	
	@Override
	public List<Map<String, Object>> selectSeniorFaceToFaceVisitList(Map<String, Object> param) throws Exception {
		return mainDAO.selectSeniorFaceToFaceVisitList(param);
	}
	
	@Override
	public Map<String, Object> selectTodayMeasrInfo(Map<String, Object> param) throws Exception {
		return mainDAO.selectTodayMeasrInfo(param);
	}	

}
