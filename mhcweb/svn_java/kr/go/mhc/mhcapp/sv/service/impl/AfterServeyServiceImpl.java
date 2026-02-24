package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.sv.service.AfterServeyService;

@Service("mhcapp.sv.AfterServeyService")
public class AfterServeyServiceImpl implements AfterServeyService{
	
	@Resource(name="mhcapp.sv.AfterServeyDAO")
    private AfterServeyDAO afterServeyDAO;
	
	@Override
	public Map<String, Object> insertafterServeyAnswr(Map<String, Object> param) throws Exception {
		
		return afterServeyDAO.insertafterServeyAnswr(param);
	}
	
	@Override
	public List<Map<String, String>> selectAfterServeyCodeList(Map<String, Object> param) throws Exception {
		return afterServeyDAO.selectAfterServeyCodeList(param);
	}
	
	@Override
	public List<Map<String, String>> selectAfterServeyList(Map<String, Object> param) throws Exception {
		
		return afterServeyDAO.selectAfterServeyList(param);
	}

	@Override
	public Integer updateAfterServeyAnswr(Map<String, Object> param) throws Exception {

		return afterServeyDAO.updateAfterServeyAnswr(param);
	}

}
