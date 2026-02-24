package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.gn.service.GnrlNoticeSetMngService;
import kr.go.mhc.mhcweb.sv.service.NoticeSetMngService;

@Service("web.gn.GnrlNoticeSetMngService")
public class GnrlNoticeSetMngServiceImpl implements GnrlNoticeSetMngService{
	
	@Resource(name="web.gn.GnrlNoticeSetMngServiceDAO")
	private GnrlNoticeSetMngServiceDAO gnrlNoticeSetMngServiceDAO;

	@Override
	public List<Map<String, String>> getTrgterGroup(Map<String, Object> param) {		
		return gnrlNoticeSetMngServiceDAO.getTrgterGroup(param);
	}

	@Override
	public List<Map<String, String>> manualLinkPageList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return gnrlNoticeSetMngServiceDAO.manualLinkPageList(param);
	}

	@Override
	public List<Map<String, String>> getPushNoticetrgterList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return gnrlNoticeSetMngServiceDAO.getPushNoticetrgterList(param);
	}

	@Override
	public List<Map<String, String>> getNoticeSttusList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return gnrlNoticeSetMngServiceDAO.getNoticeSttusList(param);
	}

	@Override
	public List<Map<String, String>> getTodayManualSendList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return gnrlNoticeSetMngServiceDAO.getTodayManualSendList(param);
	}

	@Override
	public List<Map<String, String>> getReceiverList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlNoticeSetMngServiceDAO.getReceiverList(param);
	}

	@Override
	public Map<String, Object> getSttusCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlNoticeSetMngServiceDAO.getSttusCnt(param);
	}

	@Override
	public List<Map<String, String>> selectTrgterNoticeSndSttus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlNoticeSetMngServiceDAO.selectTrgterNoticeSndSttus(param);
	}

	@Override
	public Map<String, String> checkTrgterJoinInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlNoticeSetMngServiceDAO.checkTrgterJoinInfo(param);
	}

	
	

}
