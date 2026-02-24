package kr.or.khealth.smhc.smhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcapp.sv.service.PointService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("smhcapp.sv.PointService")
public class PointServiceImpl extends EgovAbstractServiceImpl implements PointService{
	
	@Resource(name="smhcapp.sv.PointDAO")
    private PointDAO pointDAO;
	
	@Override
	public List<Map<String, String>> selectMissionPoint(Map<String, Object> param)
			throws Exception {
		
		return pointDAO.selectMissionPoint(param);
	}
	
	@Override
	public List<Map<String, String>> selectPointList(Map<String, Object> param)
			throws Exception {
		
		return pointDAO.selectPointList(param);
	}


}
