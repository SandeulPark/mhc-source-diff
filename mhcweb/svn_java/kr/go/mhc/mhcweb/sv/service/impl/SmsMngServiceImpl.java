package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sv.service.SmsMngService;

import org.springframework.stereotype.Service;

@Service("web.sv.SmsMngService")
public class SmsMngServiceImpl implements SmsMngService {

	@Resource(name="web.sv.SmsMngDAO")
	private SmsMngDAO smsMngDAO;
	
	@Override
	public List<Map<String, String>> getSmsSendTrgterList(Map<String, Object> param) throws Exception {
		return smsMngDAO.getSmsSendTrgterList(param);
	}
	
	@Override
	public List<Map<String, String>> getSmsSendManagerList(Map<String, Object> param) throws Exception {
		return smsMngDAO.getSmsSendManagerList(param);
	}
	
	@Override
	public List<Map<String, String>> getSmsSendTodayList(Map<String, Object> param) throws Exception {
		return smsMngDAO.getSmsSendTodayList(param);
	}
	
	@Override
	public void saveSmsMaster(Map<String, Object> param) throws Exception {
		smsMngDAO.saveSmsMaster(param);
	}

	@Override
	public void saveSmsHis(Map<String, Object> param) throws Exception {
		smsMngDAO.saveSmsHis(param);
	}
	
	@Override
	public List<Map<String,String>> getSmsSendList(Map<String,Object> param) throws Exception {
		return smsMngDAO.getSmsSendList(param);
	}
	
	@Override
	public List<Map<String,String>> getSmsSendDetailList(Map<String,Object> param) throws Exception {
		return smsMngDAO.getSmsSendDetailList(param);
	}
	
	@Override
	public List<Map<String,String>> getSmsTrgterSttus(Map<String,Object> param) throws Exception {
		return smsMngDAO.getSmsTrgterSttus(param);
	}
	
	@Override
	public int getSmsMsgId() throws Exception {
		return smsMngDAO.getSmsMsgId();
	}
	
	@Override
	public int getMmsMsgId() throws Exception {
		return smsMngDAO.getMmsMsgId();
	}
	
	@Override
	public void saveSmsAgent(Map<String, Object> param) throws Exception {
		smsMngDAO.saveSmsAgent(param);
	}
	
	@Override
	public void saveMmsAgent(Map<String, Object> param) throws Exception {
		smsMngDAO.saveMmsAgent(param);
	}
	
	@Override
	public Map<String, String> getSmsCharge(Map<String, Object> param) throws Exception {
		return smsMngDAO.getSmsCharge(param);
	}
}
