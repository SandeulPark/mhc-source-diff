package kr.or.khealth.smhc.smhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.khealth.smhc.smhcapp.sv.service.DistributeDeviceService;

@Service("smhcapp.sv.DistributeDeviceService")
public class DistributeDeviceServiceImpl extends EgovAbstractServiceImpl implements DistributeDeviceService {

	@Resource(name = "smhcapp.sv.DistributeDeviceDAO")
	private DistributeDeviceDAO distributeDevicedao;
		
	@Override
	public List<Map<String, Object>> selectPymntDeviceInf(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return distributeDevicedao.selectPymntDeviceInf(param);
	}

	
}
