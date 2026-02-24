package kr.or.khealth.smhc.smhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

@Repository("smhcapp.sv.DistributeDeviceDAO")
public class DistributeDeviceDAO extends DMultiEgovAbstractMapper {

	public List<Map<String, Object>> selectPymntDeviceInf(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> deviceList = selectList("smhcapp.sv.deviceDistribute.selectPymntDeviceInf", param);
		return deviceList;
	}
}
