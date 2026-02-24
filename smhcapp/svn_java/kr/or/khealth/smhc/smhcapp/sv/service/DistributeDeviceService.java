package kr.or.khealth.smhc.smhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface DistributeDeviceService {

	public List<Map<String, Object>> selectPymntDeviceInf(Map<String, Object> param) throws Exception;
} 
